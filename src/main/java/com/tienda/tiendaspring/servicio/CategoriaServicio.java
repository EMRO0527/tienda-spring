package com.tienda.tiendaspring.servicio;



import com.tienda.tiendaspring.excepciones.RecursoNoEncontradoException;
import com.tienda.tiendaspring.modelo.Categoria;
import com.tienda.tiendaspring.repositorio.CategoriaRepositorio;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServicio {

    private static final Logger logger = LoggerFactory.getLogger(CategoriaServicio.class);
    private final CategoriaRepositorio repositorio;

    public List<Categoria> obtenerTodas() {
        logger.info("Obteniendo todas las categorias");
        return repositorio.findAll();
    }

    public Categoria obtenerPorId(int id) {
        logger.info("Buscando categoria con ID: {}", id);
        return repositorio.findById(id)
                .orElseThrow(() -> {
                    logger.error("No existe la categoria con ID: {}", id);
                    return new RecursoNoEncontradoException(
                            "No existe la categoria con ID: " + id);
                });
    }

    public Categoria guardar(Categoria categoria) {
        logger.info("Guardando categoria: {}", categoria.getNombre());
        return repositorio.save(categoria);
    }

    public void eliminar(int id) {
        logger.info("Eliminando categoria con ID: {}", id);
        if (!repositorio.existsById(id)) {
            logger.error("No existe la categoria con ID: {}", id);
            throw new RecursoNoEncontradoException(
                    "No existe la categoria con ID: " + id);
        }
        repositorio.deleteById(id);
        logger.info("Categoria con ID: {} eliminada correctamente", id);
    }
}