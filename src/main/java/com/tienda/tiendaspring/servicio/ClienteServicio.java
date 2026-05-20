package com.tienda.tiendaspring.servicio;



import com.tienda.tiendaspring.excepciones.RecursoNoEncontradoException;
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
        return repositorio.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe el cliente con ID: " + id));
    }

    public Cliente guardar(Cliente cliente) {
        return repositorio.save(cliente);
    }

    public void eliminar(int id) {
        if (!repositorio.existsById(id)) {
            throw new RecursoNoEncontradoException(
                    "No existe el cliente con ID: " + id);
        }
        repositorio.deleteById(id);
    }
}