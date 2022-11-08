package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.controller.dto.CadastroFuncionarioDto;
import br.vet.sidekick.poc.model.Funcionario;
import br.vet.sidekick.poc.repository.FuncionarioRepository;
import br.vet.sidekick.poc.service.FuncionarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/cadastro-funcionario")
public class CadastroFuncionarioController {

    private static CadastroFuncionarioDto funcionarioMock = CadastroFuncionarioDto.getMock();

//    private static List<CadastroFuncionarioDto> list = new ArrayList<>();

//    static {list.add(funcionarioMock);}

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<Funcionario> registerFuncionario(
            @RequestBody Funcionario funcionario
    ){
        try {
            funcionario = funcionarioService.save(funcionario);
            return ResponseEntity.created(
                    URI.create("/funcionario/" + String.valueOf(funcionario.getId())
                    )).build();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getOne(
            @PathVariable() Long id
    ){
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
