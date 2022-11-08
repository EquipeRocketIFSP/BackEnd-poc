package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.controller.dto.ProntuarioDto;
import br.vet.sidekick.poc.model.Prontuario;
import br.vet.sidekick.poc.service.ProntuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;


@RestController
@RequestMapping("/prontuario")
@Slf4j
public class ProntuarioController {

    @Autowired
    private ProntuarioService prontuarioService;

    @PostMapping
    public ResponseEntity<Prontuario> create(
            @RequestBody ProntuarioDto prontuarioDto
    ){
        log.info("Iniciado create de prontuário");
        if(prontuarioService.exists(prontuarioDto.getDataAtendimento())){
            log.info("prontuário já em elaboração");
            return ResponseEntity.badRequest()
                    .header("motivo", "Já existe um item em elaboração no momento. Tente novamente.")
                    .build();
        }
        Prontuario prontuario = prontuarioService.save(prontuarioDto.convert());
        log.info("prontuário salvo");
        return ResponseEntity
                .created(URI.create("/prontuario/" + prontuario.getId()))
                .build();
    }

}
