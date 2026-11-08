package br.com.fatecmaua.finance_easy_app.service;

import java.time.LocalDate;
import java.util.List;

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
import br.com.fatecmaua.finance_easy_app.model.Usuario;
import br.com.fatecmaua.finance_easy_app.projection.TotalPorCategoria;
import br.com.fatecmaua.finance_easy_app.projection.TransacaoUsuarioProjection;
import br.com.fatecmaua.finance_easy_app.repository.TransacoesRepository;
import br.com.fatecmaua.finance_easy_app.repository.UsuarioRepository;

@Service
public class TransacoesService {

	@Autowired
	private final TransacoesRepository repTran;
	private final UsuarioRepository repUser;

	public TransacoesService(TransacoesRepository repTran, UsuarioRepository repUser) {
		this.repTran = repTran;
		this.repUser = repUser;
	}

	public Transacoes criar(Transacoes transacao) {
		// remover os campos de Id, Valor Atual na hora de criar
		// usar sintaxe correta para inserir o id do usuario
		// ex. -> "usuario": { "id": 2 }

		if (transacao.getUsuario() == null || transacao.getUsuario().getId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Informe o usuario da transacao: \"usuario\": { \"id\": 1 }");
		}
		if (transacao.getValor() == null || transacao.getTipo() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		Long usuarioId = transacao.getUsuario().getId();
		Usuario usuario = repUser.findById(usuarioId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		transacao.setUsuario(usuario);

		double saldoAnterior = repTran.findTopByUsuarioIdOrderByIdDesc(usuarioId) // saldo anterior = valorAtual da
																					// última transação, ou 0 se for a
																					// primeira
				.map(Transacoes::getValorAtual).orElse(0.0);

		double valor = transacao.getValor();

		double novoSaldo = (transacao.getTipo() == TipoEnum.RENDA) ? saldoAnterior + valor : saldoAnterior - valor;

		transacao.setId(null);
		transacao.setValorAtual(novoSaldo);

		return repTran.save(transacao);
	}

	public List<Transacoes> listarTransacoes() {
		return repTran.findAll();
	}

	public List<Transacoes> listarPorMetodoPagamento(String metodo) {
		return repTran.buscarPorMetodoPagamento(metodo);
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

	public List<Transacoes> listarPorCategoriaNoPeriodo(String categoria, LocalDate inicio, LocalDate fim) {
		validarPeriodo(inicio, fim);
		return repTran.buscarPorCategoriaNoPeriodo(categoria, inicio, fim);
	}

	public List<Transacoes> listarPorTipoECategoria(String tipo, String categoria) {
		return repTran.buscarPorTipoECategoria(tipo, categoria);
	}

	private void validarPeriodo(LocalDate inicio, LocalDate fim) { // validando pra bsucar corretamente
		if (inicio.isAfter(fim)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"A data inicial não pode ser depois da data final");
		}
	}

	@Transactional
	public void remover(Long id) {
		Transacoes transacao = repTran.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transação não encontrada: " + id));

		Long usuarioId = transacao.getUsuario().getId();

		repTran.deleteById(id);

		double saldo = 0.0;
		List<Transacoes> doUsuario = repTran.findByUsuarioId(usuarioId, Sort.by("id"));
		for (Transacoes t : doUsuario) {
			if (t.getTipo() == TipoEnum.RENDA) {
				saldo = saldo + t.getValor();
			} else {
				saldo = saldo - t.getValor();
			}
			t.setValorAtual(saldo);
		}
		repTran.saveAll(doUsuario);
	}

	public Transacoes atualizarCategoria(Long id, CategoriaEnum categoria) {
		Transacoes transacao = repTran.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		if (categoria.getTipo() != transacao.getTipo()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		transacao.setCategoria(categoria);
		return repTran.save(transacao);
	}

	public Transacoes atualizarMetodoPagamento(Long id, PagEnum metodoPagamento) {
		Transacoes transacao = repTran.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		transacao.setMetodoPagamento(metodoPagamento);
		return repTran.save(transacao);
	}

	public Transacoes atualizarData(Long id, LocalDate data) {
		Transacoes transacao = repTran.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		transacao.setData(data);
		return repTran.save(transacao);
	}


	public List<Transacoes> buscarPorValorMinimo(Double valor) {
		return repTran.buscarPorValorMinimo(valor);
	}

	public List<TransacaoUsuarioProjection> buscarPorValorMinimoComUsuario(Double valor) {
		return repTran.buscarPorValorMinimoComUsuario(valor);
	}

	public List<TotalPorCategoria> totalPorCategoria(Long usuarioId, TipoEnum tipo) {
		return repTran.totalPorCategoria(usuarioId, tipo);
	}
}
