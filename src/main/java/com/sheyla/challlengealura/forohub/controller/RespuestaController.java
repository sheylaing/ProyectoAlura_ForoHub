package com.sheyla.challlengealura.forohub.controller;

import com.sheyla.challlengealura.forohub.domain.respuesta.DatosCrearRespuesta;
import com.sheyla.challlengealura.forohub.domain.respuesta.DatosMostrarRespuesta;
import com.sheyla.challlengealura.forohub.domain.respuesta.RespuestaRepository;
import com.sheyla.challlengealura.forohub.domain.respuesta.validaciones.ValidarPublicarRespuestaDuplicada;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private ValidarPublicarRespuestaDuplicada validarPublicarRespuesta;

    @Autowired
    private RespuestaRepository respuestaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity publicarRespuesta(@RequestBody @Valid DatosCrearRespuesta datosCrearRespuesta,
                                            UriComponentsBuilder uriComponentsBuilder){

        var crearRespuesta = validarPublicarRespuesta.publicarRespuesta(datosCrearRespuesta);
        var uri = uriComponentsBuilder.path("/respuesta/{id}").buildAndExpand(crearRespuesta.id()).toUri();

        return ResponseEntity.created(uri).body(crearRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosMostrarRespuesta>> mostarRepuesta(@PageableDefault(size = 1) Pageable pageable){

        return ResponseEntity.ok(respuestaRepository.findAll(pageable).map(DatosMostrarRespuesta::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarRespuestaPorId(@PathVariable Long id){
        var respuestaOptional = respuestaRepository.findById(id);
        if(respuestaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO se encontro una respuesta con ese ID");
        }
        var respuesta = respuestaOptional.get();

        return ResponseEntity.ok(new DatosMostrarRespuesta(respuesta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){

        var respuestaOptional = respuestaRepository.findById(id);
        if(respuestaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO se encontro una respuesta con ese ID");
        }

        var respuesta = respuestaOptional.get();
        respuestaRepository.delete(respuesta);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Respuesta eliminada correctamente");

    }
}
