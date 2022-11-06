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
    public Optional<Veterinario> findByEmail(String email) {
        return veterinarioRepository.findByEmail(email);
    }
}
