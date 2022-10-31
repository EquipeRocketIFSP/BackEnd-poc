package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.controller.dto.CadastroClinicaDto;
import br.vet.sidekick.poc.service.ClinicaService;
import br.vet.sidekick.poc.service.FuncionarioService;
import br.vet.sidekick.poc.service.VeterinarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/cadastro-clinica")
@CrossOrigin
@Slf4j
public class CadastroClinicaController {
    private static CadastroClinicaDto cadastroDto = CadastroClinicaDto.builder()
            .cliniaRazao("Clinica Ficticia")
            .clinicaBairro("Bairro Fictício")
            .clinicaCelular("(19) 99435-8082")
            .clinicaCep("13060-587")
            .clinicaCidade("Campinas")
            .clinicaCnae("7500-1/00")
            .clinicaCnpj("69.136.908/0001-23")
            .clinicaEmail("diretoria@marioeedsonmarketingme.com.br")
            .clinicaEstado("SP")
            .clinicaLogradouro("Avenida Gilberto Targon")
            .clinicaNome("Mário e Edson Marketing ME")
//            .clinicaNumero("635")
            .clinicaTelefone("(19) 3713-9577")
            .donoBairro("Velame")
            .donoCelular("(83) 98920-8496")
            .donoCep("58420-290")
            .donoCidade("Campina Grande")
            .donoCpf("759.755.587-35")
            .donoEmail("sarah_cristiane_moreira@fundasa.com.br")
            .donoEstado("PB")
            .donoLogradouro("Rua Frutuoso Maria Vasconcelos")
            .donoNome("Sarah Cristiane Moreira")
            .donoNumero("862")
            .donoRg("28.563.840-3")
            .donoSenha("PESYZIQkaM")
            .donoTelefone("(83) 2999-8303")
            .tecnicNome("Alessandra Bruna Jennifer Nunes")
            .tecnicoCep("49097-350")
            .tecnicoBairro("Ponto Novo")
            .tecnicoCelular("(79) 99211-7227")
            .tecnicoCidade("Aracaju")
            .tecnicoCpf("698.385.927-81")
            .tecnicoCrmv("SE-123456")
            .tecnicoEmail("alessandra-nunes93@torah.com.br")
            .tecnicoEstado("SE")
            .tecnicoLogradouro("Travessa José Lemos")
//            .tecnicoNumero("815")
            .tecnicoRg("21.702.952-8")
            .tecnicoSenha("afqUXsB4mY")
            .build();

    @Autowired
    ClinicaService clinicaService;

    @Autowired
    VeterinarioService veterinarioService;

    @Autowired
    FuncionarioService funcionarioService;
    private List<CadastroClinicaDto> cadastros = Arrays.asList(cadastroDto);

    @PostMapping
    public ResponseEntity<CadastroClinicaDto> registerClinica(
            @RequestBody CadastroClinicaDto cadastro){
        cadastros.add(cadastro);
        clinicaService.create(cadastro);
//        veterinarioService.cadastrar(cadastro);
//        funcionarioService.create(cadastro);
        return ResponseEntity.ok(cadastro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CadastroClinicaDto> getOne(@PathVariable Long id){
        return id != 1L
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(cadastroDto);
    }

    @GetMapping
    public ResponseEntity<List<CadastroClinicaDto>> getAll(){
        return ResponseEntity.ok(cadastros);
    }
}
