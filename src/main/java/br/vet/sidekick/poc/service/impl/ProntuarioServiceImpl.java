package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.model.Documento;
import br.vet.sidekick.poc.model.Prontuario;
import br.vet.sidekick.poc.repository.CirurgiaRepository;
import br.vet.sidekick.poc.repository.DocumentoRepository;
import br.vet.sidekick.poc.repository.ProntuarioRepository;
import br.vet.sidekick.poc.repository.TutorRepository;
import br.vet.sidekick.poc.service.PdfService;
import br.vet.sidekick.poc.service.ProntuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProntuarioServiceImpl implements ProntuarioService {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    @Autowired
    private CirurgiaRepository cirurgiaRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private PdfService pdfService;

    @Override
    public Prontuario save(Prontuario prontuario) {
        Date now = new Date();
//        return prontuarioRepository.save(prontuario);
        log.debug("Iniciando persistência do prontuário");
        Documento doc = Documento.builder()
                .name(Prontuario.createCodigo(LocalDateTime.now()))
                .tipoDocumento("Prontuario")
                .versao(1)
                .criadoEm(now)
                .veterinario(prontuario.getVeterinario())
                .clinica(prontuario.getClinica()
                        .getId()
                )
                .build();
        log.debug("doc criado: " + doc);
        Documento tempDoc = documentoRepository.save(doc);
        log.debug("doc persistido");
        var pront = prontuario.setDocumentoDetails(tempDoc);
        log.debug("Prontuario atualizado");
        Prontuario p = prontuarioRepository.save(pront);
        log.debug("prontuario persistido");
        documentoRepository.save(tempDoc.setProntuario(p));

        log.debug("documento persistido");
        try {
            pdfService.writeProntuario(p);
            log.debug("Pdf de prontuário escrito e persistido");
        } catch (SQLException e){
            log.error("\"Erro de validação SQL do ID da Clínica\": " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    @Override
    public boolean exists(LocalDateTime dataAtendimento) {
        return prontuarioRepository.existsByDataAtendimento(dataAtendimento);
    }

    @Override
    public Optional<String> findByCertvetProntuario(String certvetCode) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Prontuario>> getByCodigo(String codigo) {
        return prontuarioRepository.findAllByCodigo(codigo);
    }
}
