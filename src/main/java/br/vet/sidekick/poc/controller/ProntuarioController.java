package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.conf.security.service.TokenService;
import br.vet.sidekick.poc.controller.dto.ListagemProntuarioDto;
import br.vet.sidekick.poc.controller.dto.ProntuarioDto;
import br.vet.sidekick.poc.controller.dto.ProntuarioV0Dto;
import br.vet.sidekick.poc.exceptionResolver.exception.ProntuarioAlreadyStartedException;
import br.vet.sidekick.poc.exceptionResolver.exception.ProntuarioNotFoundException;
import br.vet.sidekick.poc.model.*;
import br.vet.sidekick.poc.repository.AnimalRepository;
import br.vet.sidekick.poc.repository.ClinicaRepository;
import br.vet.sidekick.poc.repository.VeterinarioRepository;
import br.vet.sidekick.poc.service.FuncionarioClinicaValidator;
import br.vet.sidekick.poc.service.FuncionarioService;
import br.vet.sidekick.poc.service.PdfService;
import br.vet.sidekick.poc.service.ProntuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/prontuario")
public class ProntuarioController {

    @Autowired
    private ProntuarioService prontuarioService;

    @Autowired
    private PdfService pdfService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private FuncionarioClinicaValidator funcionarioClinicaValidator;

    @Autowired
    private ClinicaRepository clinicaRepository;
    @Autowired
    private VeterinarioRepository veterinarioRepository;
    @Autowired
    private AnimalRepository animalRepository;

    @PostMapping
    public ResponseEntity<Prontuario> create(
            @RequestBody ProntuarioV0Dto prontuariodto
    ) throws Exception {
        log.info("Iniciando cadastro prontuario");
        Prontuario prontuario = prontuarioService.convert(prontuariodto);
        log.info("Prontuario convertido");
        Optional<Clinica> responseClinica = clinicaRepository.findById(prontuariodto.getClinica());
        Optional<Veterinario> responseVeterinario = veterinarioRepository.findById(prontuariodto.getVeterinario());
        Optional<Animal> responseAnimal = animalRepository.findById(prontuariodto.getAnimal());
        log.info("Composições identificadas");
        if (responseClinica.isEmpty() || responseVeterinario.isEmpty() || responseAnimal.isEmpty()) {
            log.info("Seguindo Bad Request");
            return ResponseEntity.badRequest().build();
        }

        prontuario.setClinica(responseClinica.get());
        prontuario.setVeterinario(responseVeterinario.get());
        prontuario.setAnimal(responseAnimal.get());

        prontuario = prontuarioService.save(prontuario);

        return ResponseEntity
                .created(URI.create("/prontuario/" + prontuario.getId()))
                .build();
    }

    @PostMapping("/new")
    public ResponseEntity<Prontuario> create(
            @RequestBody ProntuarioDto prontuarioDto
    ) throws Exception {
        log.info("Processando o prontuário");
        Prontuario prontuario = null;
        try {
            prontuario = prontuarioService.save(prontuarioService.convert(prontuarioDto));
            log.info(("Prontuario salvo: " + prontuario.getCodigo()));
        } catch (ProntuarioAlreadyStartedException e){
            log.info("prontuário já em elaboração");
            throw e;
        }
        return ResponseEntity
                .created(URI.create("/prontuario/" + prontuario.getCodigo()))
                .build();
//        Prontuario prontuario = prontuarioService.save(prontuarioDto.convert());
//        return ResponseEntity
//                .created(URI.create("/prontuario/" + prontuario.getId()))
//                .build();
    }

    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> getProntuarioPdf(
            @RequestHeader(HttpHeaders.CONTENT_TYPE) String contentType,
            @PathVariable String codigo
    ) throws IOException {
        if (!MediaType.APPLICATION_PDF_VALUE.equals(contentType))
            return ResponseEntity.badRequest()
                    .header("reason", "Media type not allowed")
                    .build();
        Prontuario prontuario = getProntuarioLatestVersion(codigo);
        if(prontuario != null){
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(MediaType.APPLICATION_PDF_VALUE))
                    .body(pdfService.retrieveFromRepository(prontuario));
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<ListagemProntuarioDto> getOne(@PathVariable Long id) {
        Optional<Prontuario> responseProntuario = prontuarioService.getById(id);

        if (responseProntuario.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new ListagemProntuarioDto(responseProntuario.get()));
    }
    @GetMapping("/byCode/{codigo}")
    public ResponseEntity<Prontuario> getProntuario(
            @RequestHeader(HttpHeaders.CONTENT_TYPE) String contentType,
            @PathVariable String codigo
    ) throws Exception {
        if(!MediaType.APPLICATION_JSON_VALUE.equals(contentType)) {
            log.warn("Media type not allowed");
            return ResponseEntity.badRequest()
                    .header("reason", "Media type not allowed")
                    .build();
        }
        Prontuario prontuario = getProntuarioLatestVersion(codigo);
        if(prontuario != null){
            return ResponseEntity.ok(prontuario);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Prontuario> updateProntuario(
            @PathVariable String codigo,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @RequestBody ProntuarioDto prontuarioDto
    ) throws Exception {
        Prontuario temp = prontuarioDto.convert(codigo);
        if(!funcionarioClinicaValidator.isRequestedDocumento_CodigoFromClinica(temp, getFuncionarioFrom(token).getClinica()))
            return ResponseEntity.unprocessableEntity()
                    .header("reason", "Documento não autorizado.")
                    .build();

        Prontuario prontuario = getProntuarioLatestVersion(codigo);
        if(prontuario != null){
            prontuario.applyDifference(temp);
            prontuario.incrementVersion();
            return ResponseEntity.ok(prontuarioService.save(prontuario));
        }
        return ResponseEntity.notFound().build();
    }

    private Prontuario getProntuarioLatestVersion(String codigo) {
        List<Prontuario> prontuarios = prontuarioService.getByCodigo(codigo)
                .orElseThrow(()->new ProntuarioNotFoundException("Prontuário não identificado"));
        Prontuario prontuario = null;
        if(prontuarios.size() > 0) {
            prontuario = prontuarios.stream()
                    .max(Comparator.comparing(Prontuario::getVersao))
                    .get();
        }
        return prontuario;
    }

    private Funcionario getFuncionarioFrom(String token) {
        return funcionarioService.findById(Long.parseLong(
                tokenService.jwtDecode(
                        token.substring(token.indexOf("\s")+1)
                        )
                        .getBody()
                        .getSubject()
                )).get();
    }

}
