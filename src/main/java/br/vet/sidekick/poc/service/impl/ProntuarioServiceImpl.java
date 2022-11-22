package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.controller.dto.ProntuarioDto;
import br.vet.sidekick.poc.exceptionResolver.exception.*;
import br.vet.sidekick.poc.model.*;
import br.vet.sidekick.poc.repository.*;
import br.vet.sidekick.poc.service.PdfService;
import br.vet.sidekick.poc.service.ProntuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class ProntuarioServiceImpl implements ProntuarioService {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private PdfService pdfService;

    @Autowired
    private CirurgiaRepository cirurgiaRepository;

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private AnimalRepository animalRepository;


    public Prontuario convert(ProntuarioDto prontuarioDto) {
        log.info("Convertendo o prontuario");
        Clinica clinica = clinicaRepository.getReferenceById(prontuarioDto.getClinica());//.orElseThrow(() -> new ClinicaNotFoundException("Clínica não identificada")))
        Tutor tutor = tutorRepository.getReferenceById(prontuarioDto.getTutor());//.orElseThrow(() -> new TutorNotFoundException("Tutor não identificado")))
//        Cirurgia cirurgia = Cirurgia.builder()
//                .asa(Cirurgia.ASA.valueOf(prontuarioDto.getAsa()))
//                .tipo(Cirurgia.TipoCirurgia.valueOf(prontuarioDto.getTipoCirurgia()))
//                .prontuario(null)
//                .build();


        Veterinario veterinario = veterinarioRepository.getReferenceById(prontuarioDto.getVeterinario());//.orElseThrow(()->new VeterinarioNotFoundException("Veterinário não identificado")))
        veterinario.setProntuarios(null);
        Animal animal = animalRepository.getReferenceById(prontuarioDto.getAnimal());//.orElseThrow(() -> new AnimalNotFoundException("Animal não identificado no sistema")))
        animal.setProntuarios(null);
        return Prontuario.builder()
                .versao(1)
                .clinica(clinica)
                .tutor(tutor)
                .veterinario(veterinario)
                .animal(animal)
                .dataAtendimento(LocalDateTime.now())
                .procedimentos(prontuarioDto.getProcedimentos().stream()
                        .map(proc -> Procedimento.builder()
                                .tipoProcedimento(Procedimento.TipoProcedimento.valueOf(proc.getTipoProcedimento()))
                                .tipo(Procedimento.Tipo.valueOf(proc.getTipo()))
                                .descricao(proc.getDescricao())
                                .build())
                        .collect(Collectors.toList()))
//                .cirurgia(cirurgia)
                .prescricoes(prontuarioDto.getPrescricoes().stream()
                        .map(presc -> Prescricao.builder()
                                .descricao(presc.getMedicacao())
                                .build())
                        .collect(Collectors.toList()))
                .diagnostico(prontuarioDto.getDiagnostico())
                .observacoes(prontuarioDto.getObservacoes())
//                .documentos(getAutorizacoes().stream()
//                        .map(aut -> Documento.builder()
//                                .build())
//                        .collect(Collectors.toList()))
                .prontuarioOrigem(prontuarioDto.getProntuarioOrigem())
                .build();
    }

    @Override
    public Prontuario save(final Prontuario prontuario) throws Exception {
        var now = LocalDateTime.now();
        log.debug("Verificando se o prontuário já existe");
        var pronts = documentoRepository.findAllByVeterinario_IdAndDateCreationBetween(prontuario.getVeterinario().getId(), now.minusSeconds(10), now);
        if(pronts.isPresent() && !pronts.get().isEmpty()){
            throw new ProntuarioAlreadyStartedException(
                    "Já foram identificados os seguintes prontuários: " + pronts.get()
                            .stream()
                            .map(p -> p.getCodigo())
                            .collect(Collectors.toList()));
        }
//        log.info("Persistindo a cirurgia");
//        Cirurgia cirurgia = cirurgiaRepository.save(prontuario.getCirurgia());
//        prontuario.setCirurgia(cirurgia);

        log.info("Iniciando persistência do prontuário");
        Documento doc = Documento.builder()
                .name(Prontuario.createCodigo(now))
                .tipoDocumento("Prontuario")
                .versao(1)
                .criadoEm(LocalDateTime.now())
                .veterinario(prontuario.getVeterinario())
                .clinica(prontuario.getClinica())
                .build();
        Documento tempDoc = documentoRepository.save(doc);
        log.debug("Documento: " + tempDoc);
        var pront = prontuario.setDocumentoDetails(tempDoc);
        Prontuario p = prontuarioRepository.save(pront);
        documentoRepository.save(tempDoc.setProntuario(p));

        log.info("prontuario persistido");
        try {
            pdfService.writeProntuario(p);
            log.info("Pdf de prontuário escrito e persistido");
        } catch (SQLException e){
           log.error("\"Erro de validação SQL do ID da Clínica\": " + e.getMessage());
        }
        return p;
    }

    @Override
    public boolean exists(LocalDateTime dataAtendimento) {
        return prontuarioRepository.existsByDataAtendimento(dataAtendimento);
    }

    @Override
    public Optional<List<Prontuario>> getByCodigo(String codigo) {
        return prontuarioRepository.findAllByCodigo(codigo);
    }

}
