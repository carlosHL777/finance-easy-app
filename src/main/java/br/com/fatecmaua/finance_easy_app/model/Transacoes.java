package br.com.fatecmaua.finance_easy_app.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transacao")
public class Transacoes {

	@Id //campo do "ID" (ex. 1, 2, 3...), gerado automaticamente a cada registro - Carlos H. 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private String descricao; // qual a transação
	private Double valor; // valor da transação
	private Double valorAtual; //saldo atual (valor da nova transação +- valor atual)
	private LocalDate data; // registro de data
	@Enumerated(EnumType.STRING)
	private TipoEnum tipo; // tipo -> Renda / Gasto
	@Enumerated(EnumType.STRING)
	private CategoriaEnum categoria; // categoria da transação
	@Enumerated(EnumType.STRING)
	private PagEnum MetodoPagamento; // meio pag isso aí 👌
	
	public Transacoes() {
		
	}
	
	public Transacoes(Long id, String descricao, CategoriaEnum categoria, TipoEnum tipo, PagEnum MetodoPagamento, LocalDate data,
			Double valor, Double valorAtual) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.categoria = categoria;
		this.MetodoPagamento = MetodoPagamento;
		this.tipo = tipo;
		this.data = data;
		this.valor = valor;
		this.valorAtual = valorAtual;
	}

	public PagEnum getMetodoPagamento() {
		return MetodoPagamento;
	}

	public void setMetodoPagamento(PagEnum metodoPagamento) {
		MetodoPagamento = metodoPagamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getValorAtual() {
		return valorAtual;
	}

	public void setValorAtual(Double valorAtual) {
		this.valorAtual = valorAtual;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public TipoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoEnum tipo) {
		this.tipo = tipo;
	}

	public CategoriaEnum getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEnum categoria) {
		this.categoria = categoria;
	}
	
	
}
