package com.example.demo.repository;

import com.example.demo.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    Optional<Cartao> findById(Long id);
    boolean existsById(Long id);
    boolean existsByNumero(String numero);
    List<Cartao> findByUsuarioId(Long usuarioId);
}
