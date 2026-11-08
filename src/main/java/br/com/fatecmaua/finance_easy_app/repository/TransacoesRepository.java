package br.com.fatecmaua.finance_easy_app.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fatecmaua.finance_easy_app.model.CategoriaEnum;
import br.com.fatecmaua.finance_easy_app.model.PagEnum;
import br.com.fatecmaua.finance_easy_app.model.TipoEnum;
import br.com.fatecmaua.finance_easy_app.model.Transacoes;
import br.com.fatecmaua.finance_easy_app.projection.TotalPorCategoria;
import br.com.fatecmaua.finance_easy_app.projection.TransacaoUsuarioProjection;

@Repository
public interface TransacoesRepository extends JpaRepository<Transacoes, Long> {

	boolean existsById(Long id);
	
	Optional<Transacoes> findTopByOrderByIdDesc();
	
	List<Transacoes> findByMetodoPagamento(PagEnum metodoPagamento);

	List<Transacoes> findByTipo(TipoEnum tipo);

	List<Transacoes> findByCategoria(CategoriaEnum categoria);

	List<Transacoes> findByDataBetween(LocalDate inicio, LocalDate fim);

	List<Transacoes> findByTipoAndDataBetween(TipoEnum tipo, LocalDate inicio, LocalDate fim);

	List<Transacoes> findByCategoriaAndDataBetween(CategoriaEnum categoria, LocalDate inicio, LocalDate fim);

	List<Transacoes> findByTipoAndCategoria(TipoEnum tipo, CategoriaEnum categoria);
	
	Optional<Transacoes> findTopByUsuarioIdOrderByIdDesc(Long usuarioId);

	List<Transacoes> findByUsuarioId(Long usuarioId, Sort sort);
	
	@Query("from Transacoes t where t.valor >= :valor order by t.valor desc") // HQL
	List<Transacoes> buscarPorValorMinimo(@Param("valor") Double valor);

	@Query(nativeQuery = true,
	       value = "select t.descricao, t.valor, t.tipo, u.nome as usuario "
	             + "from transacao t "
	             + "inner join usuarios u on (t.usuario_id = u.id) "
	             + "where t.valor >= :valor "
	             + "order by t.valor desc")
	List<TransacaoUsuarioProjection> buscarPorValorMinimoComUsuario(@Param("valor") Double valor);

	@Query("select t.usuario.nome as usuario, t.categoria as categoria, sum(t.valor) as total "
		     + "from Transacoes t "
		     + "where t.usuario.id = :usuarioId and t.tipo = :tipo "
		     + "group by t.usuario.nome, t.categoria "
		     + "order by sum(t.valor) desc")
		List<TotalPorCategoria> totalPorCategoria(@Param("usuarioId") Long usuarioId,
		                                          @Param("tipo") TipoEnum tipo);
	
}
