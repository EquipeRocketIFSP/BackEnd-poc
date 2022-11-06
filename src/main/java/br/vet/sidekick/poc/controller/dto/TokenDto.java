package br.vet.sidekick.poc.controller.dto;

import br.vet.sidekick.poc.model.Veterinario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
//@Setter
//@AllArgsConstructor
@Builder
public class TokenDto {
    private String token;
    private String type;
    private String nome;
    private String crmv;

//    public TokenDto(String token, String type){
//        this.token = token;
//        this.token = type;
//    }
}
