package com.negocio.services;

import com.negocio.models.Producto;
import java.util.ArrayList;
import java.util.List;

public class InventarioService {
    private final List<Producto> productos;

    public InventarioService() {
        this.productos = new ArrayList<>();
        inicializarProductos();
    }

    private void inicializarProductos() {
        productos.add(new Producto(1, "Hamburguesa", 15.50, 20));
        productos.add(new Producto(2, "Pizza", 25.00, 15));
        productos.add(new Producto(3, "Tacos", 8.75, 30));
        productos.add(new Producto(4, "Refresco", 3.50, 50));
    }

    // ERROR 8: Corregido - Evitar bucle infinito cambiando <= a <
    public Producto buscarProductoPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que 0");
        }
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }

    // ERROR 9: Corregido - Actualizar stock después de la venta
    public boolean venderProducto(int id, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0");
        }
        Producto producto = buscarProductoPorId(id);
        if (producto != null && producto.hayStock(cantidad)) {
            producto.reducirStock(cantidad); // Reducimos el stock
            System.out.println("Venta realizada: " + producto.getNombre());
            return true;
        }
        return false;
    }

    // ERROR 10: Corregido - Condición ajustada y uso de stream para evitar duplicación
    public List<Producto> obtenerProductosDisponibles() {
        return productos.stream()
                .filter(producto -> producto.getStock() > 0)
                .toList();
    }

    // Modificado para retornar una copia de la lista y proteger encapsulamiento
    public List<Producto> obtenerTodosLosProductos() {
        return new ArrayList<>(productos);
    }
}