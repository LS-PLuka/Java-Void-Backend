package com.example.demo.repository;

import com.example.demo.model.Sacola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SacolaRepository extends JpaRepository<Sacola, Long> {

    Optional<Sacola> findByUsuarioId(Long usuarioId);
}
