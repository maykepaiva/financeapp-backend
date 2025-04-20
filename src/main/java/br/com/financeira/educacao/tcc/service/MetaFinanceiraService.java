package br.com.financeira.educacao.tcc.service;

import br.com.financeira.educacao.tcc.domain.dto.InvestimentoDTO;
import br.com.financeira.educacao.tcc.domain.dto.MetaFinanceiraDTO;
import br.com.financeira.educacao.tcc.domain.entity.Armazenamento;
import br.com.financeira.educacao.tcc.domain.entity.Investimento;
import br.com.financeira.educacao.tcc.domain.entity.MetaFinanceira;
import br.com.financeira.educacao.tcc.domain.entity.Usuario;
import br.com.financeira.educacao.tcc.repository.ArmazenamentoRepository;
import br.com.financeira.educacao.tcc.repository.InvestimentoRepository;
import br.com.financeira.educacao.tcc.repository.MetaFinanceiraRepository;
import br.com.financeira.educacao.tcc.repository.UsuarioRepository;
import br.com.financeira.educacao.tcc.util.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MetaFinanceiraService {

    private final MetaFinanceiraRepository metaFinanceiraRepository;
    private final UsuarioRepository usuarioRepository;
    private final GenericMapper genericMapper;
    private final InvestimentoRepository investimentoRepository;
    private final ArmazenamentoRepository armazenamentoRepository;

    public MetaFinanceira cadastrar(MetaFinanceiraDTO metaDTO) {
        Usuario usuario = usuarioRepository.findById(metaDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        MetaFinanceira meta = new MetaFinanceira();
        meta.setDescricao(metaDTO.getDescricao());
        meta.setValorObjetivo(metaDTO.getValorObjetivo());
        meta.setPrazoInicial(metaDTO.getPrazoInicial());
        meta.setPrazoFinal(metaDTO.getPrazoFinal());
        meta.setConcluida(false);
        meta.setUsuario(usuario);

        return metaFinanceiraRepository.save(meta);
    }

    public List<MetaFinanceiraDTO> listarPorUsuario(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new RuntimeException("Usuário não encontrado!");
        }
        List<MetaFinanceira> metas = metaFinanceiraRepository.findByUsuarioId(usuarioId);
        List<Armazenamento> armazenamentos = armazenamentoRepository.findAll();
        metas.forEach(meta -> {
            List<Investimento> investimentos = investimentoRepository.findByMetaId(meta.getId());
            meta.setInvestimentos(investimentos);
        });
        var response = genericMapper.convertToDTOList(metas,MetaFinanceiraDTO.class);

        response.forEach(meta -> {
            meta.getInvestimentos().forEach(investimento -> {
                Armazenamento armazenamento = armazenamentos.stream()
                        .filter(a -> a.getId().equals(investimento.getArmazenamentoId()))
                        .findFirst()
                        .orElse(null);
                investimento.setArmazenamentoname(armazenamento.getNome());

            });
        });
        return  response;
    }

    public List<MetaFinanceira> listarPorMetasUsuario(Long usuarioId) {
       return metaFinanceiraRepository.findByUsuarioId(usuarioId);
    }

    public void deletar(Long id) {
        metaFinanceiraRepository.deleteById(id);
    }
}

