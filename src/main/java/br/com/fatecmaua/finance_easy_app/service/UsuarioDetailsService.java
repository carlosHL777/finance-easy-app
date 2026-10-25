package br.com.fatecmaua.finance_easy_app.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fatecmaua.finance_easy_app.model.Usuario;
import br.com.fatecmaua.finance_easy_app.repository.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {

	private final UsuarioRepository repUser;
	
	public UsuarioDetailsService(UsuarioRepository repUser) {
		this.repUser = repUser;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = repUser.findByEmail(email.trim().toLowerCase())
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
		
		return User.withUsername(usuario.getEmail())
				.password(usuario.getSenha())
				.roles(usuario.getRole().name())
				.build();
	}
}
