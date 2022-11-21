package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.controller.dto.AgendamentoDto;
import br.vet.sidekick.poc.model.Agendamento;
import br.vet.sidekick.poc.model.Animal;
import br.vet.sidekick.poc.model.Clinica;
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
            @RequestBody AgendamentoDto agendamentoDto
    ){
        Optional<Agendamento> agendamento = agendamentoService.create(agendamentoDto);
        return agendamento.isEmpty()
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.created(URI.create("/agendamento/" + agendamento.get().getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> getOne(
            @PathVariable Long id
    ){
        Optional<Agendamento> referenceAgendamento = agendamentoRepository.findById(id);
        return referenceAgendamento.isEmpty()
                ?  ResponseEntity.notFound().build()
                : ResponseEntity.ok(agendamentoRepository.getReferenceById(id));
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> getAll(){
        List<Agendamento> agendamentos = agendamentoRepository.findAll();

        return ResponseEntity.ok(agendamentos);
    }

    //TODO: TESTAR e revisar se há uma forma melhor de fazer o put method / criar função de upddate no service
    @PutMapping("/editar/{id}")
    public ResponseEntity<Agendamento> updateAgendamento(
            @PathVariable Long id,
            @RequestBody AgendamentoDto agendamento
    ){
        Agendamento referenceAgendamento = agendamentoRepository.getReferenceById(id);

        if (referenceAgendamento == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                agendamentoRepository.save(
                        Agendamento.builder()
                                .id(referenceAgendamento.getId())
                                .animal(Animal.builder().id(agendamento.getAnimal()).build())
                                .clinica(Clinica.builder().id(agendamento.getClinica()).build())
                                .criadoEm(agendamento.getCriadoEm())
                                .dataConsulta(agendamento.getDataConsulta())
                                .tipoConsulta(agendamento.getTipoConsulta())
                                .build()
                )
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Agendamento> deleteAgendamento(
            @PathVariable Long id
    ){
        if(!agendamentoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        agendamentoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
