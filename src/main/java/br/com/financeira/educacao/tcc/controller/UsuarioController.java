package br.com.financeira.educacao.tcc.controller;

import br.com.financeira.educacao.tcc.domain.dto.LoginDTO;
import br.com.financeira.educacao.tcc.domain.dto.UsuarioDTO;
import br.com.financeira.educacao.tcc.domain.entity.Usuario;
import br.com.financeira.educacao.tcc.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioService.cadastrar(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO loginDTO) {
        String token = usuarioService.autenticar(loginDTO);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Usuario response = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }
}
