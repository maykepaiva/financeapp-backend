package br.com.financeira.educacao.tcc.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetaFinanceiraDTO {

    private Long id;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @Positive(message = "O valor objetivo deve ser positivo")
    private BigDecimal valorObjetivo;

    @NotNull(message = "O prazo inicial é obrigatório")
    private LocalDate prazoInicial;

    @NotNull(message = "O prazo final é obrigatório")
    private LocalDate prazoFinal;

    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioId;

    private Boolean concluida = false;

    private List<InvestimentoDTO> investimentos;

}
