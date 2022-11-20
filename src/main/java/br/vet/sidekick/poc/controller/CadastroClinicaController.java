package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.controller.dto.CadastroClinicaDto;
import br.vet.sidekick.poc.exceptionResolver.exception.ClinicaAlreadyExistsException;
import br.vet.sidekick.poc.exceptionResolver.exception.FuncionarioAlreadyExistsException;
import br.vet.sidekick.poc.exceptionResolver.exception.ResponsavelTecnicoAlreadyExistsException;
import br.vet.sidekick.poc.model.Clinica;
import br.vet.sidekick.poc.model.Funcionario;
import br.vet.sidekick.poc.model.Veterinario;
import br.vet.sidekick.poc.service.ClinicaService;
import br.vet.sidekick.poc.service.FuncionarioService;
import br.vet.sidekick.poc.service.VeterinarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cadastro/clinica")
@CrossOrigin
@Slf4j
public class CadastroClinicaController {
    private static CadastroClinicaDto cadastroDto = CadastroClinicaDto.getMock();

    @Autowired
    private ClinicaService clinicaService;

    @Autowired
    private VeterinarioService veterinarioService;

    //TODO: remover as duas linhas abaixo
    private static List<CadastroClinicaDto> cadastros = new ArrayList<>();
    static {cadastros.add(cadastroDto);}

    private void throwExceptionFromController(RuntimeException e) throws RuntimeException {
        log.error(e.getLocalizedMessage());
        throw e;
    }
    @PostMapping
    public ResponseEntity<Clinica> registerClinica(
            @RequestBody CadastroClinicaDto cadastro) throws RuntimeException {
        Optional<Clinica> clinica = null;
        try {
            clinica = clinicaService.create(cadastro);
            if(clinica.isPresent()) {
                try {
                    Optional<Veterinario> responsavelTecnico = veterinarioService.createResponsavelTecnico(cadastro, clinica.get());
                } catch (ResponsavelTecnicoAlreadyExistsException e){
                    log.warn("Responsável Técnico não adicionado pois já existe um cadastrado para a clínica.");
                }
            }
        } catch (ClinicaAlreadyExistsException e){
            throwExceptionFromController(e);
        } catch (FuncionarioAlreadyExistsException e){
            throwExceptionFromController(e);
        }
        return clinica.isEmpty()
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.created(URI.create("/clinica/" + clinica.get().getId().toString())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CadastroClinicaDto> getOne(@PathVariable Long id){
        return id != 1L
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(cadastroDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CadastroClinicaDto>> getAll(){
        return ResponseEntity.ok(cadastros);
    }
}
