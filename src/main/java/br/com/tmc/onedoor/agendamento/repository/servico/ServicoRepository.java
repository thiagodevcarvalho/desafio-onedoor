package br.com.tmc.onedoor.agendamento.repository.servico;

import br.com.tmc.onedoor.agendamento.model.servico.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}