package br.com.tmc.onedoor.agendamento.repository.cliente;

import br.com.tmc.onedoor.agendamento.model.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
