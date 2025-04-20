package br.com.financeira.educacao.tcc.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArmazenamentoDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    private String descricao;
}
