package br.com.financeira.educacao.tcc.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.YearMonth;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioDTO {
    private YearMonth mes;
    private BigDecimal totalReceitas;
    private BigDecimal totalDespesas;
    private BigDecimal totalInvestimentos;
    private BigDecimal saldo;

    public RelatorioDTO(YearMonth mes, BigDecimal receitas, BigDecimal despesas) {
        this.mes = mes;
        this.totalReceitas = receitas;
        this.totalDespesas = despesas;
        this.totalInvestimentos = BigDecimal.ZERO;
        this.saldo = receitas.subtract(despesas);
    }

    public RelatorioDTO(YearMonth mes, BigDecimal receitas, BigDecimal despesas, BigDecimal investimentos) {
        this.mes = mes;
        this.totalReceitas = receitas;
        this.totalDespesas = despesas;
        this.totalInvestimentos = investimentos;
        this.saldo = receitas.subtract(despesas);
    }
}

