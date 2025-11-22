package com.example.demo.service;

import com.example.demo.exceptions.marcaExceptions.ExistsNameException;
import com.example.demo.exceptions.marcaExceptions.MarcaNotFoundException;
import com.example.demo.model.Marca;
import com.example.demo.repository.MarcaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public Marca createMarca(String name, String description) {
        if (marcaRepository.existsByName(name)) {
            throw new ExistsNameException("Marca com nome " + name + " já existe.");
        }

        Marca marca = new Marca(name, description);
        return marcaRepository.save(marca);
    }

    public Marca getMarcaById(Long id) {
        return marcaRepository.findById(id)
                .orElseThrow(() -> new MarcaNotFoundException("Marca não encontrada com id: " + id));
    }

    public List<Marca> listMarcas() {
        return marcaRepository.findAll();
    }
}
