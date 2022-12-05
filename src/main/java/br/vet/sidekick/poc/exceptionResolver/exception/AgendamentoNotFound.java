package br.vet.sidekick.poc.exceptionResolver.exception;

public class AgendamentoNotFound extends RuntimeException{
    public AgendamentoNotFound(){
        super("Agendamento não encontrado.");
    }
}
