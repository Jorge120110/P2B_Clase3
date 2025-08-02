package com.negocio.services;

import com.negocio.models.Cliente;
import com.negocio.models.Pedido;
import com.negocio.models.Producto;
import java.util.ArrayList;
import java.util.List;

public class PedidoService {
    private final List<Pedido> pedidos;
    private final InventarioService inventarioService;
    private int contadorPedidos;

    public PedidoService(InventarioService inventarioService) {
        if (inventarioService == null) {
            throw new IllegalArgumentException("El servicio de inventario no puede ser nulo");
        }
        this.pedidos = new ArrayList<>();
        this.inventarioService = inventarioService;
        this.contadorPedidos = 1;
    }


    public Pedido crearPedido(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        Pedido pedido = new Pedido(contadorPedidos, cliente);
        contadorPedidos++; // Incrementamos el contador
        pedidos.add(pedido);
        return pedido;
    }


    public boolean agregarProductoAPedido(int pedidoId, int productoId, int cantidad) {
        if (pedidoId <= 0 || productoId <= 0 || cantidad <= 0) {
            throw new IllegalArgumentException("El ID del pedido, ID del producto y cantidad deben ser mayores que 0");
        }
        Pedido pedido = buscarPedidoPorId(pedidoId);
        if (pedido == null) {
            return false;
        }
        Producto producto = inventarioService.buscarProductoPorId(productoId);
        if (producto == null) {
            return false;
        }
        if (inventarioService.venderProducto(productoId, cantidad)) {
            pedido.agregarProducto(producto, cantidad); // Usamos el método corregido de Pedido
            return true;
        }
        return false;
    }

    private Pedido buscarPedidoPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que 0");
        }
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }
        return null;
    }

    public double calcularIngresosTotales() {
        double ingresos = 0;
        for (Pedido pedido : pedidos) {
            ingresos += pedido.getTotal();
        }
        return ingresos;
    }

    public List<Pedido> obtenerTodosLosPedidos() {
        return new ArrayList<>(pedidos); // Retorna copia para proteger encapsulamiento
    }

    public void mostrarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
        } else {
            for (Pedido pedido : pedidos) {
                System.out.println(pedido);
            }
        }
    }
}
