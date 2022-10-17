package br.com.tmc.onedoor.agendamento.model.cliente;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cliente")
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NotBlank
    @Getter @Setter
    private String nome;

    @NotBlank
    @Size(min = 11 , max = 11)
    @Getter @Setter
    private String cpf;

}
