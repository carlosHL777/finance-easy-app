package br.com.fatecmaua.finance_easy_app.projection;

import br.com.fatecmaua.finance_easy_app.model.CategoriaEnum;
import br.com.fatecmaua.finance_easy_app.model.TipoEnum;

public interface TotalPorCategoria {

    String getUsuario();
    CategoriaEnum getCategoria();
    Double getTotal();
}
