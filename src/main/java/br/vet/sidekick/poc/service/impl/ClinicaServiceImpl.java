package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.controller.dto.CadastroClinicaDto;
import br.vet.sidekick.poc.controller.model.Clinica;
import br.vet.sidekick.poc.repository.ClinicaRepository;
import br.vet.sidekick.poc.service.ClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicaServiceImpl implements ClinicaService {
    @Autowired
    private ClinicaRepository clinicaRepository;
    @Override
    public void cadastrar(CadastroClinicaDto cadastroClinicaDto) {
        clinicaRepository.save(Clinica.builder()
                        .bairro(cadastroClinicaDto.getClinicaBairro())
                        .celular(cadastroClinicaDto.getClinicaCelular())
                .build());
    }
}
