package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.controller.dto.CadastroClinicaDto;
import br.vet.sidekick.poc.exceptionResolver.exception.FuncionarioAlreadyExistsException;
import br.vet.sidekick.poc.model.Clinica;
import br.vet.sidekick.poc.model.Funcionario;
import br.vet.sidekick.poc.repository.FuncionarioRepository;
import br.vet.sidekick.poc.service.FuncionarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class FuncionarioServiceImpl implements FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public Boolean authenticate(String username, String password) {
        return funcionarioRepository.existsByUsernameAndPassword(username, password);
    }

    @Override
    public Funcionario get(Funcionario funcionario) {
        return funcionarioRepository.getByUsername(funcionario.getUsername());
    }

    @Override
    public Optional<Funcionario> create(Funcionario funcionario) {
        return funcionarioRepository.existsByUsername(funcionario.getUsername())
                ? Optional.empty()
                : Optional.of(funcionarioRepository.save(funcionario));
    }

    @Override
    public Funcionario save(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }

    @Override
    public void deleteById(Long id){
        funcionarioRepository.deleteById(id);
    }

    @Override
    public Funcionario createDonoClinica(CadastroClinicaDto cadastro, Clinica clinica) throws RuntimeException {
        log.debug("Checando existência de funcionario");
        if (funcionarioRepository.existsByCpfAndClinica_id(cadastro.getDonoCpf(), clinica.getId())) {
            throw new FuncionarioAlreadyExistsException("Funcionário já cadastrado");
        }
        log.debug("Convertendo Prontuário");
        var funcionario = Funcionario.builder()
                .clinica(clinica)
                .nome(cadastro.getDonoNome())
                .celular(cadastro.getDonoCelular())
                .logradouro(cadastro.getDonoLogradouro())
                .numero(cadastro.getDonoNumero())
                .estado(cadastro.getDonoEstado())
                .rg(cadastro.getDonoRg())
                .cep(cadastro.getDonoCep())
                .cidade(cadastro.getDonoCidade())
                .email(cadastro.getDonoEmail())
                .cpf(cadastro.getDonoCpf())
                .username(cadastro.getDonoEmail())
                .password(cadastro.getDonoSenha())
                .bairro(cadastro.getDonoBairro())
                .telefone(cadastro.getDonoTelefone())
                .build();
        log.debug("Persistindo funcionário");
        try {
            return funcionarioRepository.save(funcionario);
        }finally {
            log.debug("Funcionário persistido");
        }
    }
}