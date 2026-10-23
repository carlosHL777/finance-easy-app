package br.com.fatecmaua.finance_easy_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fatecmaua.finance_easy_app.model.MetasFinanceiras;

public interface MetasRepository extends JpaRepository<MetasFinanceiras, Long>{

}
