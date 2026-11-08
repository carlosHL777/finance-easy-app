package br.com.fatecmaua.finance_easy_app.projection;

public interface TransacaoUsuarioProjection {
    String getDescricao();
    Double getValor();
    String getTipo();
    String getUsuario();

}
