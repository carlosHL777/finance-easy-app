package br.com.fatecmaua.finance_easy_app.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fatecmaua.finance_easy_app.model.RoleEnum;
import br.com.fatecmaua.finance_easy_app.model.Usuario;
import br.com.fatecmaua.finance_easy_app.repository.UsuarioRepository;

@Service
public class UsuarioService {

	// service foi incluído por motivos de seguir padrões de mercado
	// seria totalmente possível seguir com Repository + Controller, como o +
	// Projeto Musga do Rennan;
	
	private UsuarioRepository repUser;
    private PasswordEncoder passwordEncoder;
	
	public UsuarioService(UsuarioRepository repUser) { //injeção de dependência
		this.repUser = repUser;
	}
	
	public Usuario cadastrar(Usuario usuario) {
		String email = usuario.getEmail().trim().toLowerCase();
		
		if(repUser.existsByEmail(email)) {
			throw new IllegalArgumentException("Email existente.");
		}
		
		usuario.setEmail(email);
		usuario.setRole(RoleEnum.USER); 
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
	
		
		return repUser.save(usuario);
	}
	
	public List<Usuario> listar() {
        return repUser.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return repUser.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }
}
