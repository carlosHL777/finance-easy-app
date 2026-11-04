package br.com.fatecmaua.finance_easy_app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.com.fatecmaua.finance_easy_app.model.CategoriaEnum;
import br.com.fatecmaua.finance_easy_app.model.PagEnum;
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
        // saldo anterior = valorAtual da última transação, ou 0 se for a primeira
        double saldoAnterior = repTran.findTopByOrderByIdDesc().get().getValorAtual();
                

        double valor = transacao.getValor();

        double novoSaldo = (transacao.getTipo() == TipoEnum.RENDA)
                ? saldoAnterior + valor
                : saldoAnterior - valor;

        transacao.setId(null);
        transacao.setValorAtual(novoSaldo);

        return repTran.save(transacao);
    }
	
	public List<Transacoes> listarTransacoes() {
		return repTran.findAll();
	}
	
	public List<Transacoes> listarPorMetodoPagamento(PagEnum metodo) {
	    return repTran.findByMetodoPagamento(metodo);
	}

	public List<Transacoes> listarPorTipo(TipoEnum tipo) {
	    return repTran.findByTipo(tipo);
	}

	public List<Transacoes> listarPorCategoria(CategoriaEnum categoria) {
	    return repTran.findByCategoria(categoria);
	}

	public List<Transacoes> listarPorPeriodo(LocalDate inicio, LocalDate fim) {
	    validarPeriodo(inicio, fim);
	    return repTran.findByDataBetween(inicio, fim);
	}

	public List<Transacoes> listarPorTipoNoPeriodo(TipoEnum tipo, LocalDate inicio, LocalDate fim) {
	    validarPeriodo(inicio, fim);
	    return repTran.findByTipoAndDataBetween(tipo, inicio, fim);
	}

	public List<Transacoes> listarPorCategoriaNoPeriodo(CategoriaEnum categoria, LocalDate inicio, LocalDate fim) {
	    validarPeriodo(inicio, fim);
	    return repTran.findByCategoriaAndDataBetween(categoria, inicio, fim);
	}

	public List<Transacoes> listarPorTipoECategoria(TipoEnum tipo, CategoriaEnum categoria) {
	    return repTran.findByTipoAndCategoria(tipo, categoria);
	}

	private void validarPeriodo(LocalDate inicio, LocalDate fim) { //validando pra bsucar corretamente
	    if (inicio.isAfter(fim)) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
	                "A data inicial não pode ser depois da data final");
	    }
	}
	
//	@Transactional
//	public void remover(Long id) {
//	    if (!repTran.existsById(id)) {
//	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transação não encontrada: " + id);
//	    }
//	    repTran.deleteById(id);
//
//	    // recalcula os saldos acumulados depois da remoção
//	    double saldo = 0.0;
//	    List<Transacoes> todas = repTran.findAll(Sort.by("id"));
//	    for (Transacoes t : todas) {
//	        saldo += (t.getTipo() == TipoEnum.RENDA) ? t.getValor() : -t.getValor();
//	        t.setValorAtual(saldo);
//	    }
//	    repTran.saveAll(todas);
//	}

	public Transacoes atualizarCategoria(Long id, CategoriaEnum categoria) {
	    Transacoes transacao = repTran.findById(id).orElseThrow(() ->
	            new ResponseStatusException(HttpStatus.NOT_FOUND, "Transação não encontrada: " + id));

	    if (categoria.getTipo() != transacao.getTipo()) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
	                "A categoria " + categoria + " não pertence ao tipo " + transacao.getTipo());
	    }

	    transacao.setCategoria(categoria);
	    return repTran.save(transacao);
	}

	public Transacoes atualizarMetodoPagamento(Long id, PagEnum metodoPagamento) {
	    Transacoes transacao = repTran.findById(id).orElseThrow(() ->
	            new ResponseStatusException(HttpStatus.NOT_FOUND, "Transação não encontrada: " + id));

	    transacao.setMetodoPagamento(metodoPagamento);
	    return repTran.save(transacao);
	}

	public Transacoes atualizarData(Long id, LocalDate data) {
	    Transacoes transacao = repTran.findById(id).orElseThrow(() ->
	            new ResponseStatusException(HttpStatus.NOT_FOUND, "Transação não encontrada: " + id));

	    transacao.setData(data);
	    return repTran.save(transacao);
	}
}
