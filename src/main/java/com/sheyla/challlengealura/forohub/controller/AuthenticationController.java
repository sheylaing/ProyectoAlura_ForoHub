package com.sheyla.challlengealura.forohub.controller;

import com.sheyla.challlengealura.forohub.domain.usuario.DatosAutenticarUsuario;
import com.sheyla.challlengealura.forohub.domain.usuario.Usuario;
import com.sheyla.challlengealura.forohub.infra.security.DatosTokenJWT;
import com.sheyla.challlengealura.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticacionUsuario(@RequestBody @Valid DatosAutenticarUsuario datosAuticacionUsuario){

        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAuticacionUsuario.email(),
                datosAuticacionUsuario.clave());

        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JwtToken = tokenService.generateToken((Usuario) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok(new DatosTokenJWT(JwtToken));
    }


}