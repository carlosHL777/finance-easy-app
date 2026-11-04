package br.com.fatecmaua.finance_easy_app.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fatecmaua.finance_easy_app.model.CategoriaEnum;
import br.com.fatecmaua.finance_easy_app.model.PagEnum;
import br.com.fatecmaua.finance_easy_app.model.TipoEnum;
import br.com.fatecmaua.finance_easy_app.model.Transacoes;

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
	
}
