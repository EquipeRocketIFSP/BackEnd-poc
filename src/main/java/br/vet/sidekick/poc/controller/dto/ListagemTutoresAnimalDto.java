package br.vet.sidekick.poc.controller.dto;

import br.vet.sidekick.poc.model.Tutor;
import lombok.Getter;

@Getter
public class ListagemTutoresAnimalDto {
    private Long id;
    private String nome;

    public ListagemTutoresAnimalDto(Tutor tutor) {
        this.id = tutor.getId();
        this.nome = tutor.getNome();
    }
}
