package br.com.financeira.educacao.tcc.repository;

import br.com.financeira.educacao.tcc.domain.entity.Investimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface InvestimentoRepository extends JpaRepository<Investimento, Long> {
    List<Investimento> findByMetaId(Long metaId);
    @Query("""
    SELECT COALESCE(SUM(i.valor), 0)
    FROM Investimento i
    WHERE i.meta.usuario.id = :id
      AND YEAR(i.data) = :ano
      AND MONTH(i.data) = :mes""")
    BigDecimal totalInvestimentoPorMes(@Param("id") Long id, @Param("ano") int ano, @Param("mes") int mes);


    @Query("SELECT i FROM Investimento i WHERE i.meta.usuario.id = :usuarioId")
    List<Investimento> findByMetaUsuarioId(@Param("usuarioId") Long usuarioId);

}
