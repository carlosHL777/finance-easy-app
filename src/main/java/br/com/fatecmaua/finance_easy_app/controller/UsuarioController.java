package br.com.fatecmaua.finance_easy_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatecmaua.finance_easy_app.model.Usuario;
import br.com.fatecmaua.finance_easy_app.service.UsuarioService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
public class UsuarioController {

	private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@Valid @RequestBody Usuario usuario) {
        Usuario salvo = usuarioService.cadastrar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }
	
}
