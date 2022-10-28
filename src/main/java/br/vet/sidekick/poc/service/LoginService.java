package br.vet.sidekick.poc.service;

import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;

@Service
public interface LoginService {
    String doAuthentication(@Email String username, String password);
}
