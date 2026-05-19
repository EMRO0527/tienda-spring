package com.tienda.tiendaspring.servicio;

import com.tienda.tiendaspring.modelo.Categoria;
import com.tienda.tiendaspring.repositorio.CategoriaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServicio {

    private final CategoriaRepositorio repositorio;

    public List<Categoria> obtenerTodas() {
        return repositorio.findAll();
    }

    public Categoria obtenerPorId(int id) {
        return repositorio.findById(id).orElse(null);
    }

    public Categoria guardar(Categoria categoria) {
        return repositorio.save(categoria);
    }

    public void eliminar(int id) {
        repositorio.deleteById(id);
    }
}