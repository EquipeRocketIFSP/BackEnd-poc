package br.vet.sidekick.poc.exceptionResolver.exception;

public class AnimalNotFound extends RuntimeException{
    public AnimalNotFound(){
        super("Animal não encontrado.");
    }
}
