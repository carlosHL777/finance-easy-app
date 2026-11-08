package br.com.fatecmaua.finance_easy_app.projection;

import br.com.fatecmaua.finance_easy_app.model.CategoriaEnum;
import br.com.fatecmaua.finance_easy_app.model.TipoEnum;

public interface TotalPorCategoria {

    String getUsuario();
    TipoEnum getTIpo();
    CategoriaEnum getCategoria();
    Double getTotal();
}
