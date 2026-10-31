package br.com.fatecmaua.finance_easy_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fatecmaua.finance_easy_app.model.Transacoes;

@Repository
public interface TransacoesRepository extends JpaRepository<Transacoes, Long> {

	boolean existsById(Long id);
	
	Optional<Transacoes> findTopByOrderByIdDesc();
}
