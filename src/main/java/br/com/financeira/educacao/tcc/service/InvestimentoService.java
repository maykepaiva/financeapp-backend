package br.com.financeira.educacao.tcc.service;

import br.com.financeira.educacao.tcc.domain.dto.InvestimentoDTO;
import br.com.financeira.educacao.tcc.domain.entity.Armazenamento;
import br.com.financeira.educacao.tcc.domain.entity.Investimento;
import br.com.financeira.educacao.tcc.domain.entity.MetaFinanceira;
import br.com.financeira.educacao.tcc.repository.ArmazenamentoRepository;
import br.com.financeira.educacao.tcc.repository.InvestimentoRepository;
import br.com.financeira.educacao.tcc.repository.MetaFinanceiraRepository;
import br.com.financeira.educacao.tcc.util.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestimentoService {

    private final InvestimentoRepository investimentoRepository;
    private final MetaFinanceiraRepository metaRepository;
    private final ArmazenamentoRepository armazenamentoRepository;
    private final GenericMapper genericMapper;

    public InvestimentoDTO salvar(InvestimentoDTO investimento, Long metaId, Long armazenamentoId) {

        MetaFinanceira meta = metaRepository.findById(metaId)
                .orElseThrow(() -> new IllegalArgumentException("Meta não encontrada"));

        Armazenamento armazenamento = armazenamentoRepository.findById(armazenamentoId)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de armazenamento não encontrado"));

        investimento.setMetaId(meta.getId());
        investimento.setArmazenamentoId(armazenamento.getId());

        Investimento request = genericMapper.convertToEntity(investimento,Investimento.class);
        InvestimentoDTO response = genericMapper.convertToDTO(investimentoRepository.save(request),InvestimentoDTO.class);

        BigDecimal soma = investimentoRepository
                .findByMetaId(metaId)
                .stream()
                .map(Investimento::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        MetaFinanceira metaFinanceira = metaRepository.findById(metaId).orElseThrow();

        boolean concluida = soma.compareTo(metaFinanceira.getValorObjetivo()) >= 0;
        if (concluida){
            meta.setConcluida(true);
            metaRepository.save(meta);
        }
        return response;
    }

    public List<InvestimentoDTO> listarPorMeta(Long metaId) {
        return genericMapper.convertToDTOList(investimentoRepository.findByMetaId(metaId), InvestimentoDTO.class);
    }

    public void deletar(Long id) {
        investimentoRepository.deleteById(id);
    }

    public List<InvestimentoDTO> listarTodosInvestimentos() {
        return genericMapper.convertToDTOList(investimentoRepository.findAll(), InvestimentoDTO.class);
    }
    public List<InvestimentoDTO> listarPorUsuario(Long usuarioId) {
        List<Armazenamento> armazenamentos = armazenamentoRepository.findAll();
        List<Investimento> investimentos = investimentoRepository.findByMetaUsuarioId(usuarioId);
        List<InvestimentoDTO> response =  genericMapper.convertToDTOList(investimentoRepository.findByMetaUsuarioId(usuarioId), InvestimentoDTO.class);

        response.forEach(investimento -> {
            Armazenamento armazenamento = armazenamentos.stream()
                    .filter(a->a.getId().equals(investimento.getArmazenamentoId()))
                    .findFirst()
                    .orElse(null);
            assert armazenamento != null;
            investimento.setArmazenamentoname(armazenamento.getNome());
        });
        return response;
    }

}
