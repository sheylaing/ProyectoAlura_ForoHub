package com.sheyla.challlengealura.forohub.domain.respuesta.validaciones;

import com.sheyla.challlengealura.forohub.domain.ValidacionException;
import com.sheyla.challlengealura.forohub.domain.respuesta.DatosCrearRespuesta;
import com.sheyla.challlengealura.forohub.domain.respuesta.DatosMostrarRespuesta;
import com.sheyla.challlengealura.forohub.domain.respuesta.Respuesta;
import com.sheyla.challlengealura.forohub.domain.respuesta.RespuestaRepository;
import com.sheyla.challlengealura.forohub.domain.topico.Topico;
import com.sheyla.challlengealura.forohub.domain.topico.TopicoRepository;
import com.sheyla.challlengealura.forohub.domain.usuario.Usuario;
import com.sheyla.challlengealura.forohub.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidarPublicarRespuestaDuplicada {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public DatosMostrarRespuesta publicarRespuesta(DatosCrearRespuesta creacionRespuesta){

        boolean existeRespuestaDuplicada = respuestaRepository.existsByMensaje(creacionRespuesta.mensaje());
        if(existeRespuestaDuplicada){
            throw new ValidacionException("Ya existe una respuesta con el mismo mensaje");
        }

        Topico topico = topicoRepository.findById(creacionRespuesta.idTopico())
                .orElseThrow(() -> new ValidacionException("No se encontró el topico con el id " + creacionRespuesta.idTopico()));

        Usuario usuario = usuarioRepository.findById(creacionRespuesta.idAutor())
                .orElseThrow(()-> new EntityNotFoundException("No se encontró el usuario"));

        Respuesta respuesta = new Respuesta(creacionRespuesta, topico, usuario);
        respuestaRepository.save(respuesta);

        return new DatosMostrarRespuesta(respuesta);
    }

}
