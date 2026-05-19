package com.tienda.tiendaspring.servicio;

import com.tienda.tiendaspring.modelo.Cliente;
import com.tienda.tiendaspring.repositorio.ClienteRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServicio {

    private final ClienteRepositorio repositorio;

    public List<Cliente> obtenerTodos() {
        return repositorio.findAll();
    }

    public Cliente obtenerPorId(int id) {
        return repositorio.findById(id).orElse(null);
    }

    public Cliente guardar(Cliente cliente) {
        return repositorio.save(cliente);
    }

    public void eliminar(int id) {
        repositorio.deleteById(id);
    }
}