package br.vet.sidekick.poc.exceptionResolver.exception;

public class ClinicaAlreadyExistsException extends RuntimeException {
    public ClinicaAlreadyExistsException(String message){
        super(message);
    }
}
