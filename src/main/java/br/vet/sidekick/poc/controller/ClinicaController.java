package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.controller.dto.CadastroClinicaDto;
import br.vet.sidekick.poc.exceptionResolver.exception.ClinicaAlreadyExistsException;
import br.vet.sidekick.poc.exceptionResolver.exception.FuncionarioAlreadyExistsException;
import br.vet.sidekick.poc.exceptionResolver.exception.ResponsavelTecnicoAlreadyExistsException;
import br.vet.sidekick.poc.model.Clinica;
import br.vet.sidekick.poc.service.ClinicaService;
import br.vet.sidekick.poc.service.FuncionarioService;
import br.vet.sidekick.poc.service.VeterinarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cadastro/clinica")
@CrossOrigin
@Slf4j
public class ClinicaController extends BaseController {
    @Autowired
    private ClinicaService clinicaService;

    @Autowired
    private VeterinarioService veterinarioService;

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<Clinica> registerClinica(
            @RequestBody CadastroClinicaDto cadastro
    ) throws RuntimeException {
        Optional<Clinica> clinica = null;
        try {
            clinica = clinicaService.create(cadastro);
            if(clinica.isPresent()) {
                try {
                    veterinarioService.createResponsavelTecnico(cadastro, clinica.get());
                } catch (ResponsavelTecnicoAlreadyExistsException e){
                    log.warn(e.getLocalizedMessage());
                }
                if(!cadastro.getDonoCpf().equals(cadastro.getTecnicoCpf())) {
                    try {
                        funcionarioService.createDonoClinica(cadastro, clinica.get());
                    } catch (FuncionarioAlreadyExistsException e){
                        log.warn(e.getLocalizedMessage());
                    }
                }
            }
        } catch (ClinicaAlreadyExistsException e){
            throwExceptionFromController(e);
        }
        return ResponseEntity.created(URI.create("/clinica/" + clinica.get().getId().toString())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clinica> getOne(@PathVariable Long id){
        Optional<Clinica> clinica = clinicaService.getById(id);
        return clinica.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(clinica.get());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Clinica>> getAll(){
        return ResponseEntity.ok(clinicaService.getAll());
    }
    // TODO: Implementar métodos PUT e DELETE
}
