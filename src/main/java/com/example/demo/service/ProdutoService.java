package com.example.demo.service;

import com.example.demo.exceptions.marcaExceptions.MarcaNotFoundException;
import com.example.demo.exceptions.produtoExceptions.ProdutoNotFoundException;
import com.example.demo.model.Marca;
import com.example.demo.model.Produto;
import com.example.demo.repository.MarcaRepository;
import com.example.demo.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final MarcaRepository marcaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, MarcaRepository marcaRepository) {
        this.produtoRepository = produtoRepository;
        this.marcaRepository = marcaRepository;
    }

    public Produto createProduto(String name, Double price, Long marcaId) {
        Marca marca = marcaRepository.findById(marcaId)
                .orElseThrow(() -> new MarcaNotFoundException("Marca não encontrada com id: " + marcaId));

        Produto produto = new Produto(name, price, marca);
        return produtoRepository.save(produto);
    }

    public Produto getProdutoById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado com id: " + id));
    }

    public List<Produto> listProdutos() {
        return produtoRepository.findAll();
    }
}
