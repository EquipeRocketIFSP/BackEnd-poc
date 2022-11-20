package br.vet.sidekick.poc.service.impl;

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
}
