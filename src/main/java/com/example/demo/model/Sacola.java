package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sacolas")
@Getter
@Setter
@NoArgsConstructor
public class Sacola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnoreProperties({"sacola", "cartoes", "password"})
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "sacola_produtos",
            joinColumns = @JoinColumn(name = "sacola_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos = new ArrayList<>();

    public Sacola(Usuario usuario) {
        this.usuario = usuario;
    }
}

