package br.vet.sidekick.poc.exceptionResolver.exception;

public class AnimalNotFoundException extends RuntimeException{
    public AnimalNotFoundException(String message) {
        super(message);
    }
}
