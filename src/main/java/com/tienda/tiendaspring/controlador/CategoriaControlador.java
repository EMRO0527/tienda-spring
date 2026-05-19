package com.tienda.tiendaspring.controlador;

import com.tienda.tiendaspring.modelo.Categoria;
import com.tienda.tiendaspring.servicio.CategoriaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaControlador {

    private final CategoriaServicio servicio;

    @GetMapping
    public List<Categoria> obtenerTodas() {
        return servicio.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Categoria obtenerPorId(@PathVariable int id) {
        return servicio.obtenerPorId(id);
    }

    @PostMapping
    public Categoria guardar(@RequestBody Categoria categoria) {
        return servicio.guardar(categoria);
    }

    @PutMapping("/{id}")
    public Categoria actualizar(@PathVariable int id, @RequestBody Categoria categoria) {
        categoria.setId(id);
        return servicio.guardar(categoria);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        servicio.eliminar(id);
        return "Categoria eliminada correctamente.";
    }
}