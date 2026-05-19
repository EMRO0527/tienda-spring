package com.tienda.tiendaspring.controlador;

import com.tienda.tiendaspring.modelo.Cliente;
import com.tienda.tiendaspring.servicio.ClienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteControlador {

    private final ClienteServicio servicio;

    @GetMapping
    public List<Cliente> obtenerTodos() {
        return servicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Cliente obtenerPorId(@PathVariable int id) {
        return servicio.obtenerPorId(id);
    }

    @PostMapping
    public Cliente guardar(@RequestBody Cliente cliente) {
        return servicio.guardar(cliente);
    }

    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable int id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        return servicio.guardar(cliente);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        servicio.eliminar(id);
        return "Cliente eliminado correctamente.";
    }
}