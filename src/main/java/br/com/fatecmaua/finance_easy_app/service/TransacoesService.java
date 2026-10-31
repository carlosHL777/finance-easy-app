package br.com.fatecmaua.finance_easy_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fatecmaua.finance_easy_app.model.TipoEnum;
import br.com.fatecmaua.finance_easy_app.model.Transacoes;
import br.com.fatecmaua.finance_easy_app.repository.TransacoesRepository;

@Service
public class TransacoesService {

	@Autowired
	private final TransacoesRepository repTran;
	
	public TransacoesService(TransacoesRepository repTran) {
		this.repTran = repTran;
	}

	public Transacoes criar(Transacoes transacao) {
		
		transacao.setValorAtual(100.00);
		Double valorDB = repTran.findTopByOrderByIdDesc().get().getValorAtual();
		
		if (transacao.getTipo() == TipoEnum.RENDA) {
			Double novoValor = transacao.getValorAtual() + valorDB;
			transacao.setValorAtual(novoValor);
			transacao.setId(null);
			transacao.setCategoria(transacao.getCategoria());
			transacao.setData(transacao.getData());
			transacao.setDescricao(transacao.getDescricao());
			transacao.setMetodoPagamento(transacao.getMetodoPagamento());
			transacao.setTipo(transacao.getTipo());
			transacao.setValor(transacao.getValor());
		}
		
		Double novoValor = transacao.getValorAtual() - valorDB;
		transacao.setValorAtual(novoValor);
		
		transacao.setId(null);
		transacao.setCategoria(transacao.getCategoria());
		transacao.setData(transacao.getData());
		transacao.setDescricao(transacao.getDescricao());
		transacao.setMetodoPagamento(transacao.getMetodoPagamento());
		transacao.setTipo(transacao.getTipo());
		transacao.setValor(transacao.getValor());
		
		
		return repTran.save(transacao);
	}
	
	public List<Transacoes> listarTransacoes() {
		return repTran.findAll();
	}
	
	public Double ultimoValor(Transacoes transacao) {
		return repTran.findTopByOrderByIdDesc().get().getValorAtual();
	}
	
}
