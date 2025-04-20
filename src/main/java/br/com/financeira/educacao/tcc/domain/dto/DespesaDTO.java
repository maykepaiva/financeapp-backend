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
public class DespesaDTO {

    private String id;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @Positive(message = "O valor deve ser positivo")
    private BigDecimal valor;

    @NotNull(message = "A data de pagamento é obrigatória")
    private LocalDate dataPagamento;

    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioId;

    private Boolean pago;
}

