package com.sheyla.challlengealura.forohub.domain.perfil;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistrarPerfil(

        @NotBlank
        String nombre
) {
}
