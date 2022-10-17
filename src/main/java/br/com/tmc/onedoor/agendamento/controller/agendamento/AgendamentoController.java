package br.com.tmc.onedoor.agendamento.controller.agendamento;

import br.com.tmc.onedoor.agendamento.dto.AgendamentoAgrupadoDto;
import br.com.tmc.onedoor.agendamento.dto.AgendamentoSumarizacaoDto;
import br.com.tmc.onedoor.agendamento.model.agendamento.Agendamento;
import br.com.tmc.onedoor.agendamento.service.agendamento.AgendamentoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@Api(tags="Agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public ResponseEntity<List<Agendamento>> listar() {
        return ResponseEntity.ok(agendamentoService.listarAgendamentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(agendamentoService.buscarAgendamentoPorId(id));
    }

    @GetMapping("/agrupados")
    public ResponseEntity<List<AgendamentoAgrupadoDto>> listarAgrupados() {
        return ResponseEntity.ok(agendamentoService.listarAgendamentosAgrupadoPorDataValor());
    }

    @GetMapping("/sumarizacao")
    public ResponseEntity<List<AgendamentoSumarizacaoDto>> sumarizar(
            @RequestParam(required = false) String dataHora, @RequestParam(required = false) String clienteId) {
        return ResponseEntity.ok(agendamentoService.sumarizar(dataHora, clienteId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizar(@PathVariable Long id, @Valid @RequestBody Agendamento agendamento) {
        Agendamento agendamentoSalvo = agendamentoService.atualizar(id,agendamento);
        return ResponseEntity.ok(agendamentoSalvo);
    }

    @PutMapping("/{id}/remarcar")
    public ResponseEntity<Agendamento> remarcar(@PathVariable  Long id, @Valid @RequestBody Agendamento agendamento) {
        Agendamento agendamentoSalvo = agendamentoService.remarcar(id,agendamento);
        return ResponseEntity.ok(agendamentoSalvo);
    }

    @PostMapping
    public ResponseEntity<Agendamento> criar(@Valid @RequestBody Agendamento agendamento) {
        Agendamento novoAgendamento = agendamentoService.salvar(agendamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAgendamento);
    }

    @PostMapping("/cliente/{clienteId}/servico/{servicoId}")
    public ResponseEntity<Agendamento> criarComReferenciaClienteServico(
            @Valid @RequestBody Agendamento agendamento,
            @PathVariable Long clienteId,
            @PathVariable Long servicoId
    ) {
        Agendamento novoAgendamento = agendamentoService.salvarPorReferenciaClienteServico(
                agendamento,
                clienteId,
                servicoId
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAgendamento);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        agendamentoService.deletar(id);
    }

}
