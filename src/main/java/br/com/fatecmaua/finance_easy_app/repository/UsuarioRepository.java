package br.com.fatecmaua.finance_easy_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fatecmaua.finance_easy_app.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	boolean existsByEmail(String email); // busca para ver se o email já esta cadastrado

}
