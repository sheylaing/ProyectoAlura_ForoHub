package com.sheyla.challlengealura.forohub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {

    boolean existsByMensaje(@NotBlank String mensaje);
}