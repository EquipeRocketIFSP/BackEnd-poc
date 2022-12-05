package br.vet.sidekick.poc.exceptionResolver.exception;

public class AgendamentoCreateException extends RuntimeException{
    public AgendamentoCreateException(String menssagem){
        super(menssagem);
    }
}
