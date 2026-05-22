package com.tienda.tiendaspring.servicio;

import com.tienda.tiendaspring.excepciones.RecursoNoEncontradoException;
import com.tienda.tiendaspring.modelo.Categoria;
import com.tienda.tiendaspring.repositorio.CategoriaRepositorio;
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
public class CategoriaServicioTest {

    @Mock
    private CategoriaRepositorio repositorio;

    @InjectMocks
    private CategoriaServicio servicio;

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setId(1);
        categoria.setNombre("Electronica");
        categoria.setDescripcion("Productos electronicos");
    }

    @Test
    void obtenerTodas_debeRetornarListaCategorias() {
        when(repositorio.findAll()).thenReturn(Arrays.asList(categoria));
        List<Categoria> categorias = servicio.obtenerTodas();
        assertFalse(categorias.isEmpty());
        assertEquals(1, categorias.size());
        verify(repositorio, times(1)).findAll();
    }

    @Test
    void obtenerPorId_debeRetornarCategoria_cuandoExiste() {
        when(repositorio.findById(1)).thenReturn(Optional.of(categoria));
        Categoria resultado = servicio.obtenerPorId(1);
        assertNotNull(resultado);
        assertEquals("Electronica", resultado.getNombre());
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
    void guardar_debeRetornarCategoriaGuardada() {
        when(repositorio.save(categoria)).thenReturn(categoria);
        Categoria resultado = servicio.guardar(categoria);
        assertNotNull(resultado);
        assertEquals("Electronica", resultado.getNombre());
        verify(repositorio, times(1)).save(categoria);
    }

    @Test
    void eliminar_debeLanzarExcepcion_cuandoNoExiste() {
        when(repositorio.existsById(999)).thenReturn(false);
        assertThrows(RecursoNoEncontradoException.class, () -> {
            servicio.eliminar(999);
        });
        verify(repositorio, times(1)).existsById(999);
    }
}