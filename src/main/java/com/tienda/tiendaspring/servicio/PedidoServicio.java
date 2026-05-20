package com.tienda.tiendaspring.servicio;



import com.tienda.tiendaspring.excepciones.RecursoNoEncontradoException;
import com.tienda.tiendaspring.modelo.Pedido;
import com.tienda.tiendaspring.repositorio.PedidoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoServicio {

    private final PedidoRepositorio repositorio;

    public List<Pedido> obtenerTodos() {
        return repositorio.findAll();
    }

    public Pedido obtenerPorId(int id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe el pedido con ID: " + id));
    }

    public Pedido guardar(Pedido pedido) {
        return repositorio.save(pedido);
    }

    public void eliminar(int id) {
        if (!repositorio.existsById(id)) {
            throw new RecursoNoEncontradoException(
                    "No existe el pedido con ID: " + id);
        }
        repositorio.deleteById(id);
    }
}