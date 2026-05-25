package com.tienda.tiendaspring.controlador;

import com.tienda.tiendaspring.modelo.Producto;
import com.tienda.tiendaspring.servicio.ProductoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "API para gestion de productos")
public class ProductoControlador {

    private final ProductoServicio servicio;

    @GetMapping
    @Operation(summary = "Obtener todos los productos")
    public List<Producto> obtenerTodos() {
        return servicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID")
    public Producto obtenerPorId(@PathVariable int id) {
        return servicio.obtenerPorId(id);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo producto")
    public Producto guardar(@Valid @RequestBody Producto producto) {
        return servicio.guardar(producto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto existente")
    public Producto actualizar(@PathVariable int id, @Valid @RequestBody Producto producto) {
        producto.setId(id);
        return servicio.guardar(producto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto por ID")
    public String eliminar(@PathVariable int id) {
        servicio.eliminar(id);
        return "Producto eliminado correctamente.";
    }
}