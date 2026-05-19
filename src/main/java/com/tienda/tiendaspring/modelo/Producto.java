package com.tienda.tiendaspring.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private int id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "precio", nullable = false)
    private double precio;

    @Column(name = "stock")
    private int stock;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
}