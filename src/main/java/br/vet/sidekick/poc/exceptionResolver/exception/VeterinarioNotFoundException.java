package br.vet.sidekick.poc.exceptionResolver.exception;

public class VeterinarioNotFoundException extends RuntimeException {
    public VeterinarioNotFoundException(String message) {
        super(message);
    }
}
