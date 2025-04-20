package br.com.financeira.educacao.tcc.repository;

import br.com.financeira.educacao.tcc.domain.entity.Armazenamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArmazenamentoRepository extends JpaRepository<Armazenamento, Long> {
    Optional<Armazenamento> findByNome(String nome);
}