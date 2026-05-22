package com.tienda.tiendaspring.controlador;


import com.tienda.tiendaspring.modelo.Categoria;
import com.tienda.tiendaspring.modelo.Producto;
import com.tienda.tiendaspring.servicio.CategoriaServicio;
import com.tienda.tiendaspring.servicio.ProductoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vista")
@RequiredArgsConstructor
public class VistaControlador {

    private final ProductoServicio productoServicio;
    private final CategoriaServicio categoriaServicio;

    // Página principal - lista de productos
    @GetMapping("/productos")
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoServicio.obtenerTodos());
        model.addAttribute("categorias", categoriaServicio.obtenerTodas());
        model.addAttribute("producto", new Producto());
        return "productos";
    }

    // Guardar producto desde formulario
    @PostMapping("/productos/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoServicio.guardar(producto);
        return "redirect:/vista/productos";
    }

    // Eliminar producto
    @GetMapping("/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable int id) {
        productoServicio.eliminar(id);
        return "redirect:/vista/productos";
    }
    // Mostrar formulario de edición
    @GetMapping("/productos/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        model.addAttribute("producto", productoServicio.obtenerPorId(id));
        model.addAttribute("categorias", categoriaServicio.obtenerTodas());
        model.addAttribute("productos", productoServicio.obtenerTodos());
        return "productos";
    }

    // Actualizar producto
    @PostMapping("/productos/actualizar/{id}")
    public String actualizarProducto(@PathVariable int id, @ModelAttribute Producto producto) {
        producto.setId(id);
        productoServicio.guardar(producto);
        return "redirect:/vista/productos";
    }
}