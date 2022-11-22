package br.vet.sidekick.poc.exceptionResolver.exception;

public class ClinicaNotFoundException extends RuntimeException {
    public ClinicaNotFoundException(String message) {
        super(message);
    }
}
