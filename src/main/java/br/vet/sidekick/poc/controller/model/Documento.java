package br.vet.sidekick.poc.controller.model;

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
    @Column(name = "id_documento", nullable = false)
    private Long id;

    @Column(name = "id_prontuario")
    private Long idProntuario;

    @Column(name = "caminho_arquivo", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private String caminhoArquivo;

    @Column(name = "clinica", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Long clinica;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @Column(name = "tipo_documento", nullable = false)
    @NotBlank(message = "Valor não pode ser nulo ou em branco")
    private Long tipoDocumento;

}
