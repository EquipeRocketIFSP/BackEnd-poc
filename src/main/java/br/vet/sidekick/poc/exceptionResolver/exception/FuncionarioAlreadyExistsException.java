package br.vet.sidekick.poc.exceptionResolver.exception;

public class FuncionarioAlreadyExistsException extends RuntimeException {
    public FuncionarioAlreadyExistsException(String message){
        super(message);
    }
}
