package br.vet.sidekick.poc.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_prontuario")
    private Long idProntuario;

    @Column(name = "caminho_arquivo", nullable = false)
    private String caminhoArquivo;

    @Column(name = "clinica", nullable = false)
    private Long clinica;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @Column(name = "tipo_documento", nullable = false)
    private Long tipoDocumento;

}
