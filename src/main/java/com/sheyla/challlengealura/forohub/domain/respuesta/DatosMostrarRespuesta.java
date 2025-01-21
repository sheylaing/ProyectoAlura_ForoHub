package com.sheyla.challlengealura.forohub.domain.respuesta;

import com.sheyla.challlengealura.forohub.domain.topico.DatosMostrarTopico;
import com.sheyla.challlengealura.forohub.domain.topico.DatosTopicoUsuario;

import java.time.LocalDateTime;

public record DatosMostrarRespuesta(Long id,
                                   String mensaje,
                                   DatosMostrarTopico idTopico,
                                   LocalDateTime fecha,
                                   DatosTopicoUsuario idAutor,
                                   boolean solucion

) {
    public DatosMostrarRespuesta(Respuesta datos){
        this(
                datos.getId(),
                datos.getMensaje(),
                new DatosMostrarTopico(
                        datos.getTopico().getId(),
                        datos.getTopico().getTitulo(),
                        datos.getTopico().getMensaje(),
                        datos.getTopico().getFechaCreacion(),
                        datos.getTopico().getEstado(),
                        new DatosTopicoUsuario(
                                datos.getTopico().getAutor().getId(),
                                datos.getTopico().getAutor().getNombre(),
                                datos.getTopico().getAutor().getEmail(),
                                datos.getTopico().getAutor().getPerfil()
                        ),
                        datos.getTopico().getCurso()
                ),
                datos.getFechaCreacion(),
                new DatosTopicoUsuario(
                        datos.getUsuario().getId(),
                        datos.getUsuario().getNombre(),
                        datos.getUsuario().getEmail(),
                        datos.getUsuario().getPerfil()
                ),
                datos.isSolucion()
        );
    }
}
