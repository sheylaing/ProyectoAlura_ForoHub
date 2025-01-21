package com.sheyla.challlengealura.forohub.domain.topico;


import com.sheyla.challlengealura.forohub.domain.perfil.Perfil;
import com.sheyla.challlengealura.forohub.domain.usuario.Usuario;

public record DatosTopicoUsuario(

        Long id,
        String nombre,
        String correoElectronico,
        Perfil perfil
) {
    public DatosTopicoUsuario(Usuario datos){
        this(datos.getId(), datos.getNombre(), datos.getEmail(), datos.getPerfil());
    }
}

