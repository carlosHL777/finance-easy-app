package br.com.fatecmaua.finance_easy_app.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transacao")
public class Transacoes {

	@Id //campo do "ID" (ex. 1, 2, 3...), gerado automaticamente a cada registro - Carlos H. 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(name = "Id", description = "Id da Transação cadastrada.", example = "123")
	private Long id; 
	@Schema(name = "Descrição", description = "Descrição da Transação cadastrada.", example = "Honda Civic G10 2017")
	private String descricao; // qual a transação
	@Schema(name = "Valor", description = "Valor da Transação cadastrada.", example = "93.900,00")
	private Double valor; // valor da transação
	@Schema(name = "Valor Atual", description = "Valor que o Usuário possui atualmente no BD, atualizado dinamicamente"
			+ "conforme novos registros.", example = "200,00")
	private Double valorAtual; //saldo atual (valor da nova transação +- valor atual)
	@Schema(name = "Data", description = "Data do cadastro da Transação.", example = "2026-07-01")
	private LocalDate data; // registro de data
	@Enumerated(EnumType.STRING)
	@Schema(name = "Tipo", description = "Tipo (Renda ou Gasto) da Transação.", example = "Gasto")
	private TipoEnum tipo; // tipo -> Renda / Gasto
	@Enumerated(EnumType.STRING)
	@Schema(name = "Categoria", description = "Categoria da Transação.", example = "Transporte")
	private CategoriaEnum categoria; // categoria da transação
	@Enumerated(EnumType.STRING)
	@Schema(name = "Método", description = "Método de Pagamento usado na Transação.", example = "Crédito")
	private PagEnum metodoPagamento; // meio pag isso aí 👌
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	@Schema(name = "Id Usuario", description = "ID do usuário responsável pela Transação.", example = "1")
	private Usuario usuario;
	
	public Transacoes() {
		
	}
	
	public Transacoes(Long id, String descricao, CategoriaEnum categoria, TipoEnum tipo, PagEnum MetodoPagamento, LocalDate data,
			Double valor, Double valorAtual, Usuario usuario) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.categoria = categoria;
		this.metodoPagamento = MetodoPagamento;
		this.tipo = tipo;
		this.data = data;
		this.valor = valor;
		this.valorAtual = valorAtual;
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public PagEnum getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(PagEnum metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
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
