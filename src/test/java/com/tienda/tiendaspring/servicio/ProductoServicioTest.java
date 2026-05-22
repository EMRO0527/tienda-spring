package com.tienda.tiendaspring.servicio;

import com.tienda.tiendaspring.excepciones.RecursoNoEncontradoException;
import com.tienda.tiendaspring.modelo.Producto;
import com.tienda.tiendaspring.repositorio.ProductoRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServicioTest {

    @Mock
    private ProductoRepositorio repositorio;

    @InjectMocks
    private ProductoServicio servicio;

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto();
        producto.setId(1);
        producto.setNombre("Teclado Mecanico");
        producto.setPrecio(89.99);
        producto.setStock(15);
    }

    @Test
    void obtenerTodos_debeRetornarListaProductos() {
        when(repositorio.findAll()).thenReturn(Arrays.asList(producto));
        List<Producto> productos = servicio.obtenerTodos();
        assertFalse(productos.isEmpty());
        assertEquals(1, productos.size());
        verify(repositorio, times(1)).findAll();
    }

    @Test
    void obtenerPorId_debeRetornarProducto_cuandoExiste() {
        when(repositorio.findById(1)).thenReturn(Optional.of(producto));
        Producto resultado = servicio.obtenerPorId(1);
        assertNotNull(resultado);
        assertEquals("Teclado Mecanico", resultado.getNombre());
        verify(repositorio, times(1)).findById(1);
    }

    @Test
    void obtenerPorId_debeLanzarExcepcion_cuandoNoExiste() {
        when(repositorio.findById(999)).thenReturn(Optional.empty());
        assertThrows(RecursoNoEncontradoException.class, () -> {
            servicio.obtenerPorId(999);
        });
        verify(repositorio, times(1)).findById(999);
    }

    @Test
    void guardar_debeRetornarProductoGuardado() {
        when(repositorio.save(producto)).thenReturn(producto);
        Producto resultado = servicio.guardar(producto);
        assertNotNull(resultado);
        assertEquals("Teclado Mecanico", resultado.getNombre());
        verify(repositorio, times(1)).save(producto);
    }

    @Test
    void eliminar_debeLanzarExcepcion_cuandoNoExiste() {
        when(repositorio.existsById(999)).thenReturn(false);
        assertThrows(RecursoNoEncontradoException.class, () -> {
            servicio.eliminar(999);
        });
        verify(repositorio, times(1)).existsById(999);
    }

    @Test
    void eliminar_debeEliminar_cuandoExiste() {
        when(repositorio.existsById(1)).thenReturn(true);
        servicio.eliminar(1);
        verify(repositorio, times(1)).deleteById(1);
    }
}