package br.com.fatecmaua.finance_easy_app.model;

public enum PagEnum {

	
	PIX("Pix"),
	CREDITO("Crédito"),
	DEBITO("Débito"),
	DINHEIRO("Dinheiro"),
	TRANSFERENCIA("Transferência"),
	BOLETO("Boleto");
	
	private String metodo;

	
	PagEnum(String metodo){
		this.metodo = metodo;
	}
	
	public String getMetodo() {
		return this.metodo;
	}
	
}
