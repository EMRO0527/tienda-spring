package com.tienda.tiendaspring.modelo;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "total")
    private double total;

    @Column(name = "estado")
    private String estado;
}