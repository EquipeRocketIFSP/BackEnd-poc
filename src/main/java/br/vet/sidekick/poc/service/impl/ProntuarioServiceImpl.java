/*
package br.vet.sidekick.poc.service.impl;

import br.vet.sidekick.poc.model.Cirurgia;
import br.vet.sidekick.poc.model.Prontuario;
import br.vet.sidekick.poc.repository.CirurgiaRepository;
import br.vet.sidekick.poc.repository.ProntuarioRepository;
import br.vet.sidekick.poc.repository.TutorRepository;
import br.vet.sidekick.poc.service.ProntuarioService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class ProntuarioServiceImpl implements ProntuarioService {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    @Autowired
    private CirurgiaRepository cirurgiaRepository;

    @Autowired
    private TutorRepository tutorRepository;
    @Override
    public Prontuario save(Prontuario prontuario) {
        return prontuarioRepository.save(
                prontuario
                        .setCirurgia(cirurgiaRepository.save(prontuario.getCirurgia())))
                .setTutor(tutorRepository.findById(prontuario.getTutor().getId()).orElseThrow(() -> new RuntimeException("Tutor nao identificado")));
    }

    @Override
    public boolean exists(LocalDateTime dataAtendimento) {
        return prontuarioRepository.existsByDataAtendimento(dataAtendimento);
    }
}
*/
