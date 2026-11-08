package br.com.fatecmaua.finance_easy_app.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatecmaua.finance_easy_app.model.MetaCategoriaEnum;
import br.com.fatecmaua.finance_easy_app.model.MetasFinanceiras;
import br.com.fatecmaua.finance_easy_app.model.Transacoes;
import br.com.fatecmaua.finance_easy_app.service.MetasService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
public class MetasController {

	private MetasService serviceMetas;
	
	public MetasController(MetasService serviceMetas) {
		this.serviceMetas = serviceMetas;
	}
	
	@Operation(description = "Endpoint resposável por inserir uma"
			+ "nova Meta.",
			tags = "Metas - APIs")
	@PostMapping("/metas/cadastrar")
	public MetasFinanceiras cadastrar(@RequestBody MetasFinanceiras meta) {
		return serviceMetas.criar(meta);
	}
	
	@Operation(description = "Endpoint resposável por retornar todas Metas.",
			tags = "Metas - APIs")
	@GetMapping("/metas/todas")
	public List<MetasFinanceiras> listarMetas() {
		return serviceMetas.listarMetas();
	}
	
	@Operation(description = "Retorna as metas de uma categoria.",
			tags = "Metas - APIs")
	@GetMapping("/metas/por-categoria")
	public List<MetasFinanceiras> retornarCategorias(@RequestParam MetaCategoriaEnum categoria) {
		return serviceMetas.listarPorCategorias(categoria);
	}
	
	@Operation(description = "Retorna as metas de um período específico.",
			tags = "Metas - APIs")
	@GetMapping("/metas/por-periodo")
	public List<MetasFinanceiras> retornarPorPeriodo(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio, // formatando a data
	        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) { // formatando a data
		return serviceMetas.listarPorPeriodo(inicio, fim);
	}
	
	@Operation(description = "Retorna o maior Valor Arrecadado de uma Meta.",
			tags = "Metas - APIs")
	@GetMapping("/metas/maior-valor-arrecadado")
	public MetasFinanceiras retornarMaiorValorArrecadado() {
		return serviceMetas.metaMaiorValorArrecadado();
	}
	
	@Operation(description = "Retorna o menor Valor Arrecadado de uma Meta.",
			tags = "Metas - APIs")
	@GetMapping("/metas/menor-valor-arrecadado")
	public MetasFinanceiras retornarMenorValorArrecadado() {
		return serviceMetas.metaMenorValorArrecadado();
	}
	
	@Operation(description = "Retorna o maior Percentual de Conclusão de uma Meta.",
			tags = "Metas - APIs")
	@GetMapping("/metas/maior-percentual")
	public MetasFinanceiras retornarMaiorPercentual() {
		return serviceMetas.metaMaiorPercentual();
	}
	
	@Operation(description = "Atualiza a categoria de uma meta.",
			tags = "Metas - APIs")
	@PatchMapping("/metas/{id}/categoria")
	public MetasFinanceiras atualizarCategoriaMeta(@PathVariable Long id, @RequestParam MetaCategoriaEnum categoria) {
		return serviceMetas.atualizarCategoriaPorId(id, categoria);
	}
	
	@Operation(description = "Atualiza o valor arrecadado de uma meta.",
			tags = "Metas - APIs")
	@PatchMapping("/metas/{id}/valor-arrecadado")
	public MetasFinanceiras atualizarValorArrecadadoMeta(@PathVariable Long id, @RequestParam Double valorArrecadado) {
		return serviceMetas.atualizarValorArrecadadoPorId(id, valorArrecadado);
	}
	
	@Operation(description = "Remove uma transação pelo ID.",
			tags = "Metas - APIs")
	@DeleteMapping("/metas/{id}")
	public MetasFinanceiras removerMetaPorId(@PathVariable Long id) {
		return serviceMetas.removerMetaPorId(id);
	}
}
