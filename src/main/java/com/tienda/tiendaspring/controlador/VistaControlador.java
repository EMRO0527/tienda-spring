package com.tienda.tiendaspring.controlador;

import com.tienda.tiendaspring.modelo.Producto;
import com.tienda.tiendaspring.servicio.CategoriaServicio;
import com.tienda.tiendaspring.servicio.ProductoServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vista")
@RequiredArgsConstructor
public class VistaControlador {

    private final ProductoServicio productoServicio;
    private final CategoriaServicio categoriaServicio;

    @GetMapping("/productos")
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoServicio.obtenerTodos());
        model.addAttribute("categorias", categoriaServicio.obtenerTodas());
        model.addAttribute("producto", new Producto());
        return "productos";
    }

    @PostMapping("/productos/guardar")
    public String guardarProducto(@Valid @ModelAttribute Producto producto,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productos", productoServicio.obtenerTodos());
            model.addAttribute("categorias", categoriaServicio.obtenerTodas());
            return "productos";
        }
        productoServicio.guardar(producto);
        return "redirect:/vista/productos";
    }

    @GetMapping("/productos/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        model.addAttribute("producto", productoServicio.obtenerPorId(id));
        model.addAttribute("categorias", categoriaServicio.obtenerTodas());
        model.addAttribute("productos", productoServicio.obtenerTodos());
        return "productos";
    }

    @PostMapping("/productos/actualizar/{id}")
    public String actualizarProducto(@PathVariable int id,
                                     @Valid @ModelAttribute Producto producto,
                                     BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productos", productoServicio.obtenerTodos());
            model.addAttribute("categorias", categoriaServicio.obtenerTodas());
            return "productos";
        }
        producto.setId(id);
        productoServicio.guardar(producto);
        return "redirect:/vista/productos";
    }

    @GetMapping("/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable int id) {
        productoServicio.eliminar(id);
        return "redirect:/vista/productos";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/acceso-denegado")
    public String accesoDenegado() {
        return "acceso-denegado";
    }
}