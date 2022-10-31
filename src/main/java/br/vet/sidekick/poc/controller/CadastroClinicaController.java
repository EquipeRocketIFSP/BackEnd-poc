package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.controller.dto.CadastroClinicaDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/cadastro-clinica")
@CrossOrigin
@Slf4j
public class CadastroClinicaController {
    private static CadastroClinicaDto cadastroDto = CadastroClinicaDto.getMock();

    private static List<CadastroClinicaDto> cadastros = new ArrayList<>();
    static {cadastros.add(cadastroDto);}

    @PostMapping
    public ResponseEntity<CadastroClinicaDto> registerClinica(
            @RequestBody CadastroClinicaDto cadastro){
        cadastros.add(cadastro);
        return ResponseEntity.created(URI.create("1")).build();
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
