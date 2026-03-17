package com.ecommerce;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Catalogo {
    private final List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        if (buscarPorId(producto.getId()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un producto con ese id.");
        }
        productos.add(producto);
    }

    public Optional<Producto> buscarPorId(int id) {
        return productos.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    public List<Producto> buscarPorNombreOCategoria(String termino) {
        String texto = termino == null ? "" : termino.trim().toLowerCase();
        return productos.stream()
                .filter(p -> p.getNombre().toLowerCase().contains(texto)
                        || p.getCategoria().toLowerCase().contains(texto))
                .collect(Collectors.toList());
    }

    public boolean eliminarProducto(int id) {
        return productos.removeIf(p -> p.getId() == id);
    }

    public List<Producto> listarOrdenadoPorNombre() {
        return productos.stream()
                .sorted(Comparator.comparing(Producto::getNombre, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    public List<Producto> listarOrdenadoPorPrecio() {
        return productos.stream()
                .sorted(Comparator.comparingDouble(Producto::getPrecio))
                .collect(Collectors.toList());
    }

    public List<Producto> listarTodos() {
        return new ArrayList<>(productos);
    }

    public String resumen(List<Producto> lista) {
        if (lista.isEmpty()) {
            return "No hay productos.\n";
        }
        StringBuilder sb = new StringBuilder();
        for (Producto p : lista) {
            sb.append(String.format("ID %d | %s | %s | %.2f%n",
                    p.getId(), p.getNombre(), p.getCategoria(), p.getPrecio()));
        }
        return sb.toString();
    }
}
