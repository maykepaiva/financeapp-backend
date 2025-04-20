package br.com.financeira.educacao.tcc.service;

import br.com.financeira.educacao.tcc.domain.dto.RelatorioDTO;
import br.com.financeira.educacao.tcc.repository.DespesaRepository;
import br.com.financeira.educacao.tcc.repository.InvestimentoRepository;
import br.com.financeira.educacao.tcc.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RelatorioService {
    @Autowired
    private ReceitaRepository receitaRepository;
    @Autowired
    private DespesaRepository despesaRepository;
    @Autowired
    private InvestimentoRepository investimentoRepository;

    public List<RelatorioDTO> gerarResumoMensal(Long usuarioId) {
        List<RelatorioDTO> resumos = new ArrayList<>();

        YearMonth agora = YearMonth.now();

        for (int i = 0; i < 12; i++) {
            YearMonth mes = agora.minusMonths(i);
            int ano = mes.getYear();
            int mesValor = mes.getMonthValue();

            BigDecimal totalReceitas = receitaRepository. totalReceitasPorMes(usuarioId, ano, mesValor);
            BigDecimal totalDespesas = despesaRepository.totalDespesasPorMes(usuarioId, ano, mesValor);
            BigDecimal totalInvestimento = investimentoRepository.totalInvestimentoPorMes(usuarioId, ano, mesValor);
            RelatorioDTO resumo = new RelatorioDTO(mes, totalReceitas, totalDespesas, totalInvestimento);
            resumos.add(resumo);
        }

        resumos.sort(Comparator.comparing(RelatorioDTO::getMes));
        return resumos;
    }

}
