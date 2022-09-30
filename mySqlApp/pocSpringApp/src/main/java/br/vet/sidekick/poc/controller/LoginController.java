package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.model.Funcionario;
import br.vet.sidekick.poc.service.IFuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@Validated
public class LoginController {

    @Autowired
    private IFuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<Funcionario> authenticate(@RequestBody Funcionario funcionario){
        return funcionarioService.authenticate(funcionario.getUsername(), funcionario.getPassword())
                ? ResponseEntity.ok(funcionarioService.get(funcionario))
                : ResponseEntity.noContent().build();
    }
}
