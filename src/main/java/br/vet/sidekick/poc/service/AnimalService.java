package br.vet.sidekick.poc.service;

import br.vet.sidekick.poc.controller.dto.CadastroAnimalDto;
import org.springframework.stereotype.Service;

@Service
public interface AnimalService {
    void create(CadastroAnimalDto cadastroAnimalDto);
}
