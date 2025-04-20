package br.com.financeira.educacao.tcc.service;

import br.com.financeira.educacao.tcc.domain.dto.ArmazenamentoDTO;
import br.com.financeira.educacao.tcc.domain.entity.Armazenamento;
import br.com.financeira.educacao.tcc.repository.ArmazenamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArmazenamentoService {

    @Autowired
    private ArmazenamentoRepository repository;

    public Armazenamento salvar(ArmazenamentoDTO dto) {
        Armazenamento forma = new Armazenamento();
        forma.setNome(dto.getNome());
        forma.setDescricao(dto.getDescricao());
        return repository.save(forma);
    }

    public List<Armazenamento> listar() {
        return repository.findAll();
    }

    public Optional<Armazenamento> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}

