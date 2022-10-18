package br.vet.sidekick.poc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
@CrossOrigin
@Slf4j
public class HiController {
    @GetMapping
    public ResponseEntity<String> response(){
        String sucesso = "Requição com sucesso!!";
        log.info(sucesso);
        return ResponseEntity.ok(sucesso);
    }
}
