package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.controller.dto.CadastroClinicaDto;
import br.vet.sidekick.poc.exceptionResolver.exception.ResponsavelTecnicoAlreadyExistsException;
import br.vet.sidekick.poc.model.Clinica;
import br.vet.sidekick.poc.model.Veterinario;
import br.vet.sidekick.poc.repository.VeterinarioRepository;
import br.vet.sidekick.poc.service.VeterinarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
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
    public Veterinario createResponsavelTecnico(CadastroClinicaDto cadastro, Clinica clinica) throws ResponsavelTecnicoAlreadyExistsException {
        log.info("Criando novo responsável técnico com crmv: " + cadastro.getTecnicoCrmv());
        if(veterinarioRepository.existsByRegistroCRMVAndClinica_id(cadastro.getTecnicoCrmv(), clinica.getId())){
            throw new ResponsavelTecnicoAlreadyExistsException("Já existe um responsável técnico para a clínica.");
        }
        log.debug("Parsing para veterinario");
//        Veterinario preVet =
//                new Veterinario(Funcionario.builder()
//                .clinica(clinica)
//                .cpf(cadastro.getDonoCpf())
//                .bairro(cadastro.getTecnicoBairro())
//                .celular(cadastro.getTecnicoCelular())
//                .cep(cadastro.getTecnicoCep())
//                .cidade(cadastro.getTecnicoCidade())
//                .email(cadastro.getTecnicoEmail())
//                .rg(cadastro.getTecnicoRg())
//                .estado(cadastro.getTecnicoEstado())
//                .nome(cadastro.getTecnicNome())
//                .password(cadastro.getTecnicoSenha())
//                .logradouro(cadastro.getTecnicoLogradouro())
//                .username(cadastro.getTecnicoEmail())
//                .numero(cadastro.getTecnicoNumero())
//                .build());
//        preVet.setRegistroCRMV(cadastro.getTecnicoCrmv());
        Veterinario preVet = Veterinario.builder()
                .clinica(clinica)
                .cpf(cadastro.getDonoCpf())
                .bairro(cadastro.getTecnicoBairro())
                .celular(cadastro.getTecnicoCelular())
                .cep(cadastro.getTecnicoCep())
                .cidade(cadastro.getTecnicoCidade())
                .email(cadastro.getTecnicoEmail())
                .rg(cadastro.getTecnicoRg())
                .estado(cadastro.getTecnicoEstado())
                .nome(cadastro.getTecnicNome())
                .password(new BCryptPasswordEncoder().encode(cadastro.getTecnicoSenha()))
                .logradouro(cadastro.getTecnicoLogradouro())
                .username(cadastro.getTecnicoEmail())
                .numero(cadastro.getTecnicoNumero())
                .registroCRMV(cadastro.getTecnicoCrmv())
                .build();
        log.debug("Veterinário parsed: " + preVet);
        try {
            return veterinarioRepository.save(preVet);
        } finally {
            log.debug("Persistido");
        }
    }
}
