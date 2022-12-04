package br.vet.sidekick.poc.controller;

import br.vet.sidekick.poc.controller.dto.CadastroAgendamentoDto;
import br.vet.sidekick.poc.controller.dto.RecuperarAgendamentoDto;
import br.vet.sidekick.poc.model.Agendamento;
import br.vet.sidekick.poc.repository.AgendamentoRepository;
import br.vet.sidekick.poc.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agendamento")
@CrossOrigin
public class AgendamentoController extends BaseController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @PostMapping
    public ResponseEntity<Agendamento> registerAgendamento(@RequestBody CadastroAgendamentoDto agendamentoDto) {
        try {
            Agendamento agendamento = this.agendamentoService.create(agendamentoDto);

            return ResponseEntity.created(URI.create("/agendamento/" + agendamento.getId())).build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatus()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecuperarAgendamentoDto> getOne(@PathVariable Long id) {
        try {
            Agendamento agendamento = this.agendamentoService.getOne(id);
            RecuperarAgendamentoDto agendamentoDto = new RecuperarAgendamentoDto(
                    agendamento.getAnimal().getNome(),
                    agendamento.getDataConsulta(),
                    agendamento.getCriadoEm(),
                    agendamento.getTipoConsulta()
            );

            return ResponseEntity.ok(agendamentoDto);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatus()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List> getAll(@RequestParam(value = "consultas-marcadas", required = false) boolean consultasMarcadas) {
        if (consultasMarcadas)
            return ResponseEntity.ok(this.agendamentoService.getScheduleConsultsDates());

        return ResponseEntity.ok(agendamentoRepository.findAll());
    }

    //TODO: TESTAR e revisar se há uma forma melhor de fazer o put method / criar função de upddate no service
    @PutMapping("/editar/{id}")
    public ResponseEntity<Agendamento> updateAgendamento(
            @PathVariable Long id,
            @RequestBody Agendamento agendamento
    ) {
        Agendamento referenceAgendamento = agendamentoRepository.getReferenceById(id);

        if (referenceAgendamento != null) {
            referenceAgendamento.setAnimal(agendamento.getAnimal());
            referenceAgendamento.setClinica(agendamento.getClinica());
            referenceAgendamento.setCriadoEm(agendamento.getCriadoEm());
            referenceAgendamento.setDataConsulta(agendamento.getDataConsulta());
            referenceAgendamento.setTipoConsulta(agendamento.getTipoConsulta());
            agendamentoRepository.save(referenceAgendamento);

            return ResponseEntity.ok(referenceAgendamento);
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Agendamento> deleteAgendamento(
            @PathVariable Long id
    ) {
        if (agendamentoRepository.existsById(id)) {
            agendamentoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
