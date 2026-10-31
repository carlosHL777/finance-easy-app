package br.com.fatecmaua.finance_easy_app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    		tags = "Inserção de Info")
    @PostMapping("/cadastrar")
    public Usuario cadastrar(@Valid @RequestBody Usuario usuario) {
        return serviceUser.cadastrar(usuario);
    }
    
   @Operation(description = "Endpoint que retorna todos Usuarios.",
    		tags = "Busca de Info")
    @GetMapping("/usuarios/todos")
    public List<Usuario> listarUsuarios(){
    	return serviceUser.listar();
    }
   
   @Operation(description = "Endpoint que busca Usuarios por ID.",
   		tags = "Busca de Info")
   @GetMapping("/usuarios/{id}")
   public Usuario buscarId(@PathVariable Long id) {
	   return serviceUser.buscarPorId(id);
   }
	
}
