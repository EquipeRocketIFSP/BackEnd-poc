package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.exceptionResolver.exception.ProntuarioAlreadyStartedException;
import br.vet.sidekick.poc.model.Documento;
import br.vet.sidekick.poc.model.Prontuario;
import br.vet.sidekick.poc.repository.DocumentoRepository;
import br.vet.sidekick.poc.repository.ProntuarioRepository;
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
    @Override
    public Prontuario save(final Prontuario prontuario) throws Exception {
        var now = LocalDateTime.now();
        var pronts = documentoRepository.findByVeterinario_IdAndDateCreationBetween(now.minusSeconds(10), now, prontuario.getVeterinario().getId());
        if(pronts.isPresent() && !pronts.get().isEmpty()){
            throw new ProntuarioAlreadyStartedException(
                    "Já foram identificados os seguintes prontuários: " + pronts.get()
                            .stream()
                            .map(p -> p.getCodigo())
                            .collect(Collectors.toList()));
        }
        Prontuario p = prontuarioRepository.save(
                prontuario.setDocumentoDetails(
                        documentoRepository.save(
                                Documento.builder()
                                        .name(Prontuario.createCodigo(now))
                                        .tipoDocumento("Prontuario")
                                        .versao(1)
                                        .build()
                        )
                )
        );
        try {
            pdfService.writeProntuario(p);
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
