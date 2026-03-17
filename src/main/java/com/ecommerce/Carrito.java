package com.ecommerce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Carrito {
    private final List<ItemCarrito> items = new ArrayList<>();

    public void agregarProducto(Producto producto, int cantidad) {
        if (cantidad <= 0) {
            throw new CantidadInvalidaException("La cantidad debe ser mayor que 0.");
        }

        Optional<ItemCarrito> existente = buscarItemPorProductoId(producto.getId());
        if (existente.isPresent()) {
            existente.get().sumarCantidad(cantidad);
        } else {
            items.add(new ItemCarrito(producto, cantidad));
        }
    }

    public boolean quitarProducto(int productoId) {
        return items.removeIf(item -> item.getProducto().getId() == productoId);
    }

    public Optional<ItemCarrito> buscarItemPorProductoId(int productoId) {
        return items.stream()
                .filter(item -> item.getProducto().getId() == productoId)
                .findFirst();
    }

    public boolean contieneCategoria(String categoria) {
        return items.stream()
                .anyMatch(item -> item.getProducto().getCategoria().equalsIgnoreCase(categoria));
    }

    public double getTotalBase() {
        return items.stream()
                .mapToDouble(ItemCarrito::getSubtotal)
                .sum();
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }

    public void vaciar() {
        items.clear();
    }

    public List<ItemCarrito> getItems() {
        return Collections.unmodifiableList(items);
    }

    public String resumen() {
        StringBuilder sb = new StringBuilder();
        if (items.isEmpty()) {
            sb.append("Carrito vacío.\n");
        } else {
            for (ItemCarrito item : items) {
                sb.append(String.format("- ID %d | %s | %s | precio %.2f | cantidad %d | subtotal %.2f%n",
                        item.getProducto().getId(),
                        item.getProducto().getNombre(),
                        item.getProducto().getCategoria(),
                        item.getProducto().getPrecio(),
                        item.getCantidad(),
                        item.getSubtotal()));
            }
            sb.append(String.format("TOTAL base: %.2f%n", getTotalBase()));
        }
        return sb.toString();
    }
}
