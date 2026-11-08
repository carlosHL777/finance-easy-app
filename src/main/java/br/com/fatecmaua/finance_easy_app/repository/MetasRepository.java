package br.com.fatecmaua.finance_easy_app.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fatecmaua.finance_easy_app.model.MetaCategoriaEnum;
import br.com.fatecmaua.finance_easy_app.model.MetasFinanceiras;

@Repository
public interface MetasRepository extends JpaRepository<MetasFinanceiras, Long>{
	
	boolean existsById(Long id);
	Optional<MetasFinanceiras> findById(Long id);
	
	@Query(nativeQuery = true,
		       value = "select * from metas order by valor_arrecadado desc limit 1")
	Optional<MetasFinanceiras> metaMaiorArrecadado();

	@Query(nativeQuery = true,
	       value = "select * from metas order by valor_arrecadado asc limit 1")
	Optional<MetasFinanceiras> metaMenorArrecadado();
	
	@Query(nativeQuery = true,
		       value = "select * from metas order by percentual desc limit 1")
	Optional<MetasFinanceiras> maiorPercentual();  
	
	List<MetasFinanceiras> findByCategoria(MetaCategoriaEnum categoria);
	List<MetasFinanceiras> findByDataBetween(LocalDate inicio, LocalDate fim);

}
