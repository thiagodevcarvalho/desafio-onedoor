package br.com.tmc.onedoor.agendamento.service.cliente;

import br.com.tmc.onedoor.agendamento.model.cliente.Cliente;
import br.com.tmc.onedoor.agendamento.repository.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {

        return clienteRepository.findAll();
    }

    public Cliente buscarClientePorId(Long id) {
        Optional<Cliente> clienteSalvo = clienteRepository.findById(id);

        if(!clienteSalvo.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }

        return clienteSalvo.get();
    }

    public Cliente atualizar(Long id, Cliente novoCliente) {
        Optional<Cliente> clienteSalvo = clienteRepository.findById(id);

        if(!clienteSalvo.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }

        clienteSalvo.get().setNome(novoCliente.getNome());
        clienteSalvo.get().setCpf(novoCliente.getCpf());

        return clienteRepository.save(clienteSalvo.get());
    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deletar(Long id) {
        Optional<Cliente> clienteSalvo = clienteRepository.findById(id);

        if(!clienteSalvo.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }

        clienteRepository.delete(clienteSalvo.get());
    }
}
