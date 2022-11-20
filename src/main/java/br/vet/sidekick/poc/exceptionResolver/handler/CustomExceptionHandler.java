package br.vet.sidekick.poc.exceptionResolver.handler;

import br.vet.sidekick.poc.exceptionResolver.exception.ClinicaAlreadyExistsException;
import br.vet.sidekick.poc.exceptionResolver.exception.FuncionarioAlreadyExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            FuncionarioAlreadyExistsException.class,
            ClinicaAlreadyExistsException.class
    })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex,
            WebRequest request
    ){
        return handleExceptionInternal(
                ex,
                ex.getLocalizedMessage(),
                new HttpHeaders(),
                HttpStatus.CONFLICT,
                request
        );
    }
}
