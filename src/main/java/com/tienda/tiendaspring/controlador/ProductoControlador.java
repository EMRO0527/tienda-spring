package com.tienda.tiendaspring.controlador;

import com.tienda.tiendaspring.modelo.Producto;
import com.tienda.tiendaspring.servicio.ProductoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoControlador {

    private final ProductoServicio servicio;

    @GetMapping
    public List<Producto> obtenerTodos() {
        return servicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Producto obtenerPorId(@PathVariable int id) {
        return servicio.obtenerPorId(id);
    }

    @PostMapping
    public Producto guardar(@RequestBody Producto producto) {
        return servicio.guardar(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable int id, @RequestBody Producto producto) {
        producto.setId(id);
        return servicio.guardar(producto);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        servicio.eliminar(id);
        return "Producto eliminado correctamente.";
    }
}