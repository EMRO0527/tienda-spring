package com.tienda.tiendaspring.repositorio;

import com.tienda.tiendaspring.modelo.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Integer> {
}