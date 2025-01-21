package com.sheyla.challlengealura.forohub.domain.usuario;

public record DatosMostrarUsuario(
        Long id,
        String nombre,
        String email,
        String clave,
        String perfilId
) {

    public DatosMostrarUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getClave(), usuario.getPerfil().getNombre());
    }
}
