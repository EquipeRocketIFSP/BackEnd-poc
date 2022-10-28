package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.service.FuncionarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("user")
@Slf4j
@CrossOrigin
public class UserController {
    private final UserDetailsService detailsService;

    public UserController(UserDetailsService detailsService) {
        this.detailsService = detailsService;
    }

    @GetMapping
    public UserDetails getUser(Authentication authentication) {
        JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
        return detailsService.loadUserByUsername(token.getTokenAttributes().get("username").toString());
    }

}
