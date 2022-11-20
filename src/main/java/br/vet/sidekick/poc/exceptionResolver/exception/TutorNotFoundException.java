package br.vet.sidekick.poc.exceptionResolver.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TutorNotFoundException extends RuntimeException {
    public TutorNotFoundException(String message) {
        super(message);
    }
}
