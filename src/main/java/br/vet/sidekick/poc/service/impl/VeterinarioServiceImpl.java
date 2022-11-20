package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.controller.dto.CadastroClinicaDto;
import br.vet.sidekick.poc.exceptionResolver.exception.FuncionarioAlreadyExistsException;
import br.vet.sidekick.poc.exceptionResolver.exception.ResponsavelTecnicoAlreadyExistsException;
import br.vet.sidekick.poc.model.Clinica;
import br.vet.sidekick.poc.model.Funcionario;
import br.vet.sidekick.poc.model.Veterinario;
import br.vet.sidekick.poc.repository.VeterinarioRepository;
import br.vet.sidekick.poc.service.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VeterinarioServiceImpl implements VeterinarioService {
    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Override
    public Boolean authenticate(String username, String password) {
        return veterinarioRepository.existsByUsernameAndPassword(username, password);
    }

    @Override
    public Veterinario get(Veterinario funcionario) {
        return veterinarioRepository.getByUsername(funcionario.getUsername());
    }

    @Override
    public Optional<Veterinario> create(Veterinario funcionario) {
        return veterinarioRepository.existsByUsername(funcionario.getUsername())
                ? Optional.empty()
                : Optional.of(veterinarioRepository.save(funcionario));
    }

    @Override
    public Veterinario save(Veterinario funcionario){
        return veterinarioRepository.save(funcionario);
    }

    @Override
    public void deleteById(Long id){
        veterinarioRepository.deleteById(id);
    }

    @Override
    public Optional<Veterinario> createResponsavelTecnico(CadastroClinicaDto cadastro, Clinica clinica) throws ResponsavelTecnicoAlreadyExistsException {
        if(veterinarioRepository.existsByCrmvAndClinica__id(cadastro.getTecnicoCrmv(), clinica.getId())){
            throw new ResponsavelTecnicoAlreadyExistsException("O responsável Técnico para a clínica já está cadastrado.");
        }
        Veterinario preVet = new Veterinario(Funcionario.builder()
                .clinica(clinica)
                .cpf(cadastro.getDonoCpf())
                .bairro(cadastro.getTecnicoBairro())
                .celular(cadastro.getTecnicoCelular())
                .cep(cadastro.getTecnicoCep())
                .cidade(cadastro.getTecnicoCidade())
                .email(cadastro.getTecnicoEmail())
                .rg(cadastro.getTecnicoRg())
                .estado(cadastro.getTecnicoEstado())
//                .nome(cadastro.)
                .build());

        Veterinario vet = veterinarioRepository.save(preVet);
        // TODO: Finalizar implementação
        return Optional.empty();
    }
}
