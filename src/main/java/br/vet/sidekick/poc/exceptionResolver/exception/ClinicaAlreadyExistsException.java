package br.vet.sidekick.poc.exceptionResolver.exception;

public class ClinicaAlreadyExistsException extends RuntimeException {
    ClinicaAlreadyExistsException(String message){
        super(message);
    }
}
