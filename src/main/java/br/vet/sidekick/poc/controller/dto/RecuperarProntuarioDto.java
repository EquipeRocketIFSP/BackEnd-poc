package br.vet.sidekick.poc.controller.dto;

import br.vet.sidekick.poc.model.Animal;
import br.vet.sidekick.poc.model.Prontuario;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RecuperarProntuarioDto {
    private Long id;
    private String veterinario;
    private String animal;

    public RecuperarProntuarioDto(Prontuario prontuario) {
        this.id = prontuario.getId();
        this.veterinario = prontuario.getVeterinario().getNome();
        this.animal = prontuario.getAnimal().getNome();
    }
}
