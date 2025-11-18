package com.example.demo.service;

import com.example.demo.exceptions.ExistsEmailException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.exceptions.ExistsUsernameException;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            throw new ExistsUsernameException("Este username já existe! Por favor, escolha outro.");
        }

        if (usuarioRepository.existsByEmail(email)) {
            throw new ExistsEmailException("Este email já está registrado! Por favor, escolha outro.");
        }

        String encodedPassword = passwordEncoder.encode(password);
        Usuario usuario = new Usuario(username, email, encodedPassword);

        return usuarioRepository.save(usuario);
    }

    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário com ID " + id + " não encontrado."));
    }

    public List<Usuario> listUsurios() {
        return usuarioRepository.findAll();
    }

    public Usuario editUsuario(Long id, String username, String email, String password) {
        Usuario usuario = getUsuarioById(id);

        if (!usuario.getUsername().equals(username) && usuarioRepository.existsByUsername(username)) {
            throw new ExistsUsernameException("Este username já existe! Por favor, escolha outro.");
        }

        if (!usuario.getEmail().equals(email) && usuarioRepository.existsByEmail(email)) {
            throw new ExistsEmailException("Este email já está registrado! Por favor, escolha outro.");
        }

        usuario.setUsername(username);
        usuario.setEmail(email);
        if (password != null && !password.isEmpty()) {
            String encodedPassword = passwordEncoder.encode(password);
            usuario.setPassword(encodedPassword);
        }

        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        Usuario usuario = getUsuarioById(id);
        usuarioRepository.delete(usuario);
    }
}
