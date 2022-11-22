package br.vet.sidekick.poc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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
    private Long id;

//    @OneToMany(mappedBy = "id") Illegal attempt to map a non collection as a @OneToMany, @ManyToMany or @CollectionOfElements: br.vet.sidekick.poc.model.Agendamento.animal
    @ManyToOne
    @JsonBackReference("animal_agendamentos")
    private Animal animal;

    @ManyToOne
    @JsonBackReference("clinica_agendamentos")
    private Clinica clinica;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date criadoEm;

    private LocalDateTime dataConsulta;

    private String tipoConsulta;

}
