package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.controller.dto.CadastroFuncionarioDto;
import br.vet.sidekick.poc.controller.dto.ListagemFuncionarioDto;
import br.vet.sidekick.poc.model.Clinica;
import br.vet.sidekick.poc.model.Funcionario;
import br.vet.sidekick.poc.model.Veterinario;
import br.vet.sidekick.poc.repository.ClinicaRepository;
import br.vet.sidekick.poc.repository.FuncionarioRepository;
import br.vet.sidekick.poc.service.FuncionarioService;
import br.vet.sidekick.poc.service.VeterinarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private VeterinarioService veterinarioService;

    @Autowired
    private ClinicaRepository clinicaService;

    @PostMapping
    public ResponseEntity<Funcionario> registerFuncionario(@RequestBody CadastroFuncionarioDto funcionarioDto) {
        try {
            Optional<Clinica> clinica = this.clinicaService.findById(funcionarioDto.getClinica());

            if (clinica.isEmpty())
                return ResponseEntity.badRequest().build();

            Funcionario funcionario = funcionarioDto.convert();
            funcionario.setClinica(clinica.get());

            if (funcionarioDto.getCrmv().length() != 0) {
                Veterinario veterinario = new Veterinario(funcionario);
                veterinario.setRegistroCRMV(funcionarioDto.getCrmv());

                this.veterinarioService.save(veterinario);

                return ResponseEntity.created(URI.create("/funcionario/" + String.valueOf(funcionario.getId()))).build();
            }

            this.funcionarioService.save(funcionario);

            return ResponseEntity.created(URI.create("/funcionario/" + String.valueOf(funcionario.getId()))).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<ListagemFuncionarioDto>> getAll() {
        List<ListagemFuncionarioDto> funcionarioDtos = this.funcionarioRepository.findAll()
                .stream()
                .map((funcionario) -> {
                    ListagemFuncionarioDto funcionarioDto = new ListagemFuncionarioDto(funcionario);

                    //TODO: Setar CRMV
                    return funcionarioDto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(funcionarioDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getOne(
            @PathVariable() Long id
    ) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        if (funcionario.isPresent())
            return ResponseEntity.ok(funcionario.get());

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> update(
            @PathVariable Long id,
            @RequestBody CadastroFuncionarioDto funcionario
    ) {
        Optional<Funcionario> currentFuncionario = funcionarioRepository.findById(id);
        if (currentFuncionario.isPresent()) {
            BeanUtils.copyProperties(funcionario, currentFuncionario.get(), "id");
            Funcionario savedFuncionario = funcionarioService.save(currentFuncionario.get());
            return ResponseEntity.ok(savedFuncionario);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Funcionario> delete(
            @PathVariable Long id
    ) {
        try {
            funcionarioService.deleteById(id);
            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


//    @PostMapping
//    public ResponseEntity<CadastroFuncionarioDto> registerfuncionario(
//            @RequestBody CadastroFuncionarioDto funcionario
//    ){
//        list.add(funcionario);
//        return ResponseEntity.created(
//                URI.create("/funcionario/" + String.valueOf(list.indexOf(funcionario))
//                )).build();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CadastroFuncionarioDto> getOne(
//            @PathVariable() Integer id
//    ){
//        CadastroFuncionarioDto funcionario = list.get(id);
//        return funcionario != null
//                ? ResponseEntity.ok(funcionario)
//                : ResponseEntity.notFound().build();
//    }
//
//    @GetMapping
//    public ResponseEntity<List<CadastroFuncionarioDto>> getAll(){
//        return list.size() == 0
//                ? ResponseEntity.notFound().build()
//                : ResponseEntity.ok(list);
//    }

}
