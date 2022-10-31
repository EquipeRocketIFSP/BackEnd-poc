package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.controller.dto.CadastroFuncionarioDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cadastro-funcionario")
public class CadastroFuncionarioController {

    private static CadastroFuncionarioDto funcionarioMock = CadastroFuncionarioDto.getMock();

    private static List<CadastroFuncionarioDto> list = new ArrayList<>();

    static {list.add(funcionarioMock);}

    @PostMapping
    public ResponseEntity<CadastroFuncionarioDto> registerfuncionario(
            @RequestBody CadastroFuncionarioDto funcionario
    ){
        list.add(funcionario);
        return ResponseEntity.created(
                URI.create("/funcionario/" + String.valueOf(list.indexOf(funcionario))
                )).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CadastroFuncionarioDto> getOne(
            @PathVariable() Integer id
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
