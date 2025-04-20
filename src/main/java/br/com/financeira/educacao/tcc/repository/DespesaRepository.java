package br.com.financeira.educacao.tcc.repository;

import br.com.financeira.educacao.tcc.domain.entity.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findByUsuarioId(Long usuarioId);

    @Query("SELECT COALESCE(SUM(d.valor), 0) FROM Despesa d WHERE d.usuario.id = :id AND YEAR(d.dataPagamento) = :ano AND MONTH(d.dataPagamento) = :mes")
    BigDecimal totalDespesasPorMes(@Param("id") Long id, @Param("ano") int ano, @Param("mes") int mes);

}

