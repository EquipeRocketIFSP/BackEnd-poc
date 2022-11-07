package br.vet.sidekick.poc.controller.dto;

import br.vet.sidekick.poc.model.Veterinario;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RequiredArgsConstructor(staticName = "of")
public class TokenDto {

    @NonNull private String token;
    @NonNull private String type;
    private String nome;
    private String crmv;

}
