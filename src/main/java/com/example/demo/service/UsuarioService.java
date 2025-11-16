package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // REGRAS DE NEGOCIO

    public Usuario createUsuario(String username, String email, String password) {
        if (usuarioRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Este username já existe! Por favor, escolha outro.");
        }

        if (usuarioRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Este email já está registrado! Por favor, escolha outro.");
        }

        String encodedPassword = passwordEncoder.encode(password);
        Usuario usuario = new Usuario(username, email, encodedPassword);

        return usuarioRepository.save(usuario);
    }
}
