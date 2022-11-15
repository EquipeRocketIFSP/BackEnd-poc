package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.controller.dto.CadastroClinicaDto;
import br.vet.sidekick.poc.model.Clinica;
import br.vet.sidekick.poc.repository.ClinicaRepository;
import br.vet.sidekick.poc.service.ClinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ClinicaServiceImpl implements ClinicaService {

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Override
    public Optional<Clinica> create(CadastroClinicaDto cadastro) {
        Clinica clinica = Clinica.builder()
                .razaoSocial("Mário e Edson Marketing ME")
                .bairro("Bairro Fictício")
                .celular("(19) 99435-8082")
                .cep("13060-587")
                .cidade("Campinas")
                .cnae("7500-1/00")
                .cnpj("69.136.908/0001-23")
                .email("diretoria@marioeedsonmarketingme.com.br")
                .estado("SP")
                .logradouro("Avenida Gilberto Targon")
                .nomeFantasia("Clinica Ficticia")
                .numero("635")
                .responsavelTecnico("Nome Qualquer")
                .telefone("(19) 3713-9577").build();
        //está verificando sempre o cnpj do DTO, mudar para o da request
        if (clinicaRepository.existsByCnpj(clinica.getCnpj())) {
            return Optional.empty();
        }
        return Optional.of(clinicaRepository.save(clinica));
    }
}
