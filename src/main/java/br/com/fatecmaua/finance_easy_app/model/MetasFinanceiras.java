package br.com.fatecmaua.finance_easy_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "metas")
public class MetasFinanceiras {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeMeta;
	private String descricao;
	private Double valorTotal;
	private Double valorArrecadado;
	
	public MetasFinanceiras(){ 
		
	}
	
	public MetasFinanceiras(Long id, String nomeMeta, String descricao, Double valorTotal, Double valorArrecadado) {
		super();
		this.id = id;
		this.nomeMeta = nomeMeta;
		this.descricao = descricao;
		this.valorTotal = valorTotal;
		this.valorArrecadado = valorArrecadado;
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
