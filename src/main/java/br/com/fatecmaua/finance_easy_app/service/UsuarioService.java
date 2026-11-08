package br.com.fatecmaua.finance_easy_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fatecmaua.finance_easy_app.model.MetasFinanceiras;
import br.com.fatecmaua.finance_easy_app.model.RoleEnum;
import br.com.fatecmaua.finance_easy_app.model.Usuario;
import br.com.fatecmaua.finance_easy_app.repository.UsuarioRepository;

@Service
public class UsuarioService {

	// service foi incluído por motivos de seguir padrões de mercado
	// seria totalmente possível seguir com Repository + Controller, como o +
	// Projeto Musga do Rennan;

	@Autowired
	private final UsuarioRepository repUser;

	public UsuarioService(UsuarioRepository repUser) { // injeção de dependência
		this.repUser = repUser;
	}

	public Usuario cadastrar(Usuario usuario) {
		String email = usuario.getEmail().trim().toLowerCase(); // formatando email

		if (repUser.existsByEmail(email)) { // verificando se ja existe
			throw new IllegalArgumentException("E-mail já cadastrado");

		}

		usuario.setId(null); // sempre sera null para nao repetir
		usuario.setRole(RoleEnum.USER);
		usuario.setEmail(email);
		usuario.setSenha(usuario.getSenha());

		return repUser.save(usuario);
	}

	public List<Usuario> listar() {
		return repUser.findAll();
	}
	
	public Usuario atualizarNomePorEmail(String email, String nome) {
		Optional<Usuario> busca = repUser.findByEmail(email);
		
		if (!repUser.existsByEmail(email)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		Usuario user = busca.get();
		user.setNome(nome);
		return repUser.save(user);
		
	}
	
	public Usuario atualizarSenhaPorEmail(String email, String senha) {
		Optional<Usuario> busca = repUser.findByEmail(email);
		
		if (!repUser.existsByEmail(email)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		Usuario user = busca.get();
		user.setSenha(senha);
		return repUser.save(user);
		
	}
	
	public Usuario atualizarProfissaoPorEmail(String email, String profissao) {
		Optional<Usuario> busca = repUser.findByEmail(email);
		
		if (!repUser.existsByEmail(email)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		Usuario user = busca.get();
		user.setProfissao(profissao);
		return repUser.save(user);
		
	}
	
	public Usuario removerUsuarioPorEmail(String email) {
		Optional<Usuario> busca = repUser.findByEmail(email);
		
		if (!repUser.existsByEmail(email)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		Usuario user = busca.get();
		repUser.deleteByEmail(email);
		return user;
	}
	
}
