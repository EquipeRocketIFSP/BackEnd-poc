package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.controller.dto.CadastroAnimalDto;
import br.vet.sidekick.poc.model.Animal;
import br.vet.sidekick.poc.repository.AnimalRepository;
import br.vet.sidekick.poc.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public void create(CadastroAnimalDto cadastroAnimalDto) {
        animalRepository.save(Animal.builder()
                    .nome(cadastroAnimalDto.getNome())
                    .idade(cadastroAnimalDto.getIdade())
                    .sexo(cadastroAnimalDto.getSexo())
                    .raca(cadastroAnimalDto.getRaca())
                    .especie(cadastroAnimalDto.getEspecie())
                    .pelagem(cadastroAnimalDto.getPelagem())
                    .tutor(cadastroAnimalDto.getTutor())
                    .mae(cadastroAnimalDto.getMae())
//                    .clinica(cadastroAnimalDto.getClinica())
                    .pai(cadastroAnimalDto.getPai())
                        .build());
    }
}
