package br.vet.sidekick.poc.controller.dto;

import br.vet.sidekick.poc.model.Animal;
import java.util.List;
import java.util.stream.Collectors;

public class AnimalResponse {

    private Long id;
    private Long clinica;
    private String especie;
    private List<Long> filho;
    private String formaIdentificacao;
    private Integer idade;
    private Animal mae;
    private String nome;
    private Animal pai;
    private String pelagem;
    private String raca;
    private String sexo;

    private List<TutorResponse> tutores;
    public AnimalResponse(Animal animal) {
        this.id = animal.getId();
        this.clinica = animal.getClinica();
        this.especie = animal.getEspecie();
        this.filho = animal.getFilho().stream().map(Animal::getId).collect(Collectors.toList());
        this.formaIdentificacao = animal.getFormaIdentificacao();
        this.idade = animal.getIdade();
        this.mae = animal.getMae();
        this.nome = animal.getNome();
        this.pai = animal.getPai();
        this.pelagem = animal.getPelagem();
        this.raca = animal.getRaca();
        this.sexo = animal.getSexo();
        this.tutores = animal.getTutores().stream().map(TutorResponse::new).collect(Collectors.toList());

    }
}
