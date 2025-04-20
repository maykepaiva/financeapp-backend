package br.com.financeira.educacao.tcc.service;

import br.com.financeira.educacao.tcc.domain.dto.ReceitaDTO;
import br.com.financeira.educacao.tcc.domain.entity.Receita;
import br.com.financeira.educacao.tcc.domain.entity.Usuario;
import br.com.financeira.educacao.tcc.repository.ReceitaRepository;
import br.com.financeira.educacao.tcc.repository.UsuarioRepository;
import br.com.financeira.educacao.tcc.util.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceitaService {

    private final ReceitaRepository receitaRepository;
    private final UsuarioRepository usuarioRepository;
    private final GenericMapper genericMapper;

    public ReceitaDTO cadastrar(ReceitaDTO receitaDTO) {
        usuarioRepository.findById(receitaDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        Receita receita = genericMapper.convertToEntity(receitaDTO, Receita.class);
        receita.setId(null);
        return genericMapper.convertToDTO(receitaRepository.save(receita),ReceitaDTO.class);
    }

    public List<ReceitaDTO> buscarReceitasPorUsuario(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new RuntimeException("Usuário não encontrado!");
        }

        return genericMapper.convertToDTOList(receitaRepository.findByUsuarioId(usuarioId),ReceitaDTO.class);
    }

    public void removerReceita(Long id) {
        if (!receitaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada");
        }
        receitaRepository.deleteById(id);
    }
}

