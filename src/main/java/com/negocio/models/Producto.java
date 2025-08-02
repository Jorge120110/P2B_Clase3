package com.negocio.models;

public class Producto {

    private int id;
    private String nombre;
    private double precio;
    private int stock;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que 0");
        }
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        this.nombre = nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.precio = precio;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        this.stock = stock;
    }


    public Producto(int id, String nombre, double precio, int stock) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que 0");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // ERROR 3: Corregido - Método con validación para evitar stock negativo
    public void reducirStock(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad a reducir no puede ser negativa");
        }
        if (this.stock < cantidad) {
            throw new IllegalStateException("No hay suficiente stock disponible");
        }
        this.stock -= cantidad;
    }

    // ERROR 4: Corregido - Lógica ajustada para incluir igualdad
    public boolean hayStock(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        return stock >= cantidad;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
}
