package com.example.demo.controller;

import com.example.demo.dto.cartaoDto.CreateRequest;
import com.example.demo.model.Cartao;
import com.example.demo.service.CartaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    private final CartaoService cartaoService;

    public CartaoController(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    // ENDPOINTS - Rotas
    // CREATE
    @PostMapping("/create")
    public ResponseEntity<?> createCartao(@RequestBody CreateRequest request) {
        Cartao cartao = cartaoService.createCartao(
                request.numero(),
                request.validade(),
                request.cvv(),
                request.usuarioId()
        );

        return ResponseEntity.ok(Map.of(
                "id", cartao.getId(),
                "numero", cartao.getNumero(),
                "validade", cartao.getValidade(),
                "cvv", cartao.getCvv(),
                "usuario", Map.of(
                        "id", cartao.getUsuario().getId(),
                        "nome", cartao.getUsuario().getUsername()
                )
        ));
    }

    // GET pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCartaoById(@PathVariable Long id) {
        Cartao cartao = cartaoService.getCartaoById(id);

        return ResponseEntity.ok(Map.of(
                "id", cartao.getId(),
                "numero", cartao.getNumero(),
                "validade", cartao.getValidade(),
                "cvv", cartao.getCvv(),
                "usuario", Map.of(
                        "id", cartao.getUsuario().getId(),
                        "nome", cartao.getUsuario().getUsername()
                )
        ));
    }

    // LIST cartoes do usuario
    @GetMapping("/list/{usuarioId}")
    public ResponseEntity<?> listCartoesByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(cartaoService.listCartoesByUsuario(usuarioId));
    }

    // EDIT
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editCartao(@PathVariable Long id, @RequestBody CreateRequest request) {
        Cartao cartao = cartaoService.editCartao(
                id,
                request.numero(),
                request.validade(),
                request.cvv()
        );

        return ResponseEntity.ok(Map.of(
                "id", cartao.getId(),
                "numero", cartao.getNumero(),
                "validade", cartao.getValidade(),
                "cvv", cartao.getCvv(),
                "usuario", Map.of(
                        "id", cartao.getUsuario().getId(),
                        "nome", cartao.getUsuario().getUsername()
                )
        ));
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCartao(@PathVariable Long id) {
        cartaoService.deleteCartao(id);
        return ResponseEntity.ok(Map.of("message", "Cartão deletado com sucesso"));
    }
}
