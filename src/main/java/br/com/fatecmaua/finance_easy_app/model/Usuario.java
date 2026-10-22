package br.com.fatecmaua.finance_easy_app.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")

public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String profissao;
	@Column(unique = true) // email ÚNICO, n pode ter mais de um no mesmo
	private String email;
	@Column(length = 100) // para salvar HASH da senha corretamente😜
	private String senha;
	private LocalDate dataNasc;
	@Enumerated(EnumType.STRING) // permissão de usuário
	// por padrão, todos são USER blz
	private RoleEnum role = RoleEnum.USER;
	
	public Usuario() {
		
	}
	
	public Usuario(Long id, String nome, String profissao, String email, String senha,
			LocalDate dataNasc, RoleEnum role){
		super();
		this.id = id;
		this.nome = nome;
		this.profissao = profissao;
		this.email = email;
		this.senha = senha;
		this.dataNasc = dataNasc;
		this.role = role;
	}

	public RoleEnum getRole() {
		return role;
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}
	
	
}
