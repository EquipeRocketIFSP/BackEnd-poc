package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.controller.dto.CadastroClinicaDto;
import br.vet.sidekick.poc.exceptionResolver.exception.ClinicaAlreadyExistsException;
import br.vet.sidekick.poc.model.Clinica;
import br.vet.sidekick.poc.repository.ClinicaRepository;
import br.vet.sidekick.poc.service.ClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicaServiceImpl implements ClinicaService {

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Override
    public Optional<Clinica> create(CadastroClinicaDto cadastro) throws RuntimeException{
        if (clinicaRepository.existsByCnpj(cadastro.getClinicaCnpj())) {
            throw new ClinicaAlreadyExistsException("Clinica já cadastrada");
        }
        return Optional.of(clinicaRepository.save(
                Clinica.builder()
                .razaoSocial(cadastro.getCliniaRazao())
                .bairro(cadastro.getClinicaBairro())
                .celular(cadastro.getClinicaCelular())
                .cep(cadastro.getClinicaCep())
                .cidade(cadastro.getClinicaCidade())
                .cnae(cadastro.getClinicaCnae())
                .cnpj(cadastro.getClinicaCnpj())
                .email(cadastro.getClinicaEmail())
                .estado(cadastro.getClinicaEstado())
                .logradouro(cadastro.getClinicaLogradouro())
                .nomeFantasia(cadastro.getClinicaNome())
                .numero(cadastro.getClinicaNumero())
                .responsavelTecnico(cadastro.getTecnicoCpf())
                .telefone(cadastro.getTecnicoTelefone())
                .donoCpf(cadastro.getDonoCpf())
                .build()
        ));
    }

    @Override
    public Optional<Clinica> getById(Long id) {
        return clinicaRepository.findById(id);
    }

    @Override
    public List<Clinica> getAll() {
        return clinicaRepository.findAll();
    }
}
