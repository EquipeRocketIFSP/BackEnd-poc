package br.vet.sidekick.poc.exceptionResolver.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProntuarioNotFoundException extends RuntimeException {
    public ProntuarioNotFoundException(String message){
        super(message);
    }
}
