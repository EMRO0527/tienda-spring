package com.tienda.tiendaspring.controlador;

import com.tienda.tiendaspring.modelo.Producto;
import com.tienda.tiendaspring.servicio.ProductoServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoControlador.class)
public class ProductoControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
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
    void obtenerTodos_debeRetornar200() throws Exception {
        when(servicio.obtenerTodos()).thenReturn(Arrays.asList(producto));
        mockMvc.perform(get("/productos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Teclado Mecanico"));
        verify(servicio, times(1)).obtenerTodos();
    }

    @Test
    void obtenerPorId_debeRetornar200_cuandoExiste() throws Exception {
        when(servicio.obtenerPorId(1)).thenReturn(producto);
        mockMvc.perform(get("/productos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Teclado Mecanico"));
        verify(servicio, times(1)).obtenerPorId(1);
    }

    @Test
    void eliminar_debeRetornar200() throws Exception {
        doNothing().when(servicio).eliminar(1);
        mockMvc.perform(delete("/productos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(servicio, times(1)).eliminar(1);
    }
}