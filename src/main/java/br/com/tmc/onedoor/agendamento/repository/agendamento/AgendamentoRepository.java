package br.com.tmc.onedoor.agendamento.repository.agendamento;

import br.com.tmc.onedoor.agendamento.dto.AgendamentoAgrupadoDto;
import br.com.tmc.onedoor.agendamento.dto.AgendamentoSumarizacaoDto;
import br.com.tmc.onedoor.agendamento.model.agendamento.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    @Query(value = "select group_concat(a.id) as agendamentosId, count(a.id) as quantidadeAgendamentos , " +
            "cast(a.data_hora as date) as data, s.valor as valor " +
            "from agendamento as a , servico as s where a.servico_id = s.id " +
            "group by cast(a.data_hora as date ) , s.valor" ,
            nativeQuery = true)
    List<AgendamentoAgrupadoDto> listarAgendamentosAgrupadosPorDataValor();

    @Query(value = "select sum(s.valor) as valorTotal , a.cliente_id as clienteId , c.nome as cliente , " +
            "cast(a.data_hora as date) as data from " +
            "servico as s , agendamento as a , cliente as c  where a.servico_id = s.id and a.cliente_id = c.id " +
            " and (coalesce(cast(:dataHora as datetime), null) is null or a.data_hora=cast(:dataHora as datetime)) " +
            " and (coalesce(cast(:clienteId as bigint), null) is null or cliente_id=cast(:clienteId as bigint))" +
            "group by cast(a.data_hora as date ) , a.cliente_id ,  c.nome " ,
            nativeQuery = true)
    List<AgendamentoSumarizacaoDto> sumarizar(@Param("dataHora") String dataHora , @Param("clienteId") String clienteId );
}
