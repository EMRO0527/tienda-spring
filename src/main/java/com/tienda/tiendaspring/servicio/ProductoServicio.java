package com.tienda.tiendaspring.servicio;


import com.tienda.tiendaspring.excepciones.RecursoNoEncontradoException;
import com.tienda.tiendaspring.modelo.Producto;
import com.tienda.tiendaspring.repositorio.ProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServicio {

    private static final Logger logger = LoggerFactory.getLogger(ProductoServicio.class);
    private final ProductoRepositorio repositorio;

    public List<Producto> obtenerTodos() {
        logger.info("Obteniendo todos los productos");
        return repositorio.findAll();
    }

    public Producto obtenerPorId(int id) {
        logger.info("Buscando producto con ID: {}", id);
        return repositorio.findById(id)
                .orElseThrow(() -> {
                    logger.error("No existe el producto con ID: {}", id);
                    return new RecursoNoEncontradoException(
                            "No existe el producto con ID: " + id);
                });
    }

    public Producto guardar(Producto producto) {
        logger.info("Guardando producto: {}", producto.getNombre());
        return repositorio.save(producto);
    }

    public void eliminar(int id) {
        logger.info("Eliminando producto con ID: {}", id);
        if (!repositorio.existsById(id)) {
            logger.error("No existe el producto con ID: {}", id);
            throw new RecursoNoEncontradoException(
                    "No existe el producto con ID: " + id);
        }
        repositorio.deleteById(id);
        logger.info("Producto con ID: {} eliminado correctamente", id);
    }
}