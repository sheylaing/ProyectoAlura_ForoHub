package com.sheyla.challlengealura.forohub.domain.perfil;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;

@Entity(name = "Perfil")
@Table(name = "perfiles")
@EqualsAndHashCode(of = "id")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public Perfil() {
    }

    public Perfil(@Valid DatosRegistrarPerfil datos) {
        this.nombre = datos.nombre();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

}