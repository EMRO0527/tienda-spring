package com.tienda.tiendaspring.repositorio;

import com.tienda.tiendaspring.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
    Usuario findByUsername(String username);
}