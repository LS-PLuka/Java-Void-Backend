package com.example.demo.service;

import com.example.demo.exceptions.produtoExceptions.ProdutoNotFoundException;
import com.example.demo.exceptions.sacolaExceptions.SacolaNotFoundException;
import com.example.demo.exceptions.userExceptions.UserNotFoundException;
import com.example.demo.model.Sacola;
import com.example.demo.model.Usuario;
import com.example.demo.model.Produto;
import com.example.demo.repository.SacolaRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class SacolaService {

    private final SacolaRepository sacolaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;

    public SacolaService(
            SacolaRepository sacolaRepository,
            UsuarioRepository usuarioRepository,
            ProdutoRepository produtoRepository
    ) {
        this.sacolaRepository = sacolaRepository;
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
    }

    public Sacola getOrCreateSacola(Long usuarioId) {
        return sacolaRepository.findByUsuarioId(usuarioId)
                .orElseGet(() -> {
                    Usuario usuario = usuarioRepository.findById(usuarioId)
                            .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

                    Sacola nova = new Sacola(usuario);
                    return sacolaRepository.save(nova);
                });
    }

    public Sacola getSacolaByUsuario(Long usuarioId) {
        return sacolaRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new SacolaNotFoundException("Sacola não encontrada"));
    }

    public Sacola getSacola(Long usuarioId) {
        return getOrCreateSacola(usuarioId);
    }

    public Sacola addProduto(Long usuarioId, Long produtoId) {
        Sacola sacola = getOrCreateSacola(usuarioId);

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado"));

        sacola.getProdutos().add(produto);
        return sacolaRepository.save(sacola);
    }

    public Sacola removeProduto(Long usuarioId, Long produtoId) {
        Sacola sacola = getOrCreateSacola(usuarioId);

        sacola.getProdutos().removeIf(p -> p.getId().equals(produtoId));
        return sacolaRepository.save(sacola);
    }

    public Sacola clearSacola(Long usuarioId) {
        Sacola sacola = getOrCreateSacola(usuarioId);

        sacola.getProdutos().clear();
        return sacolaRepository.save(sacola);
    }
}
