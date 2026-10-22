package br.com.fatecmaua.finance_easy_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fatecmaua.finance_easy_app.model.Transacoes;

public interface FinanceEasyTransacoes extends JpaRepository<Transacoes, Long>{

}
