package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.exceptionResolver.exception.FuncionarioAlreadyExistsException;
import br.vet.sidekick.poc.model.Clinica;
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
    public Optional<Veterinario> findByEmail(String email) {
        return veterinarioRepository.findByEmail(email);
    }

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
    public Optional<Veterinario> createResponsavelTecnico(String crmv, Clinica clinica) throws FuncionarioAlreadyExistsException {
        Optional<Veterinario> resp = veterinarioRepository.findByCrmvAndClinica__id(crmv, clinica.getId());
        // TODO: Finalizar implementação
        return Optional.empty();
    }
}
