package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.controller.dto.CadastroAgendamentoDto;
import br.vet.sidekick.poc.model.Agendamento;
import br.vet.sidekick.poc.repository.AgendamentoRepository;
import br.vet.sidekick.poc.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.ArrayList;
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

    private static CadastroAgendamentoDto cadastroAgendamentoDto = CadastroAgendamentoDto.getMock();

    //TODO: remover as duas linhas abaixo
    private static List<CadastroAgendamentoDto> list = new ArrayList<>();
    static {list.add(cadastroAgendamentoDto);}

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

        return ResponseEntity.ok(agendamentoRepository.getOne(id));
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> getAll(){
        if (agendamentoRepository.findAll().isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(agendamentoRepository.findAll());
    }

    //TODO: TESTAR e revisar se há uma forma melhor de fazer o put method
    @PutMapping("/editar/{id}")
    public ResponseEntity<Agendamento> updateAgendamento(
            @PathVariable Long id,
            @RequestBody Agendamento agendamento
    ){
        if (agendamentoRepository.existsById(id)){
            agendamentoRepository.findById(id)
                    .map( updatedAgendamento -> {
                agendamento.setAnimal(updatedAgendamento.getAnimal());
//                agendamento.getAnimal().getClinica().setId(updatedAgendamento.getAnimal().getClinica().getId());
                agendamento.setAnimal(updatedAgendamento.getAnimal());
                agendamento.setDataConsulta(updatedAgendamento.getDataConsulta());
                agendamento.setTipoConsulta(updatedAgendamento.getTipoConsulta());
                agendamentoRepository.save(agendamento);

                return ResponseEntity.ok(agendamento);
            });
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
