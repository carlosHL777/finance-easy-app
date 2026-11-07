package br.com.fatecmaua.finance_easy_app.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "metas")
public class MetasFinanceiras {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(name = "Id", description = "Id da Meta", example = "123")
	private Long id;
	@Schema(name = "Nome", description = "Nome da Meta", example = "GTA 6")
	private String nomeMeta;
	@Schema(name = "Descrição", description = "Descrição da Meta", example = "Realizar a compra do Game GTA 6, da Rockstar Games.")
	private String descricao;
	@Schema(name = "Valor da Meta", description = "Valor total que compõe a Meta", example = "20.000,00")
	private Double valorTotal;
	@Schema(name = "Valor Arrecadado", description = "Valor que o Usuário guardou para aquela Meta em específico.", example = "5.000,00")
	private Double valorArrecadado;
	@Enumerated(EnumType.STRING)
	@Schema(name = "Categoria", description = "Categoria da Meta", example = "Compra")
	private MetaCategoriaEnum categoria;
	@Schema(name = "Data", description = "Data do registro da Meta", example = "2026-08-05")
	private LocalDate data;
	@Schema(name = "Percentual", description = "Percentual de conclusão da Meta", example = "67%")
	private Double percentual;

	public Double getPercentual() {
		return percentual;
	}

	public void setPercentual(Double percentual) {
		this.percentual = percentual;
	}

	public MetaCategoriaEnum getCategoria() {
		return categoria;
	}

	public void setCategoria(MetaCategoriaEnum categoria) {
		this.categoria = categoria;
	}

	public MetasFinanceiras() {

	}

	public MetasFinanceiras(Long id, String nomeMeta, String descricao, Double valorTotal, Double valorArrecadado,
			MetaCategoriaEnum categoria, LocalDate data, Double percentual) {
		super();
		this.id = id;
		this.nomeMeta = nomeMeta;
		this.descricao = descricao;
		this.valorTotal = valorTotal;
		this.valorArrecadado = valorArrecadado;
		this.categoria = categoria;
		this.data = data;
		this.percentual = percentual;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeMeta() {
		return nomeMeta;
	}

	public void setNomeMeta(String nomeMeta) {
		this.nomeMeta = nomeMeta;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Double getValorArrecadado() {
		return valorArrecadado;
	}

	public void setValorArrecadado(Double valorArrecadado) {
		this.valorArrecadado = valorArrecadado;
	}

}
