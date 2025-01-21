package com.sheyla.challlengealura.forohub.domain.curso;


public record DatosMostrarCurso(

        Long id,
        String nombre,
        Categoria categoria
) {

    public DatosMostrarCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
