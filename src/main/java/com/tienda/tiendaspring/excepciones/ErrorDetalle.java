package com.tienda.tiendaspring.excepciones;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorDetalle {

    private int codigo;
    private String mensaje;
    private String detalle;
    private LocalDateTime fecha;
}