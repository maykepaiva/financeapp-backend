package br.com.financeira.educacao.tcc.service;

import br.com.financeira.educacao.tcc.domain.dto.DespesaDTO;
import br.com.financeira.educacao.tcc.domain.entity.Despesa;
import br.com.financeira.educacao.tcc.domain.entity.Usuario;
import br.com.financeira.educacao.tcc.repository.DespesaRepository;
import br.com.financeira.educacao.tcc.repository.UsuarioRepository;
import br.com.financeira.educacao.tcc.util.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DespesaService {

    private final DespesaRepository despesaRepository;
    private final UsuarioRepository usuarioRepository;
    private final GenericMapper genericMapper;

    public DespesaDTO cadastrar(DespesaDTO despesaDTO) {
        usuarioRepository.findById(despesaDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        Despesa despesa = genericMapper.convertToEntity(despesaDTO,Despesa.class);
        despesa.setId(null);

        return genericMapper.convertToDTO(despesaRepository.save(despesa),DespesaDTO.class);
    }

    public List<DespesaDTO> buscarDespesasPorUsuario(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new RuntimeException("Usuário não encontrado!");
        }
        return genericMapper.convertToDTOList(despesaRepository.findByUsuarioId(usuarioId),DespesaDTO.class);
    }

    public void removerReceita(Long id) {
        if (!despesaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada");
        }
        despesaRepository.deleteById(id);
    }
}

