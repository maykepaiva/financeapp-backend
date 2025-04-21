package br.com.financeira.educacao.tcc.repository;

import br.com.financeira.educacao.tcc.domain.entity.MetaFinanceira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MetaFinanceiraRepository extends JpaRepository<MetaFinanceira, Long> {
    List<MetaFinanceira> findByUsuarioId(Long usuarioId);

}
