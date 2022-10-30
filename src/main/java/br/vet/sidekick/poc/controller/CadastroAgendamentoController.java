package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.controller.dto.CadastroAgendamentoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController("/cadastro-agendamento")
@CrossOrigin
public class CadastroAgendamentoController {

    private CadastroAgendamentoDto cadastroAgendamentoDto = CadastroAgendamentoDto.getMock();
    
    List<CadastroAgendamentoDto> list = Arrays.asList(cadastroAgendamentoDto);

    @PostMapping
    public ResponseEntity<CadastroAgendamentoDto> registerAgendamento(
            @RequestBody CadastroAgendamentoDto agendamento
    ){
        list.add(agendamento);
        return ResponseEntity.created(
                URI.create("/agendamento/" + String.valueOf(list.indexOf(agendamento))
                )).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<CadastroAgendamentoDto> getOne(
            @RequestParam Integer id
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
