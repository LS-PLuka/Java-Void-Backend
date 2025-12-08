package com.example.demo.controller;

import com.example.demo.model.Sacola;
import com.example.demo.service.SacolaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sacola")
public class SacolaController {

    private final SacolaService sacolaService;

    public SacolaController(SacolaService sacolaService) {
        this.sacolaService = sacolaService;
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<?> getSacola(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(sacolaService.getSacola(usuarioId));
    }

    @PostMapping("/{usuarioId}/add/{produtoId}")
    public ResponseEntity<?> addProduto(
            @PathVariable Long usuarioId,
            @PathVariable Long produtoId) {

        return ResponseEntity.ok(sacolaService.addProduto(usuarioId, produtoId));
    }

    @DeleteMapping("/{usuarioId}/remove/{produtoId}")
    public ResponseEntity<?> removeProduto(
            @PathVariable Long usuarioId,
            @PathVariable Long produtoId) {

        return ResponseEntity.ok(sacolaService.removeProduto(usuarioId, produtoId));
    }
}
