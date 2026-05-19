package com.tienda.tiendaspring.controlador;

import com.tienda.tiendaspring.modelo.Pedido;
import com.tienda.tiendaspring.servicio.PedidoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoControlador {

    private final PedidoServicio servicio;

    @GetMapping
    public List<Pedido> obtenerTodos() {
        return servicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Pedido obtenerPorId(@PathVariable int id) {
        return servicio.obtenerPorId(id);
    }

    @PostMapping
    public Pedido guardar(@RequestBody Pedido pedido) {
        return servicio.guardar(pedido);
    }

    @PutMapping("/{id}")
    public Pedido actualizar(@PathVariable int id, @RequestBody Pedido pedido) {
        pedido.setId(id);
        return servicio.guardar(pedido);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable int id) {
        servicio.eliminar(id);
        return "Pedido eliminado correctamente.";
    }
}