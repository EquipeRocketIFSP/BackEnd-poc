package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.model.Funcionario;
import br.vet.sidekick.poc.repository.FuncionarioRepository;
import br.vet.sidekick.poc.service.IFuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService implements IFuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public Boolean authenticate(String username, String password) {
        return funcionarioRepository.existsByUserNameAndPassword(username, password);
    }

    @Override
    public Funcionario get(Funcionario funcionario) {
        return funcionarioRepository.getByUserName(funcionario.getUsername());
    }
}
