package com.tienda.tiendaspring.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ManejadorExcepciones {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ErrorDetalle> manejarRecursoNoEncontrado(
            RecursoNoEncontradoException ex, WebRequest request) {

        ErrorDetalle error = new ErrorDetalle(
                404,
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetalle> manejarExcepcionGlobal(
            Exception ex, WebRequest request) {

        ErrorDetalle error = new ErrorDetalle(
                500,
                "Error interno del servidor",
                request.getDescription(false),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}