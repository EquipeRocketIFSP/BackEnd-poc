package br.vet.sidekick.poc.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String tipoAutorizacao;
    private String caminhoArquivo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "prontuario_id", nullable = false)
    @ToString.Exclude
    private Prontuario prontuario;

}
