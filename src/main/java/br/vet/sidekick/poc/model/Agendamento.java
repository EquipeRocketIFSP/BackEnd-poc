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

    @ManyToOne
    @JoinColumn(name = "animal_id", insertable = false, updatable = false)
    private Animal animal;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @Column(name = "data_consulta")
    private LocalDateTime dataConsulta;

    @Column(name = "tipo_consulta")
    private String tipoConsulta;

}
