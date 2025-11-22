package com.example.demo.service;

import com.example.demo.exceptions.marcaExceptions.ExistsNameException;
import com.example.demo.exceptions.marcaExceptions.MarcaNotFoundException;
import com.example.demo.model.Marca;
import com.example.demo.repository.MarcaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MarcaServiceTest {

    @Mock
    private MarcaRepository marcaRepository;

    @InjectMocks
    private MarcaService marcaService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createMarca_DeveCriarQuandoNaoExisteNome() {
        when(marcaRepository.existsByName("Nike")).thenReturn(false);

        Marca marcaMock = new Marca("Nike", "Esportes");
        when(marcaRepository.save(any(Marca.class))).thenReturn(marcaMock);

        Marca result = marcaService.createMarca("Nike", "Esportes");

        assertNotNull(result);
        assertEquals("Nike", result.getName());
        verify(marcaRepository).save(any(Marca.class));
    }

    @Test
    void createMarca_DeveLancarExistsNameException() {
        when(marcaRepository.existsByName("Adidas")).thenReturn(true);

        assertThrows(ExistsNameException.class, () ->
                marcaService.createMarca("Adidas", "Descrição")
        );

        verify(marcaRepository, never()).save(any());
    }

    @Test
    void getMarcaById_DeveRetornar() {
        Marca marca = new Marca("Puma", "Descrição");
        when(marcaRepository.findById(1L)).thenReturn(Optional.of(marca));

        Marca result = marcaService.getMarcaById(1L);

        assertEquals("Puma", result.getName());
        verify(marcaRepository).findById(1L);
    }

    @Test
    void getMarcaById_DeveLancarMarcaNotFoundException() {
        when(marcaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(MarcaNotFoundException.class, () ->
                marcaService.getMarcaById(1L)
        );
    }

    @Test
    void listMarcas_DeveRetornarLista() {
        List<Marca> lista = List.of(
                new Marca("Nike", "Desc"),
                new Marca("Adidas", "Desc")
        );

        when(marcaRepository.findAll()).thenReturn(lista);

        List<Marca> result = marcaService.listMarcas();

        assertEquals(2, result.size());
        verify(marcaRepository).findAll();
    }
}
