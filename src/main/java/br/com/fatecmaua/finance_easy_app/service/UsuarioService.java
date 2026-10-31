package br.com.fatecmaua.finance_easy_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

	public Usuario buscarPorId(Long id) {
		Optional<Usuario> busca = repUser.findById(id);

		if (busca.isPresent()) {
			return busca.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}
}
