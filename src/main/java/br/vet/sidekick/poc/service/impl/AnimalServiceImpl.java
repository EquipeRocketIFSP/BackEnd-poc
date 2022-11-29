package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.controller.dto.CadastroAnimalDto;
import br.vet.sidekick.poc.model.Animal;
import br.vet.sidekick.poc.repository.AnimalRepository;
import br.vet.sidekick.poc.repository.TutorRepository;
import br.vet.sidekick.poc.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Override
    public Optional<Animal> create(CadastroAnimalDto animalDto, Long clinicaId) {

        Animal animal = convert(animalDto, clinicaId);
        //PRECISAMOS SETAR A LISTA DE TUTORES AQUI NO REGISTRO DE ANIMAL
//        if (animalRepository.getAnimalByTutorCpf())
//            return Optional.empty();

        return Optional.of(animalRepository.save(animal));
    }

    private Animal convert(CadastroAnimalDto animalDto, Long clinicaId) {
        return Animal.builder()
                .nome(animalDto.getNome())
                .idade(animalDto.getIdade())
                .sexo(animalDto.getSexo())
                .raca(animalDto.getRaca())
                .especie(animalDto.getEspecie())
                .pelagem(animalDto.getPelagem())
                .clinica(clinicaId)
                .tutores(
                        animalDto.getTutores().stream()
                                .map(t -> tutorRepository.findById(t).get())
                                .collect(Collectors.toList())
                )
                .build();
    }

}
