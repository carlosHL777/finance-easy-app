package br.com.fatecmaua.finance_easy_app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatecmaua.finance_easy_app.model.Transacoes;
import br.com.fatecmaua.finance_easy_app.service.TransacoesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class TransacoesController {

	private final TransacoesService serviceTran;
	
	public TransacoesController(TransacoesService serviceTran) {
		this.serviceTran = serviceTran;
	}
	
	@Operation(description = "Endpoint resposável por inserir uma"
			+ "nova Transação.",
			tags = "Inserção de Info")
	@PostMapping("/transacoes/cadastrar")
	public Transacoes cadastrar(@RequestBody Transacoes transacao) {
		return serviceTran.cadastrar(transacao);
	}
	
	@Operation(description = "Endpoint resposável por retornar todas Transações.",
			tags = "Busca de Info")
	@GetMapping("/transacoes/todas")
	public List<Transacoes> listar() {
		return serviceTran.listarTransacoes();
	}
}
