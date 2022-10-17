package br.com.tmc.onedoor.agendamento.model.agendamento;

import br.com.tmc.onedoor.agendamento.model.cliente.Cliente;
import br.com.tmc.onedoor.agendamento.model.servico.Servico;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamento")
@NoArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NotNull
    @Getter @Setter
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHora;

    @Getter @Setter
    private String observacao;

    @Getter @Setter
    @OneToOne(cascade=CascadeType.ALL)
    private Cliente cliente;

    @Getter @Setter
    @OneToOne(cascade=CascadeType.ALL)
    private Servico servico;

}
