package com.example.demo.service;

import com.example.demo.exceptions.cartaoExceptions.CartaoNotFoundException;
import com.example.demo.exceptions.cartaoExceptions.ExistsNumeroException;
import com.example.demo.exceptions.userExceptions.UserNotFoundException;
import com.example.demo.model.Cartao;
import com.example.demo.model.Usuario;
import com.example.demo.repository.CartaoRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaoService {

    // configs da classe
    private final CartaoRepository cartaoRepository;
    private final UsuarioRepository usuarioRepository;

    public CartaoService(CartaoRepository cartaoRepository, UsuarioRepository usuarioRepository) {
        this.cartaoRepository = cartaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // CRIAR cartao
    public Cartao createCartao(String numero, String validade, String cvv, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com id: " + usuarioId));

        if (cartaoRepository.existsByNumero(numero)) {
            throw new ExistsNumeroException("Esse número de cartão já existe! Por favor, escolha outro.");
        }

        Cartao cartao = new Cartao(numero, validade, cvv, usuario);
        return cartaoRepository.save(cartao);
    }

    // OBTER cartao pelo ID
    public Cartao getCartaoById(Long id) {
        return cartaoRepository.findById(id)
                .orElseThrow(() -> new CartaoNotFoundException("Cartão não encontrado com id: " + id));
    }

    // LISTAR cartoes do usuario
    public List<Cartao> listCartoesByUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        return cartaoRepository.findByUsuarioId(usuarioId);
    }

    // EDITAR cartao
    public Cartao editCartao(Long id, String numero, String validade, String cvv) {
        Cartao cartao = getCartaoById(id);

        if (!cartao.getNumero().equals(numero) && cartaoRepository.existsByNumero(numero)) {
            throw new ExistsNumeroException("Esse número de cartão já existe! Por favor, escolha outro.");
        }

        cartao.setNumero(numero);
        cartao.setValidade(validade);
        cartao.setCvv(cvv);

        return cartaoRepository.save(cartao);
    }
}
