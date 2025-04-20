package br.com.financeira.educacao.tcc.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestimentoDTO {

        private Long id;

        private BigDecimal valor;

        private LocalDate data;

        private Long metaId;

        private Long armazenamentoId;

        private String armazenamentoname;

}
