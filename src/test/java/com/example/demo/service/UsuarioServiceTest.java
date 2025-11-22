package com.example.demo.service;

import com.example.demo.exceptions.ExistsEmailException;
import com.example.demo.exceptions.ExistsUsernameException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Test
    void deveCriarUsuarioComSucesso() {
        UsuarioRepository repo = mock(UsuarioRepository.class);
        PasswordEncoder encoder = mock(PasswordEncoder.class);

        UsuarioService service = new UsuarioService(repo);

        when(repo.existsByUsername("pedro")).thenReturn(false);
        when(repo.existsByEmail("p@gmail.com")).thenReturn(false);
        when(repo.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Usuario usuario = service.createUsuario("pedro", "p@gmail.com", "123");

        assertEquals("pedro", usuario.getUsername());
        assertEquals("p@gmail.com", usuario.getEmail());
    }

    @Test
    void deveLancarErroQuandoUsernameExiste() {
        UsuarioRepository repo = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repo);

        when(repo.existsByUsername("pedro")).thenReturn(true);

        assertThrows(ExistsUsernameException.class, () -> {
            service.createUsuario("pedro", "p@gmail.com", "123");
        });
    }

    @Test
    void deveLancarErroQuandoEmailExiste() {
        UsuarioRepository repo = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repo);

        when(repo.existsByEmail("p@gmail.com")).thenReturn(true);

        assertThrows(ExistsEmailException.class, () -> {
            service.createUsuario("pedro", "p@gmail.com", "123");
        });
    }

    @Test
    void deveEditarUsuarioComSucesso() {
        UsuarioRepository repo = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repo);

        Usuario u = new Usuario("pedro", "p@gmail.com", "123");
        u.setId(1L);

        when(repo.findById(1L)).thenReturn(Optional.of(u));
        when(repo.existsByUsername("novo")).thenReturn(false);
        when(repo.existsByEmail("novo@gmail.com")).thenReturn(false);
        when(repo.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Usuario editado = service.editUsuario(1L, "novo", "novo@gmail.com", "pass");

        assertEquals("novo", editado.getUsername());
        assertEquals("novo@gmail.com", editado.getEmail());
    }

    @Test
    void deveLancarErroAoEditarUsuarioInexistente() {
        UsuarioRepository repo = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repo);

        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            service.editUsuario(1L, "novo", "n@gmail.com", "123");
        });
    }

    @Test
    void deveDeletarUsuario() {
        UsuarioRepository repo = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repo);

        Usuario u = new Usuario("pedro", "p@gmail.com", "123");
        u.setId(1L);

        when(repo.findById(1L)).thenReturn(Optional.of(u));

        service.deleteUsuario(1L);

        verify(repo, times(1)).delete(u);
    }

    @Test
    void deveLancarErroAoDeletarUsuarioInexistente() {
        UsuarioRepository repo = mock(UsuarioRepository.class);
        UsuarioService service = new UsuarioService(repo);

        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            service.deleteUsuario(1L);
        });
    }
}
