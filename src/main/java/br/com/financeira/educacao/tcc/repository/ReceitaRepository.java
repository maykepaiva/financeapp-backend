package br.com.financeira.educacao.tcc.repository;

import br.com.financeira.educacao.tcc.domain.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    List<Receita> findByUsuarioId(Long usuarioId);

    @Query("SELECT COALESCE(SUM(r.valor), 0) FROM Receita r WHERE r.usuario.id = :id AND YEAR(r.dataRecebimento) = :ano AND MONTH(r.dataRecebimento) = :mes")
    BigDecimal totalReceitasPorMes(@Param("id") Long id, @Param("ano") int ano, @Param("mes") int mes);

}
