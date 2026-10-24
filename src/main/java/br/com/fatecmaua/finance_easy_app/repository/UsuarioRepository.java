package br.com.fatecmaua.finance_easy_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fatecmaua.finance_easy_app.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	boolean existsByEmail(String email); // busca para ver se o email já esta cadastrado

}
