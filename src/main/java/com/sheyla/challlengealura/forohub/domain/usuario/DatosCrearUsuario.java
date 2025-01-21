package com.sheyla.challlengealura.forohub.domain.usuario;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DatosCrearUsuario(
        @NotBlank String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank String clave,
        @NotNull
        @Positive
        Long perfilId
) {
}
