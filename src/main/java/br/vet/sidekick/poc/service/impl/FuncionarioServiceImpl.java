package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.model.Funcionario;
import br.vet.sidekick.poc.repository.FuncionarioRepository;
import br.vet.sidekick.poc.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public Boolean authenticate(String username, String password) {
        var f = funcionarioRepository.getByUsername(username);
        System.out.println(f);
        var f2 = funcionarioRepository.existsByUsernameAndPassword(username, password);
        System.out.println(f);
        return f2;
    }

    @Override
    public Funcionario get(Funcionario funcionario) {
        var f = funcionarioRepository.getByUsername(funcionario.getUsername());
        System.out.println(f);
        return f;
    }
}