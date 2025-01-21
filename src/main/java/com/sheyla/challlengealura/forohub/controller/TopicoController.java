package com.sheyla.challlengealura.forohub.controller;

import com.sheyla.challlengealura.forohub.domain.ValidacionException;
import com.sheyla.challlengealura.forohub.domain.curso.CursoRepository;
import com.sheyla.challlengealura.forohub.domain.topico.DatosActualizarTopico;
import com.sheyla.challlengealura.forohub.domain.topico.DatosCrearTopico;
import com.sheyla.challlengealura.forohub.domain.topico.DatosMostrarTopico;
import com.sheyla.challlengealura.forohub.domain.topico.TopicoRepository;
import com.sheyla.challlengealura.forohub.domain.topico.validaciones.ActualizarTopico;
import com.sheyla.challlengealura.forohub.domain.topico.validaciones.ValidarCrearTopicoDuplicado;
import com.sheyla.challlengealura.forohub.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ValidarCrearTopicoDuplicado topicoDuplicado;

    @Autowired
    private ActualizarTopico actualizacionDeTopicos;

    @PostMapping
    @Transactional
    public ResponseEntity crearTopico(@RequestBody @Valid DatosCrearTopico datosCreacionTopico,
                                      UriComponentsBuilder uriComponentsBuilder){

        try {
            var topicoNuevo = topicoDuplicado.registrar(datosCreacionTopico);
            return ResponseEntity.ok(topicoNuevo);
        }catch(ValidacionException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<DatosMostrarTopico>> mostrarTopicoPorCurso(@RequestParam String curso,
                                                                          @PageableDefault(page = 0, size = 10, sort={"titulo"})
                                                                          Pageable pageable){

        //var nombreCurso = curso.getNombre();

        if(curso == null){
            var page = topicoRepository.findAll(pageable).map(DatosMostrarTopico::new);
            return ResponseEntity.ok(page);
            //throw new RuntimeException("Debes de escribir un curso para ver sus topicos");
        }

        return ResponseEntity.ok(topicoRepository.findTopicosByNombreCurso(curso, pageable).map(DatosMostrarTopico::new));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizarTopico datos , @PathVariable Long id){

        try{
            var detalleTopico = actualizacionDeTopicos.actualizar(id, datos);
            return ResponseEntity.ok(detalleTopico);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("{id}")
    public ResponseEntity mostarTopicoPorId(@PathVariable Long id){

        var topicoOptional = topicoRepository.findById(id);

        if(topicoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró un tópico con el ID proporcionado.");
        }
        var topico = topicoOptional.get();
        return ResponseEntity.ok(new DatosMostrarTopico(topico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){

        var topico = topicoRepository.findById(id);
        if(topico.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún tópico con id: " + id);
        }

        topicoRepository.delete(topico.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Tópico eliminado correctamente");
    }
}
