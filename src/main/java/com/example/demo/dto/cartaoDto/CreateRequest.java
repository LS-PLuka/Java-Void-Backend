package com.example.demo.dto.cartaoDto;

import com.example.demo.model.Usuario;

public record CreateRequest(String numero, String validade, String cvv, Long usuarioId) {}
