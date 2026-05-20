package com.tienda.tiendaspring.servicio;



import com.tienda.tiendaspring.excepciones.RecursoNoEncontradoException;
import com.tienda.tiendaspring.modelo.Pedido;
import com.tienda.tiendaspring.repositorio.PedidoRepositorio;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoServicio {

    private static final Logger logger = LoggerFactory.getLogger(PedidoServicio.class);
    private final PedidoRepositorio repositorio;

    public List<Pedido> obtenerTodos() {
        logger.info("Obteniendo todos los pedidos");
        return repositorio.findAll();
    }

    public Pedido obtenerPorId(int id) {
        logger.info("Buscando pedido con ID: {}", id);
        return repositorio.findById(id)
                .orElseThrow(() -> {
                    logger.error("No existe el pedido con ID: {}", id);
                    return new RecursoNoEncontradoException(
                            "No existe el pedido con ID: " + id);
                });
    }

    public Pedido guardar(Pedido pedido) {
        logger.info("Guardando pedido para cliente con ID: {}", pedido.getCliente().getId());
        return repositorio.save(pedido);
    }

    public void eliminar(int id) {
        logger.info("Eliminando pedido con ID: {}", id);
        if (!repositorio.existsById(id)) {
            logger.error("No existe el pedido con ID: {}", id);
            throw new RecursoNoEncontradoException(
                    "No existe el pedido con ID: " + id);
        }
        repositorio.deleteById(id);
        logger.info("Pedido con ID: {} eliminado correctamente", id);
    }
}