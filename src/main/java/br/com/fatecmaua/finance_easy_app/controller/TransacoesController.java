package br.com.fatecmaua.finance_easy_app.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatecmaua.finance_easy_app.model.CategoriaEnum;
import br.com.fatecmaua.finance_easy_app.model.PagEnum;
import br.com.fatecmaua.finance_easy_app.model.TipoEnum;
import br.com.fatecmaua.finance_easy_app.model.Transacoes;
import br.com.fatecmaua.finance_easy_app.service.TransacoesService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
public class TransacoesController {

	private final TransacoesService serviceTran;
	
	public TransacoesController(TransacoesService serviceTran) {
		this.serviceTran = serviceTran;
	}
	
	@Operation(description = "Endpoint resposável por inserir uma"
			+ "nova Transação.",
			tags = "Transações - APIs")
	@PostMapping("/transacoes/cadastrar")
	public Transacoes cadastrar(@RequestBody Transacoes transacao) {
		return serviceTran.criar(transacao);
	}
	
	@Operation(description = "Endpoint resposável por retornar todas Transações.",
			tags = "Transações - APIs")
	@GetMapping("/transacoes/todas")
	public List<Transacoes> listar() {
		return serviceTran.listarTransacoes();
	}
	
	@Operation(description = "Retorna as transações de um meio de pagamento.", 
			tags = "Transações - APIs")
	@GetMapping("/transacoes/por-meio-pagamento")
	public List<Transacoes> porMetodoPagamento(@RequestParam PagEnum meio) {
	    return serviceTran.listarPorMetodoPagamento(meio);
	}

	@Operation(description = "Retorna as transações de um tipo (RENDA x GASTO).",
			tags = "Transações - APIs")
	@GetMapping("/transacoes/por-tipo")
	public List<Transacoes> porTipo(@RequestParam TipoEnum tipo) {
	    return serviceTran.listarPorTipo(tipo);
	}

	@Operation(description = "Retorna as transações de uma categoria.",
			tags = "Transações - APIs")
	@GetMapping("/transacoes/por-categoria")
	public List<Transacoes> porCategoria(@RequestParam CategoriaEnum categoria) {
	    return serviceTran.listarPorCategoria(categoria);
	}

	@Operation(description = "Retorna as transações de um período específico.",
			tags = "Transações - APIs")
	@GetMapping("/transacoes/por-periodo")
	public List<Transacoes> porPeriodo(
	        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio, // formatando a data
	        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) { // formatando a data
	    return serviceTran.listarPorPeriodo(inicio, fim);
	}

	@Operation(description = "Retorna as transações de um tipo em um período.",
			tags = "Transações - APIs")
	@GetMapping("/transacoes/tipo-no-periodo")
	public List<Transacoes> tipoNoPeriodo(
	        @RequestParam TipoEnum tipo,
	        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio, // formatando a data
	        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) { // formatando a data
	    return serviceTran.listarPorTipoNoPeriodo(tipo, inicio, fim);
	}

	@Operation(description = "Retorna as transações de uma categoria em um período.",
			tags = "Transações - APIs")
	@GetMapping("/transacoes/categoria-no-periodo")
	public List<Transacoes> categoriaNoPeriodo(
	        @RequestParam CategoriaEnum categoria,
	        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio, // formatando a data
	        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) { // formatando a data
	    return serviceTran.listarPorCategoriaNoPeriodo(categoria, inicio, fim);
	}

	@Operation(description = "Retorna as transações de um tipo em uma categoria.",
			tags = "Transações - APIs")
	@GetMapping("/transacoes/tipo-na-categoria")
	public List<Transacoes> tipoNaCategoria(@RequestParam TipoEnum tipo, @RequestParam CategoriaEnum categoria) {
	    return serviceTran.listarPorTipoECategoria(tipo, categoria);
	}
	
	@Operation(description = "Remove uma transação pelo ID.",
			tags = "Transações - APIs")
	@DeleteMapping("/transacoes/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
	    serviceTran.remover(id);
	    return ResponseEntity.noContent().build();
	}

	@Operation(description = "Atualiza a categoria de uma transação.",
			tags = "Transações - APIs")
	@PatchMapping("/transacoes/{id}/categoria")
	public Transacoes atualizarCategoria(@PathVariable Long id, @RequestParam CategoriaEnum categoria) {
	    return serviceTran.atualizarCategoria(id, categoria);
	}

	@Operation(description = "Atualiza o meio de pagamento de uma transação.",
			tags = "Transações - APIs")
	@PatchMapping("/transacoes/{id}/meio-pagamento")
	public Transacoes atualizarMetodoPagamento(@PathVariable Long id, @RequestParam PagEnum metodoPagamento) {
	    return serviceTran.atualizarMetodoPagamento(id, metodoPagamento);
	}

	@Operation(description = "Atualiza a data de uma transação.",
			tags = "Transações - APIs")
	@PatchMapping("/transacoes/{id}/data")
	public Transacoes atualizarData(
	        @PathVariable Long id,
	        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
	    return serviceTran.atualizarData(id, data);
	}
}
