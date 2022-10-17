package br.com.tmc.onedoor.agendamento.service.servico;

import br.com.tmc.onedoor.agendamento.model.servico.Servico;
import br.com.tmc.onedoor.agendamento.repository.servico.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> listarServicos() {

        return servicoRepository.findAll();
    }

    public Servico buscarServicoPorId(Long id) {
        Optional<Servico> servicoSalvo = servicoRepository.findById(id);

        if(!servicoSalvo.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado");
        }

        return servicoSalvo.get();
    }

    public Servico atualizar(Long id, Servico novoServico) {
        Optional<Servico> servicoSalvo = servicoRepository.findById(id);

        if(!servicoSalvo.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado");
        }

        servicoSalvo.get().setCodigo(novoServico.getCodigo());
        servicoSalvo.get().setDescricao(novoServico.getDescricao());
        servicoSalvo.get().setValor(novoServico.getValor());

        return servicoRepository.save(servicoSalvo.get());
    }

    public Servico salvar(Servico servico) {
        return servicoRepository.save(servico);
    }

    public void deletar(Long id) {
        Optional<Servico> servicoSalvo = servicoRepository.findById(id);

        if(!servicoSalvo.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado");
        }

        servicoRepository.delete(servicoSalvo.get());
    }
}
