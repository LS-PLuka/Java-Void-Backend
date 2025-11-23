package com.example.demo.controller;

import com.example.demo.dto.produtoDto.CreateRequest;
import com.example.demo.model.Produto;
import com.example.demo.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.CacheRequest;
import java.util.Map;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduto(@RequestBody CreateRequest request) {
        Produto produto = produtoService.createProduto(
                request.name(),
                request.price(),
                request.marcaId()
        );

        return ResponseEntity.ok(Map.of(
                "id", produto.getId(),
                "name", produto.getName(),
                "price", produto.getPrice(),
                "marca", Map.of(
                        "id", produto.getMarca().getId(),
                        "name", produto.getMarca().getName()
                )
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProdutoById(@PathVariable Long id) {
        Produto produto = produtoService.getProdutoById(id);

        return ResponseEntity.ok(Map.of(
                "id", produto.getId(),
                "name", produto.getName(),
                "price", produto.getPrice(),
                "marca", Map.of(
                        "id", produto.getMarca().getId(),
                        "name", produto.getMarca().getName()
                )
        ));
    }

    @GetMapping("/list")
    public ResponseEntity<?> listProdutos() {
        return ResponseEntity.ok(produtoService.listProdutos());
    }
}
