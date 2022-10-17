package br.com.tmc.onedoor.agendamento.dto;


import java.math.BigDecimal;

public interface AgendamentoSumarizacaoDto {
    BigDecimal getValorTotal();
    Integer getClienteId();
    String getCliente();
    String getData();
}
