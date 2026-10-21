package br.com.fatecmaua.finance_easy_app.model;

public enum CategoriaEnum {

	MORADIA("Moradia"), ALIMENTACAO("Alimentação"), TRANSPORTE("Transporte"),
	SAUDE("Saúde"), EDUCACAO("Educação"), LAZER("Lazer"),
	TELEFONE("Telefone"), AUTOMOVEL("Automóvel"), INTERNET("Internet"),
	FREELANCE("Freelance"), EMPREGO("Emprego");

	private String descricao;


	CategoriaEnum(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
}
