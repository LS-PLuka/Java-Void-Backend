package com.example.demo.controller;

import com.example.demo.exceptions.userExceptions.UserNotFoundException;
import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.ok(Map.of("message", "Usuário deletado com sucesso"));
    }

    @GetMapping("/list")
    public ResponseEntity<?> listUsuarios() {
        return ResponseEntity.ok(usuarioService.listUsurios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Long id) {
        Usuario usuario = usuarioService.getUsuarioById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        return ResponseEntity.ok(usuario);
    }
}
