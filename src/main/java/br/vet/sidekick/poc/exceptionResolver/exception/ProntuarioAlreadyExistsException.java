package br.vet.sidekick.poc.exceptionResolver.exception;

public class ProntuarioAlreadyExistsException extends RuntimeException {
    public ProntuarioAlreadyExistsException(String message) {
        super(message);
    }
}
