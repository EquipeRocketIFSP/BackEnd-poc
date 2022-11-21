package br.vet.sidekick.poc.service.impl;

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
    public Optional<Animal> create(Animal animal) {
        //PRECISAMOS SETAR A LISTA DE TUTORES AQUI NO REGISTRO DE ANIMAL
//        if (animalRepository.getAnimalByTutorCpf())
//            return Optional.empty();

        return Optional.of(animalRepository.save(animal));
    }

    @Override
    public Animal updateAnimal(Animal updateAnimal) {
        Animal animal = animalRepository.getReferenceById(updateAnimal.getId());

        animal.setClinica(updateAnimal.getClinica());
        animal.setEspecie(updateAnimal.getEspecie());
        animal.setFilho(updateAnimal.getFilho());
        animal.setFormaIdentificacao(updateAnimal.getFormaIdentificacao());
        animal.setIdade(updateAnimal.getIdade());
        animal.setMae(updateAnimal.getMae());
        animal.setNome(updateAnimal.getNome());
        animal.setPai(updateAnimal.getPai());
        animal.setPelagem(updateAnimal.getPelagem());
        animal.setRaca(updateAnimal.getRaca());
        animal.setSexo(updateAnimal.getSexo());
        animal.setTutores(updateAnimal.getTutores());

        animal = animalRepository.save(animal);

        return animal;
    }

}
