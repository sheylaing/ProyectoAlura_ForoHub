package com.sheyla.challlengealura.forohub.controller;

import com.sheyla.challlengealura.forohub.domain.perfil.PerfilRepository;
import com.sheyla.challlengealura.forohub.domain.usuario.DatosMostrarUsuario;
import com.sheyla.challlengealura.forohub.domain.usuario.DatosCrearUsuario;
import com.sheyla.challlengealura.forohub.domain.usuario.Usuario;
import com.sheyla.challlengealura.forohub.domain.usuario.UsuarioRepository;
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
@RequestMapping("/usuario")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    PerfilRepository perfilRepository;

    @PostMapping
    @Transactional
    public ResponseEntity crearUsuario(@RequestBody @Valid DatosCrearUsuario datosRegistroUsuario,
                                       UriComponentsBuilder uriComponentsBuilder){

        var perfil = perfilRepository.findById(datosRegistroUsuario.perfilId())
                .orElseThrow(()-> new RuntimeException("Perfil no Encontrado"));

        var usuario = new Usuario(datosRegistroUsuario.nombre(), datosRegistroUsuario.email(),
                datosRegistroUsuario.clave(), perfil);

        usuarioRepository.save(usuario);
        var usuarioRegistrado = new DatosMostrarUsuario(usuario);
        var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(usuarioRegistrado);
    }

    @GetMapping
    public ResponseEntity <Page<DatosMostrarUsuario>> mostarUsuarios(@PageableDefault(page =0, size = 5) Pageable pageable){

        var page = usuarioRepository.findAll(pageable).map(DatosMostrarUsuario::new);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarUsuarios(@PathVariable Long id){
        var usuario = usuarioRepository.findById(id);
        if(usuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el usuario con ese id");
        }

        var respuesta = usuario.get();
        usuarioRepository.delete(respuesta);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuario eliminado correctamente");
    }
}
