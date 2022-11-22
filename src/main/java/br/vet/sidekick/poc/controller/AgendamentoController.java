package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.model.Agendamento;
import br.vet.sidekick.poc.repository.AgendamentoRepository;
import br.vet.sidekick.poc.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agendamento")
@CrossOrigin
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @PostMapping
    public ResponseEntity<Agendamento> registerAgendamento(
            @RequestBody Agendamento agendamento
    ){
        Optional<Agendamento> referenceAgendamento = agendamentoService.create(agendamento);
        if (referenceAgendamento.isEmpty())
            return ResponseEntity.badRequest().build();

        return ResponseEntity.created(
                URI.create("/agendamento/" + agendamento.getId().toString())
        ).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> getOne(
            @PathVariable Long id
    ){
        Optional<Agendamento> referenceAgendamento = agendamentoRepository.findById(id);
        if (referenceAgendamento.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(agendamentoRepository.getReferenceById(id));
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> getAll(){
        List<Agendamento> agendamentos = agendamentoRepository.findAll();

        return ResponseEntity.ok(agendamentos);
    }

    //TODO: Refatorar regra respeitando as camadas
    @PutMapping("/editar/{id}")
    public ResponseEntity<Agendamento> updateAgendamento(
            @PathVariable Long id,
            @RequestBody Agendamento updateAgendamento
    ){
        Optional<Agendamento> referenceAgendamento = agendamentoRepository.findById(id);

        if (referenceAgendamento.isPresent()){
            return referenceAgendamento
                    .map(agendamento -> {
                        agendamento.setAnimal(updateAgendamento.getAnimal());
                        agendamento.setDataConsulta(updateAgendamento.getDataConsulta());
                        agendamento.setTipoConsulta(updateAgendamento.getTipoConsulta());
                        agendamento = agendamentoRepository.save(agendamento);
                        return ResponseEntity.ok(agendamento);
                    })
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Agendamento> deleteAgendamento(
            @PathVariable Long id
    ){
        if (agendamentoRepository.existsById(id)){
            agendamentoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
