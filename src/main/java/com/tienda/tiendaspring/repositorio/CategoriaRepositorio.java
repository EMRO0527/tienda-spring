package com.tienda.tiendaspring.repositorio;

import com.tienda.tiendaspring.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Integer> {
}