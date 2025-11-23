package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cartoes")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numero;

    @Column(nullable = false)
    private String validade;

    @Column(nullable = false)
    private String cvv;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnoreProperties("cartoes")
    private Usuario usuario;

    public Cartao(String numero, String validade, String cvv, Usuario usuario) {
        this.numero = numero;
        this.validade = validade;
        this.cvv = cvv;
        this.usuario = usuario;
    }
}
