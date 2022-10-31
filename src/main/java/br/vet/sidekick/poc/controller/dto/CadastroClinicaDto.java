package br.vet.sidekick.poc.controller.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class CadastroClinicaDto {
    @JsonProperty("clinica-nome-fantasia")
    private String clinicaNome;

    @JsonProperty("clinica-razao-social")
    private String cliniaRazao;

    @JsonProperty("clinica-cnpj")
    private String clinicaCnpj;

    @JsonProperty("clinica-cnae")
    private String clinicaCnae;

    @JsonProperty("clinica-cep")
    private String clinicaCep;

    @JsonProperty("clinica-logradouro")
    private String clinicaLogradouro;

    @JsonProperty("clinica-numero")
    private String clinicaNumero;

    @JsonProperty("clinica-bairro")
    private String clinicaBairro;

    @JsonProperty("clinica-cidade")
    private String clinicaCidade;

    @JsonProperty("clinica-estado")
    private String clinicaEstado;

    @JsonProperty("clinica-celular")
    private String clinicaCelular;

    @JsonProperty("clinica-telefone")
    private String clinicaTelefone;

    @JsonProperty("clinica-email")
    private String clinicaEmail;

    @JsonProperty("dono-nome-fantasia")
    private String donoNome;

    @JsonProperty("dono-cpf")
    private String donoCpf;

    @JsonProperty("dono-rg")
    private String donoRg;

    @JsonProperty("dono-cep")
    private String donoCep;

    @JsonProperty("dono-logradouro")
    private String donoLogradouro;

    @JsonProperty("dono-numero")
    private String donoNumero;

    @JsonProperty("dono-bairro")
    private String donoBairro;

    @JsonProperty("dono-cidade")
    private String donoCidade;

    @JsonProperty("dono-estado")
    private String donoEstado;

    @JsonProperty("dono-celular")
    private String donoCelular;

    @JsonProperty("dono-telefone")
    private String donoTelefone;

    @JsonProperty("dono-email")
    private String donoEmail;

    @JsonProperty("dono-senha")
    private String donoSenha;

    @JsonProperty("tecnico-nome")
    private String tecnicNome;

    @JsonProperty("tecnico-crmv")
    private String tecnicoCrmv;

    @JsonProperty("tecnico-cpf")
    private String tecnicoCpf;

    @JsonProperty("tecnico-rg")
    private String tecnicoRg;

    @JsonProperty("tecnico-cep")
    private String tecnicoCep;

    @JsonProperty("tecnico-logradouro")
    private String tecnicoLogradouro;

    @JsonProperty("tecnico-numero")
    private String tecnicoNumero;

    @JsonProperty("tecnico-bairro")
    private String tecnicoBairro;

    @JsonProperty("tecnico-cidade")
    private String tecnicoCidade;

    @JsonProperty("tecnico-estado")
    private String tecnicoEstado;

    @JsonProperty("tecnico-celular")
    private String tecnicoCelular;

    @JsonProperty("tecnico-telefone")
    private String tecnicoTelefone;

    @JsonProperty("tecnico-email")
    private String tecnicoEmail;

    @JsonProperty("tecnico-senha")
    private String tecnicoSenha;


    public static CadastroClinicaDto getMock() {
        return CadastroClinicaDto.builder()
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
                .clinicaNumero("635")
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
                .tecnicoNumero("815")
                .tecnicoRg("21.702.952-8")
                .tecnicoSenha("afqUXsB4mY")
                .build();
    }
}
