package com.tienda.tiendaspring.repositorio;

import com.tienda.tiendaspring.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {
}