package br.vet.sidekick.poc.controller.dto;

import br.vet.sidekick.poc.model.Animal;
import br.vet.sidekick.poc.model.Prontuario;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ListagemProntuarioDto {
    private Long id;
    private String veterinario;
    private Animal animal;
    private String diagnostico;
    private String observacoes;
    private String medicamento;
    private String medida;
    //private String tipoCirugia;
    //private String asa;
    private String exames;
    private String procedimento;
    private String prescricoes;
    private int quantidade;
//    nome
//    sexo
//    idade
//    pelagem
//    raca

    public ListagemProntuarioDto(Prontuario prontuario) {
        this.id = prontuario.getId();
        this.veterinario = prontuario.getVeterinario().getNome();
        this.animal = prontuario.getAnimal();
        this.diagnostico = prontuario.getDiagnostico();
        this.observacoes = prontuario.getObservacoes();
        this.medicamento = prontuario.getMedicamento();
        this.medida = prontuario.getMedida();
        this.prescricoes = prontuario.getPrescricoesT();
        this.quantidade = prontuario.getQuantidade();

        this.animal.setTutores(null);
    }
}