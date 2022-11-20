package br.vet.sidekick.poc.exceptionResolver.exception;

public class ProntuarioAlreadyStartedException extends RuntimeException {
    public ProntuarioAlreadyStartedException(String message) { super(message); }
}
