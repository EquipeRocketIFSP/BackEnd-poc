package br.vet.sidekick.poc.controller.dto;

import br.vet.sidekick.poc.model.Funcionario;
import lombok.Getter;

import java.util.Optional;

@Getter
public class ListagemFuncionarioDto {
    private final Long id;
    private final String nome;
    private Optional<String> crmv;

    public ListagemFuncionarioDto(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.crmv = Optional.empty();
    }

    public void setCrmv(String crmv) {
        this.crmv = Optional.of(crmv);
    }
}
