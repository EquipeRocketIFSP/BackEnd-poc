package br.vet.sidekick.poc.exceptionResolver.exception;

public class ResponsavelTecnicoAlreadyExistsException extends FuncionarioAlreadyExistsException {
    public ResponsavelTecnicoAlreadyExistsException(String message) {
        super(message);
    }
}