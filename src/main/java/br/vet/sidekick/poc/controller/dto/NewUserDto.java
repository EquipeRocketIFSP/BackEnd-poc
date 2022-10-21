package br.vet.sidekick.poc.controller.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUserDto {
    private String email;
    private String password;
}
