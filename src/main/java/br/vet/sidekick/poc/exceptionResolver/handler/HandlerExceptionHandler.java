package br.vet.sidekick.poc.exceptionResolver.handler;

import br.vet.sidekick.poc.exceptionResolver.exception.ProntuarioAlreadyStartedException;
import br.vet.sidekick.poc.exceptionResolver.exception.ProntuarioNotFoundException;
import br.vet.sidekick.poc.exceptionResolver.exception.TutorNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class HandlerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value= {
            ProntuarioNotFoundException.class,
            TutorNotFoundException.class
    })
    protected ResponseEntity<Object> handleNotFound(
            RuntimeException ex,
            WebRequest request
    ) {
        return handleExceptionInternal(
                ex,
                ex.getLocalizedMessage(),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request
        );
    }

    @ExceptionHandler(value = {
            ProntuarioAlreadyStartedException.class
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
