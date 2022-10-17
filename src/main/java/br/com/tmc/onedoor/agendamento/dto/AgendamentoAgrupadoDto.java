package br.com.tmc.onedoor.agendamento.dto;


import java.math.BigDecimal;
import java.util.Date;

public interface AgendamentoAgrupadoDto {
    String getAgendamentosId();
    Integer getQuantidadeAgendamentos();
    Date getData();
    BigDecimal getValor();
}
