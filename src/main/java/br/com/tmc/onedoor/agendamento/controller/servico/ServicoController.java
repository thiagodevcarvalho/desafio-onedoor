package br.com.tmc.onedoor.agendamento.controller.servico;

import br.com.tmc.onedoor.agendamento.model.servico.Servico;
import br.com.tmc.onedoor.agendamento.service.servico.ServicoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/servicos")
@Api(tags="Serviço")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping
    public ResponseEntity<List<Servico>> listar() {
        return ResponseEntity.ok(servicoService.listarServicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servicoService.buscarServicoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizar(@PathVariable Long id, @Valid @RequestBody Servico servico) {
        Servico servicoSalvo = servicoService.atualizar(id,servico);
        return ResponseEntity.ok(servicoSalvo);
    }

    @PostMapping
    public ResponseEntity<Servico> criar(@Valid @RequestBody Servico servico) {
        Servico novoServico = servicoService.salvar(servico);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoServico);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        servicoService.deletar(id);
    }
}
