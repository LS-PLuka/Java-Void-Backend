package com.example.demo.controller;

import com.example.demo.dto.marcaDto.CreateRequest;
import com.example.demo.model.Marca;
import com.example.demo.service.MarcaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMarca(@RequestBody CreateRequest request) {
        Marca marca = marcaService.createMarca(
                request.name(),
                request.description()
        );

        return ResponseEntity.ok(Map.of(
                "id", marca.getId(),
                "name", marca.getName(),
                "description", marca.getDescription()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMarcaById(@PathVariable Long id) {
        Marca marca = marcaService.getMarcaById(id);

        return ResponseEntity.ok(Map.of(
                "id", marca.getId(),
                "name", marca.getName(),
                "description", marca.getDescription()
        ));
    }

    @GetMapping("/list")
    public ResponseEntity<?> listMarcas() {
        return ResponseEntity.ok(marcaService.listMarcas());
    }
}
