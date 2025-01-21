package com.sheyla.challlengealura.forohub.controller;

import com.sheyla.challlengealura.forohub.domain.curso.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity crearCurso(@RequestBody @Valid DatosCrearCurso datosCreacionCurso,
                                     UriComponentsBuilder uriComponentsBuilder){

        var curso = new Curso(datosCreacionCurso);
        cursoRepository.save(curso);

        var datosDetalleCurso = new DatosMostrarCurso(curso);
        var uri = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();

        return ResponseEntity.created(uri).body(datosDetalleCurso);
    }

    @GetMapping
    public ResponseEntity<Page<DatosMostrarCurso>> mostarCursos(@PageableDefault(page = 0, size = 10) Pageable pageable){

        var page = cursoRepository.findAll(pageable).map(DatosMostrarCurso::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosMostrarCurso> editarCurso(@RequestBody @Valid DatosActualizarCurso actualizarCurso){

        Curso curso = cursoRepository.getReferenceById(actualizarCurso.id());
        curso.atualizarCurso(actualizarCurso);

        return ResponseEntity.ok(new DatosMostrarCurso(curso));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarCurso(@PathVariable Long id){

        var curso = cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        cursoRepository.delete(curso);

        return ResponseEntity.noContent().build();
    }

}
