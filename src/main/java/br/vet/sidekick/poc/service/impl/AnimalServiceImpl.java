package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.controller.dto.CadastroAnimalDto;
import br.vet.sidekick.poc.exceptionResolver.exception.AnimalNotFound;
import br.vet.sidekick.poc.model.Animal;
import br.vet.sidekick.poc.model.Clinica;
import br.vet.sidekick.poc.model.Tutor;
import br.vet.sidekick.poc.repository.AnimalRepository;
import br.vet.sidekick.poc.repository.TutorRepository;
import br.vet.sidekick.poc.service.AnimalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
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

    @Override
    public List<Tutor> getAllTutoresByAnimal(Long id) {
        Optional<Animal> responseAnimal = this.animalRepository.findById(id);

        if (responseAnimal.isEmpty())
            throw new AnimalNotFound();

        return responseAnimal.get().getTutores();
    }

    private Animal convert(CadastroAnimalDto animalDto, Long clinicaId) {
        log.info("Animal: " + animalDto);
        return Animal.builder()
                .nome(animalDto.getNome())
                .idade(animalDto.getIdade())
                .sexo(animalDto.getSexo())
                .raca(animalDto.getRaca())
                .especie(animalDto.getEspecie())
                .pelagem(animalDto.getPelagem())
                .clinica(Clinica.builder().id(clinicaId).build())
                .formaIdentificacao(animalDto.getFormaIdentificacao() == null ? animalDto.getFormaIdentificacao() : "Sem dados")
                .tutores(
                        animalDto.getTutores().stream()
                                .map(t -> tutorRepository.findById(t).get())
                                .collect(Collectors.toList())
                )
                .build();
    }

}
