package com.sheyla.challlengealura.forohub.domain.perfil;

public record DatosMostrarPerfil(

        Long id,
        String nombre
) {
    public DatosMostrarPerfil(Perfil perfil){
        this(perfil.getId(), perfil.getNombre());
    }
}
