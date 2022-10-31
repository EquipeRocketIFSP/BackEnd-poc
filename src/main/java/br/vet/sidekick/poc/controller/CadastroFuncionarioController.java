package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.controller.dto.CadastroFuncionarioDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController("/cadastro-funcionario")
public class CadastroFuncionarioController {

    private CadastroFuncionarioDto funcionarioMock = CadastroFuncionarioDto.getMock();

    List<CadastroFuncionarioDto> list = Arrays.asList(funcionarioMock);
    
    @PostMapping
    public ResponseEntity<CadastroFuncionarioDto> registerfuncionario(
            @RequestBody CadastroFuncionarioDto funcionario
    ){
        list.add(funcionario);
        return ResponseEntity.created(
                URI.create("/funcionario/" + String.valueOf(list.indexOf(funcionario))
                )).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<CadastroFuncionarioDto> getOne(
            @RequestParam Integer id
    ){
        CadastroFuncionarioDto funcionario = list.get(id);
        return funcionario != null
                ? ResponseEntity.ok(funcionario)
                : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<CadastroFuncionarioDto>> getAll(){
        return list.size() == 0
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(list);
    }
}
