package com.restapiproductos.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "productos")
public class Producto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nombre")
    private String nombre;

    @NotNull
    @Column(name = "precio")
    private double precio;

    @NotNull
    @Column(name = "categoria")
    private String categoria;

    @NotNull
    @Column(name = "cantidad")
    private int cantidad;

    @NotNull
    @Column(name = "enStock")
    private boolean stock;


    public Producto() {

    }

    public Producto(Long id, String nombre, double precio, String categoria, int cantidad, boolean stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.cantidad = cantidad;
        if(cantidad > 0) {
            this.stock = true;
        }
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean hayStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }
}
