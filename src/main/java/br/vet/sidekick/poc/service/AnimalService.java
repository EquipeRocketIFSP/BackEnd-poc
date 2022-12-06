package br.vet.sidekick.poc.service;

import br.vet.sidekick.poc.controller.dto.CadastroAnimalDto;
import br.vet.sidekick.poc.model.Animal;
import br.vet.sidekick.poc.model.Tutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AnimalService {
    Optional<Animal> create(CadastroAnimalDto animalDto, Long clinicaId);
    List<Tutor> getAllTutoresByAnimal(Long id);
}
