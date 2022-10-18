package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.model.Funcionario;
import br.vet.sidekick.poc.service.FuncionarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@RestController
@RequestMapping("login")
@Validated
@CrossOrigin
@Slf4j
public class LoginController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<Funcionario> authenticate(
            @Validated @RequestBody Funcionario funcionario
    ){
        return funcionarioService.authenticate(funcionario.getUsername(), funcionario.getPassword())
                ? ResponseEntity.ok(funcionarioService.get(funcionario))
                : ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Funcionario> create(
            @Validated @RequestBody Funcionario funcionario
    ){
        var created = funcionarioService.create(funcionario);
        return created.isPresent()
                ? ResponseEntity.created(URI.create("/funcionario/" + created.get().getId())).build()
                : ResponseEntity.status(UNPROCESSABLE_ENTITY).header("reason", "Already exists").build();
    }
}
