package br.com.fatecmaua.finance_easy_app.model;

public enum TipoEnum {
	
	RENDA("Renda"), GASTO("Gasto");
	
	private String descricao;
	
	TipoEnum(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
