package com.sheyla.challlengealura.forohub.infra.errores;

import com.sheyla.challlengealura.forohub.domain.ValidacionException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tatarError404(){

        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity tatarErrorDeValidacion(ValidacionException e){

        return ResponseEntity.badRequest().build();
    }

    private record DatosErrorValidacion(String campo, String error){

        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
