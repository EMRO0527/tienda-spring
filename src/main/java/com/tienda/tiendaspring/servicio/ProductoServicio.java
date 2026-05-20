package com.tienda.tiendaspring.servicio;

import com.tienda.tiendaspring.excepciones.RecursoNoEncontradoException;
import com.tienda.tiendaspring.modelo.Producto;
import com.tienda.tiendaspring.repositorio.ProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServicio {

    private final ProductoRepositorio repositorio;

    public List<Producto> obtenerTodos() {
        return repositorio.findAll();
    }

    public Producto obtenerPorId(int id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe el producto con ID: " + id));
    }

    public Producto guardar(Producto producto) {
        return repositorio.save(producto);
    }

    public void eliminar(int id) {
        if (!repositorio.existsById(id)) {
            throw new RecursoNoEncontradoException(
                    "No existe el producto con ID: " + id);
        }
        repositorio.deleteById(id);
    }
}