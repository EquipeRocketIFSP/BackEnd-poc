package br.vet.sidekick.poc.exceptionResolver.handler;

import br.vet.sidekick.poc.exceptionResolver.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            FuncionarioAlreadyExistsException.class,
            ClinicaAlreadyExistsException.class,
            ResponsavelTecnicoAlreadyExistsException.class,
            ProntuarioAlreadyExistsException.class,
            AgendamentoCreateException.class
    })
    protected ResponseEntity<Object> handleBadRequest(
            RuntimeException ex,
            WebRequest request
    ) {
        return handleExceptionInternal(
                ex,
                ex.getLocalizedMessage(),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    protected ResponseEntity<Object> handleNotFound(RuntimeException exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.getLocalizedMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
