package br.com.restaurante.VeganoSpace.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAPI {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity exception404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity exception404(MethodArgumentNotValidException ex){
        var error = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(error.stream().map(DataError::new).toList());
    }

    public record DataError(String field, String message){
        public DataError(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
