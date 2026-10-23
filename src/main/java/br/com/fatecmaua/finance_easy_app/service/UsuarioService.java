package br.com.fatecmaua.finance_easy_app.service;

import br.com.fatecmaua.finance_easy_app.model.Usuario;
import br.com.fatecmaua.finance_easy_app.repository.UsuarioRepository;

public class UsuarioService {

	// service foi incluído por motivos de seguir padrões de mercado
	// seria totalmente possível seguir com Repository + Controller, como o +
	// Projeto Musga do Rennan;
	
	private UsuarioRepository repUser;
	
	public UsuarioService(UsuarioRepository repUser) { //injeção de dependência
		this.repUser = repUser;
	}
	
	public Usuario cadastrar(Usuario usuario) {
		String email = usuario.getEmail().trim().toLowerCase();
		
		if(repUser.existsByEmail(email)) {
			throw new IllegalArgumentException("Email existente.");
			
		}
		return repUser.save(usuario);
	}
}
