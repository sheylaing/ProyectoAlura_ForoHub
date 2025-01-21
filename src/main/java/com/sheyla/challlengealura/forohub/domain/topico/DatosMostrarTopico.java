package com.sheyla.challlengealura.forohub.domain.topico;

import com.sheyla.challlengealura.forohub.domain.curso.Curso;

import java.time.LocalDateTime;

public record DatosMostrarTopico(

        Long id,

        String titulo,

        String mensaje,

        LocalDateTime fechaCreacion,

        Estado status,

        DatosTopicoUsuario autor,

        Curso curso
) {
    public DatosMostrarTopico(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getEstado(),
                new DatosTopicoUsuario(topico.getAutor().getId(), topico.getAutor().getNombre(), topico.getAutor().getEmail(),
                        topico.getAutor().getPerfil()),topico.getCurso());
    }
}
