package br.vet.sidekick.poc.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

//    @OneToMany(mappedBy = "id") Illegal attempt to map a non collection as a @OneToMany, @ManyToMany or @CollectionOfElements: br.vet.sidekick.poc.model.Agendamento.animal
    @Column(name = "animal_id")
    private Long animal;

    @Column(name = "clinica_id")
    private Long clinica;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @Column(name = "data_consulta")
    private LocalDateTime dataConsulta;

    @Column(name = "tipo_consulta")
    private String tipoConsulta;

}
