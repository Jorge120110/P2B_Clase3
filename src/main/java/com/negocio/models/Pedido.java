package com.negocio.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private Cliente cliente;
    private List<Producto> productos;
    private LocalDateTime fecha;
    private double total;

    public Pedido(int id, Cliente cliente) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que 0");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        this.id = id;
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.fecha = LocalDateTime.now();
        this.total = 0.0;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0");
        }
        if (!producto.hayStock(cantidad)) {
            throw new IllegalStateException("No hay suficiente stock para el producto: " + producto.getNombre());
        }
        productos.add(producto);
        producto.reducirStock(cantidad); // Reducimos el stock del producto
        calcularTotal();
    }


    private void calcularTotal() {
        total = 0;
        // Asumimos que la cantidad de cada producto se maneja en un mapa o lista de cantidades
        // Para este ejemplo, asumimos que cada producto en la lista representa una unidad
        for (Producto producto : productos) {
            total += producto.getPrecio(); // Por simplicidad, asumimos 1 unidad por producto
        }
    }


    public Producto obtenerPrimerProducto() {
        if (productos.isEmpty()) {
            throw new IllegalStateException("La lista de productos está vacía");
        }
        return productos.get(0);
    }


    public double aplicarDescuento(double porcentaje) {
        if (porcentaje < 0 || porcentaje > 100) {
            throw new IllegalArgumentException("El porcentaje de descuento debe estar entre 0 y 100");
        }
        double descuento = total * porcentaje / 100;
        return total - descuento;
    }


    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public List<Producto> getProductos() { return new ArrayList<>(productos); } // Retorna copia para proteger la lista
    public LocalDateTime getFecha() { return fecha; }
    public double getTotal() { return total; }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + cliente.getNombre() +
                ", productos=" + productos.size() +
                ", fecha=" + fecha +
                ", total=" + total +
                '}';
    }
}