package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.conf.security.service.TokenService;
import br.vet.sidekick.poc.controller.dto.TokenDto;
import br.vet.sidekick.poc.model.Funcionario;
import br.vet.sidekick.poc.controller.dto.LoginForm;
import br.vet.sidekick.poc.model.Veterinario;
import br.vet.sidekick.poc.service.FuncionarioService;
import br.vet.sidekick.poc.service.VeterinarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@Slf4j
public class AuthenticationController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private VeterinarioService veterinarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> authenticate(
            @Validated @RequestBody LoginForm login
    ){
        UsernamePasswordAuthenticationToken loginData = login.convert();
        Authentication auth;
        String token;
        try {
            auth = authenticationManager.authenticate(loginData);
            token = tokenService.create(auth);
        }catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }
        // retornar nome e crmv
        Optional<Veterinario> vet = veterinarioService.findByEmail(login.getEmail());
        System.out.println("vet: " + vet.toString());
        return  ResponseEntity.ok(
                vet.isPresent()
                ? TokenDto.builder()
                        .token(token)
                        .type("Bearer")
                        .nome(vet.get().getNome())
                        .crmv(vet.get().getRegistroCRMV())
                        .build()
                :
                        TokenDto.builder()
                        .token(token)
                        .type("Bearer")
                        .build()
        );

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
