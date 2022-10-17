package br.com.tmc.onedoor.agendamento.service.agendamento;

import br.com.tmc.onedoor.agendamento.dto.AgendamentoAgrupadoDto;
import br.com.tmc.onedoor.agendamento.dto.AgendamentoSumarizacaoDto;
import br.com.tmc.onedoor.agendamento.model.agendamento.Agendamento;
import br.com.tmc.onedoor.agendamento.model.cliente.Cliente;
import br.com.tmc.onedoor.agendamento.model.servico.Servico;
import br.com.tmc.onedoor.agendamento.repository.agendamento.AgendamentoRepository;
import br.com.tmc.onedoor.agendamento.repository.cliente.ClienteRepository;
import br.com.tmc.onedoor.agendamento.repository.servico.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    public List<Agendamento> listarAgendamentos() {
        return agendamentoRepository.findAll();
    }

    public Agendamento buscarAgendamentoPorId(Long id) {
        Optional<Agendamento> agendamentoSalvo = agendamentoRepository.findById(id);

        if(!agendamentoSalvo.isPresent()) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado");
        }

        return agendamentoSalvo.get();
    }

    public List<AgendamentoAgrupadoDto> listarAgendamentosAgrupadoPorDataValor() {
        return agendamentoRepository.listarAgendamentosAgrupadosPorDataValor();
    }

    public List<AgendamentoSumarizacaoDto> sumarizar(String dataHora, String clienteId) {
        String dataFiltro = dataHora != null ? dataHora : null;
        String clienteIdFiltro = clienteId != null ? clienteId : null;
        return agendamentoRepository.sumarizar(dataFiltro, clienteIdFiltro);
    }

    public Agendamento atualizar(Long id, Agendamento novoAgendamento) {
        Optional<Agendamento> agendamentoSalvo = agendamentoRepository.findById(id);

        if(!agendamentoSalvo.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado");
        }

        agendamentoSalvo.get().setObservacao(novoAgendamento.getObservacao());
        agendamentoSalvo.get().setDataHora(novoAgendamento.getDataHora());

        return agendamentoRepository.save(agendamentoSalvo.get());
    }

    public Agendamento remarcar(Long id, Agendamento novoAgendamento) {
        Optional<Agendamento> agendamentoSalvo = agendamentoRepository.findById(id);

        if(!agendamentoSalvo.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado");
        }

        agendamentoSalvo.get().setDataHora(novoAgendamento.getDataHora());

        return agendamentoRepository.save(agendamentoSalvo.get());
    }

    public Agendamento salvar(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    public Agendamento salvarPorReferenciaClienteServico(Agendamento agendamento, Long clienteId, Long servicoId) {
        Optional<Cliente> clienteSalvo = clienteRepository.findById(clienteId);
        Optional<Servico> servicoSalvo = servicoRepository.findById(servicoId);

        if(!clienteSalvo.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }

        if(!servicoSalvo.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado");
        }

        agendamento.setCliente(clienteSalvo.get());
        agendamento.setServico(servicoSalvo.get());

        return agendamentoRepository.save(agendamento);
    }

    public void deletar(Long id) {
        Optional<Agendamento> agendamentoSalvo = agendamentoRepository.findById(id);

        if(!agendamentoSalvo.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado");
        }

        agendamentoRepository.delete(agendamentoSalvo.get());
    }
}
