package br.vet.sidekick.poc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
public class TipoDocumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoDocumento;
    private String descricao;

    @OneToMany(mappedBy = "id")
    @JsonManagedReference("prontuario_tipoDocumento")
    @ToString.Exclude
    private List<Documento> documento;
}
