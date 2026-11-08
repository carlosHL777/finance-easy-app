package br.com.fatecmaua.finance_easy_app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatecmaua.finance_easy_app.model.Usuario;
import br.com.fatecmaua.finance_easy_app.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
public class UsuarioController {

	private final UsuarioService serviceUser;

    public UsuarioController(UsuarioService serviceUser) {
        this.serviceUser = serviceUser;
    }

    @Operation(description = "Endpoint de cadastro de Usuário",
    		tags = "Usuário - APIs")
    @PostMapping("/usuarios/cadastrar")
    public Usuario cadastrar(@Valid @RequestBody Usuario usuario) {
        return serviceUser.cadastrar(usuario);
    }
    
   @Operation(description = "Endpoint que retorna todos Usuarios.",
    		tags = "Usuário - APIs")
    @GetMapping("/usuarios/todos")
    public List<Usuario> listarUsuarios(){
    	return serviceUser.listar();
    }
   
	@Operation(description = "Atualiza o Nome de um Usuario.",
			tags = "Usuário - APIs")
	@PatchMapping("/usuarios/{email}/nome")
	public Usuario atualizarNomeUsuario(@PathVariable String email, @RequestParam String nome) {
		return serviceUser.atualizarNomePorEmail(email, nome);
	}
	
	@Operation(description = "Atualiza a Senha de um Usuario.",
			tags = "Usuário - APIs")
	@PatchMapping("/usuarios/{email}/senha")
	public Usuario atualizarSenhaUsuario(@PathVariable String email, @RequestParam String senha) {
		return serviceUser.atualizarSenhaPorEmail(email, senha);
	}
	
	@Operation(description = "Atualiza a Senha de um Usuario.",
			tags = "Usuário - APIs")
	@PatchMapping("/usuarios/{email}/profissao")
	public Usuario atualizarProfissaoUsuario(@PathVariable String email, @RequestParam String profissao) {
		return serviceUser.atualizarProfissaoPorEmail(email, profissao);
	}
	
	@Operation(description = "Remove uma transação pelo ID.",
			tags = "Metas - APIs")
	@DeleteMapping("/usuarios/{email}/remover")
	public Usuario removerUsuario(@PathVariable String email) {
		return serviceUser.removerUsuarioPorEmail(email);
	}
   
}
