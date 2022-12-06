package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.model.*;
import br.vet.sidekick.poc.repository.*;
import br.vet.sidekick.poc.service.PdfService;
import br.vet.sidekick.poc.service.ProntuarioService;
import br.vet.sidekick.poc.repository.impl.S3BucketServiceRepository;
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

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public Prontuario save(Prontuario prontuario) {
        log.info("prontuario: " + prontuario);
        Date now = new Date();
        String codigo = Prontuario.createCodigo(LocalDateTime.now());
//        return prontuarioRepository.save(prontuario);
        prontuario.setCodigo(codigo);
        Optional<Clinica> clinica = clinicaRepository.findById(prontuario.getClinica().getId());
        prontuario.setClinica(clinica.get());

        Optional<Tutor> tutor = tutorRepository.findById(prontuario.getTutor().getId());
        prontuario.setTutor(tutor.get());

        List<Animal> animais = animalRepository.findAllByTutores_idAndNome(tutor.get().getId(), prontuario.getAnimal().getNome());

        Optional<Animal> animal = animalRepository.findByTutores_idAndNome(tutor.get().getId(), prontuario.getAnimal().getNome());
        prontuario.setAnimal(animal.get());

        log.info("Iniciando persistência do prontuário");
        Documento doc = Documento.builder()
                .name(codigo)
                .tipoDocumento(tipoDocumentoRepository.findByDescricao("Prontuario"))
                .versao(1)
                .criadoEm(now)
                .veterinario(prontuario.getVeterinario())
                .clinica(clinica.get())
                .caminhoArquivo("/" + S3BucketServiceRepository.getConventionedBucketName(prontuario.getClinica().getCnpj()) + "/" + prontuario.getCodigo()+".pdf")
                .build();
//        log.info("doc a ser persistido: " + doc);
        Documento tempDoc = documentoRepository.save(doc);
        log.info("doc persistido");
        Prontuario p = prontuario.setDocumentoDetails(tempDoc);
        log.info("Prontuario atualizado");
        p = prontuarioRepository.save(p);
        log.info("prontuario persistido");
        documentoRepository.save(tempDoc.setProntuario(p));
        log.info("documento persistido");
        try {
            pdfService.writeProntuario(p);
            log.info("Processo de gravação de PDF finalizado");
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

    @Override
    public Optional<Prontuario> findById(Long id) {
        return prontuarioRepository.findById(id);
    }
}
