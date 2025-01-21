package com.sheyla.challlengealura.forohub.domain.topico.validaciones;


import com.sheyla.challlengealura.forohub.domain.curso.Curso;
import com.sheyla.challlengealura.forohub.domain.curso.CursoRepository;
import com.sheyla.challlengealura.forohub.domain.topico.DatosCrearTopico;
import com.sheyla.challlengealura.forohub.domain.topico.DatosMostrarTopico;
import com.sheyla.challlengealura.forohub.domain.topico.Topico;
import com.sheyla.challlengealura.forohub.domain.topico.TopicoRepository;
import com.sheyla.challlengealura.forohub.domain.usuario.Usuario;
import com.sheyla.challlengealura.forohub.domain.usuario.UsuarioRepository;
import com.sheyla.challlengealura.forohub.domain.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidarCrearTopicoDuplicado {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public DatosMostrarTopico registrar(DatosCrearTopico datosCreacionTopico){
        boolean existeDuplicado = topicoRepository.existsByTitulo(datosCreacionTopico.titulo());
        if(existeDuplicado){
            throw new IllegalArgumentException("Ya existe un tópico con el mismo titulo");
        }

        Usuario autor = usuarioRepository.findById(datosCreacionTopico.idUsuario())
                .orElseThrow(() -> new ValidacionException("Autor No encontrado"));

        Curso curso = cursoRepository.findById(datosCreacionTopico.idCurso())
                .orElseThrow(() -> new ValidacionException("Curso no encontrado"));

        Topico topico = new Topico(datosCreacionTopico, autor, curso);
        topicoRepository.save(topico);

        return new DatosMostrarTopico(topico);
    }
}

