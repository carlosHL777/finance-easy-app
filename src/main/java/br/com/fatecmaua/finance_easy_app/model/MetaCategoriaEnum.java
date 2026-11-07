package br.com.fatecmaua.finance_easy_app.model;

public enum MetaCategoriaEnum {

	EMERGENCIA("Emergência"), VIAGEM("Viagem"), AUTOMOVEL("Automóvel"), DIVIDA("Dívida"), COMPRA("Compra"),
	APOSENTADORIA("Aposentadoria"), POUPANCA("Poupança"), HERANCA("Herança"), EDUCACAO("Educação");
	
	private String descricao;
	
	MetaCategoriaEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}
