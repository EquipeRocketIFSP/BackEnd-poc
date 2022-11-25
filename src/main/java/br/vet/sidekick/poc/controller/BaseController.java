package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.conf.security.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class BaseController {

    @Autowired
    private TokenService tokenService;

    protected Long getClinicaFromRequester(String auth) {
        return tokenService.getFuncionario(auth).getClinica().getId();
    }


    protected void throwExceptionFromController(RuntimeException e) throws RuntimeException {
        log.error(e.getLocalizedMessage());
        throw e;
    }
}
