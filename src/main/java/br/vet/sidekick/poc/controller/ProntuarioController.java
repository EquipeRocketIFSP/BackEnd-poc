package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.controller.dto.ListagemProntuarioDto;
import br.vet.sidekick.poc.controller.dto.ProntuarioDto;
import br.vet.sidekick.poc.controller.dto.RecuperarProntuarioDto;
import br.vet.sidekick.poc.exceptionResolver.exception.ProntuarioAlreadyExistsException;
import br.vet.sidekick.poc.exceptionResolver.exception.ProntuarioNotFoundException;
import br.vet.sidekick.poc.model.Animal;
import br.vet.sidekick.poc.model.Clinica;
import br.vet.sidekick.poc.model.Prontuario;
import br.vet.sidekick.poc.model.Veterinario;
import br.vet.sidekick.poc.repository.AnimalRepository;
import br.vet.sidekick.poc.repository.ClinicaRepository;
import br.vet.sidekick.poc.repository.ProntuarioRepository;
import br.vet.sidekick.poc.repository.VeterinarioRepository;
import br.vet.sidekick.poc.service.CertvetCodingService;
import br.vet.sidekick.poc.service.ProntuarioService;
import br.vet.sidekick.poc.service.impl.PdfServicePdfBoxImpl;
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
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/prontuario")
public class ProntuarioController extends BaseController {

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    @Autowired
    private CertvetCodingService certvetCodingService;

    @Autowired
    private PdfServicePdfBoxImpl pdfService;

    @Autowired
    private ProntuarioService prontuarioService;

    private Prontuario getProntuarioLatestVersion(String codigo) {
        List<Prontuario> prontuarios = prontuarioService.getByCodigo(codigo)
                .orElseThrow(() -> new ProntuarioNotFoundException("Prontuário não identificado"));
        Prontuario prontuario = null;
        if(prontuarios.size() > 0) {
            prontuario = prontuarios.stream()
                    .max(Comparator.comparing(Prontuario::getVersao))
                    .get();
        }
        return prontuario;
    }

    @PostMapping
    public ResponseEntity<Prontuario> create(
            @RequestBody ProntuarioDto prontuarioDto
    ) {
        log.debug("Iniciando cadastro prontuario");
// TODO: Verificar a implementação de validação de duplicidade de prontuários
//        Optional<String> certvetCode = certvetCodingService.checkExistingProntuario(prontuarioDto.getCertvetCode());
//        if (certvetCode.isPresent()){
//            throw new ProntuarioAlreadyExistsException("Prontuário já criado para o código: " + certvetCode.get());
//        }

        Prontuario prontuario = prontuarioDto.convert();
        log.debug("Prontuario convertido");

        Optional<Clinica> responseClinica = this.clinicaRepository.findById(prontuarioDto.getClinica());
        Optional<Veterinario> responseVeterinario = this.veterinarioRepository.findById(prontuarioDto.getVeterinario());
        Optional<Animal> responseAnimal = this.animalRepository.findById(prontuarioDto.getAnimal());

        boolean willThrow = responseClinica.isEmpty() || responseVeterinario.isEmpty() || responseAnimal.isEmpty();
        log.debug("Composições identificadas. " + (willThrow ? "Seguindo Bad Request" : "Persistindo"));

        if (willThrow)
            return ResponseEntity.badRequest().build();

        prontuario = prontuarioService.save(prontuario
//        prontuario = this.prontuarioRepository.save(prontuario
                .setClinica(responseClinica.get())
                .setVeterinario(responseVeterinario.get())
                .setAnimal(responseAnimal.get())
        );

        return ResponseEntity
                .created(URI.create("/prontuario/" + prontuario.getId()))
                .build();
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
        return prontuario != null
                ? ResponseEntity.ok()
                    .contentType(MediaType.valueOf(MediaType.APPLICATION_PDF_VALUE))
                    .body(pdfService.retrieveFromRepository(prontuario))
                : ResponseEntity.notFound().build();

    }

    @GetMapping
    public ResponseEntity<List<RecuperarProntuarioDto>> getAll() {
        List<RecuperarProntuarioDto> prontuarios = this.prontuarioRepository
                .findAll().stream()
                .map((prontuario) -> new RecuperarProntuarioDto(prontuario))
                .collect(Collectors.toList());

        return ResponseEntity.ok(prontuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListagemProntuarioDto> getOne(@PathVariable Long id) {
        Optional<Prontuario> responseProntuario = this.prontuarioRepository.findById(id);

        if (responseProntuario.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new ListagemProntuarioDto(responseProntuario.get()));
    }
}