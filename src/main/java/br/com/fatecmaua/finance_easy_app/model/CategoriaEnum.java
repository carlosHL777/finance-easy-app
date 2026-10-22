package br.com.fatecmaua.finance_easy_app.model;

public enum CategoriaEnum {

	// Adicionando as Categorias para tipo de Transação correta
	
	//Gastos
	MORADIA(TipoEnum.GASTO),
	ALIMENTACAO(TipoEnum.GASTO), 
	TRANSPORTE(TipoEnum.GASTO),
	SAUDE(TipoEnum.GASTO), 
	EDUCACAO(TipoEnum.GASTO), 
	LAZER(TipoEnum.GASTO),
	TELEFONE(TipoEnum.GASTO), 
	AUTOMOVEL(TipoEnum.GASTO), 
	INTERNET(TipoEnum.GASTO),
	//Rendas
	FREELANCE(TipoEnum.RENDA), 
	EMPREGO(TipoEnum.RENDA),
	COMISSAO(TipoEnum.RENDA),
	PREVIDENCIA(TipoEnum.RENDA),
	LIL_TIGRE(TipoEnum.RENDA),
	DAY_TRADE(TipoEnum.RENDA),
	FEIRA_DO_ROLO(TipoEnum.RENDA),
	VENDA(TipoEnum.RENDA);

	private final TipoEnum tipo;
	
	CategoriaEnum(TipoEnum tipo) {
	    this.tipo = tipo;
	}

	public TipoEnum getTipo() {
		return tipo;
	}

	
}
