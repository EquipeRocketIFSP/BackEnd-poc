package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.controller.dto.CadastroAgendamentoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/cadastro-agendamento")
@CrossOrigin
public class CadastroAgendamentoController {

    private static CadastroAgendamentoDto cadastroAgendamentoDto = CadastroAgendamentoDto.getMock();

    private static List<CadastroAgendamentoDto> list = new ArrayList<>();
    static {list.add(cadastroAgendamentoDto);}

    @PostMapping
    public ResponseEntity<CadastroAgendamentoDto> registerAgendamento(
            @RequestBody CadastroAgendamentoDto agendamento
    ){
        list.add(agendamento);
        return ResponseEntity.created(
                URI.create("/agendamento/" + String.valueOf(list.indexOf(agendamento))
                )).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CadastroAgendamentoDto> getOne(
            @PathVariable Integer id
    ){
        CadastroAgendamentoDto agendamento = list.get(id);
        return agendamento != null
                ? ResponseEntity.ok(agendamento)
                : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<CadastroAgendamentoDto>> getAll(){
        return list.size() == 0
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(list);
    }

}
