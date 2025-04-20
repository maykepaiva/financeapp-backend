package br.com.financeira.educacao.tcc.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceitaDTO {
    private String id;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @Positive(message = "O valor deve ser positivo")
    private BigDecimal valor;

    @NotNull(message = "A data de recebimento é obrigatória")
    private LocalDate dataRecebimento;

    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioId;

}
