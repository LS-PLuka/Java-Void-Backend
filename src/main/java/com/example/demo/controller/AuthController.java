package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.Usuario;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        Usuario usuario = usuarioService.createUsuario(
                request.username(),
                request.email(),
                request.password()
        );

        return ResponseEntity.ok(Map.of(
                "id", usuario.getId(),
                "username", usuario.getUsername(),
                "email", usuario.getEmail()
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String username = request.username();
        String password = request.password();

        Optional<Usuario> usuario = usuarioService.getUsuarioByUsername(username);

        if (usuario.isPresent() &&
                passwordEncoder.matches(password, usuario.get().getPassword())) {

            String token = JwtUtil.generateToken(usuario.get().getUsername());
            return ResponseEntity.ok(Map.of("token", token));
        }

        return ResponseEntity.status(401).body("Credenciais inválidas");
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody RegisterRequest request) {
        Usuario usuario = usuarioService.editUsuario(
                id,
                request.username(),
                request.email(),
                request.password()
        );

        String newToken = JwtUtil.generateToken(usuario.getUsername());

        return ResponseEntity.ok(Map.of(
                "message", "Usuário atualizado",
                "token", newToken,
                "username", usuario.getUsername(),
                "email", usuario.getEmail()
        ));
    }
}
