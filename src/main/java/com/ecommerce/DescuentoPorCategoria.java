package com.ecommerce;

import java.util.Optional;

public class DescuentoPorCategoria implements ReglaDescuento {
    private final String categoria;
    private final double porcentaje;

    public DescuentoPorCategoria(String categoria, double porcentaje) {
        this.categoria = categoria;
        this.porcentaje = porcentaje;
    }

    @Override
    public String descripcion() {
        return String.format("Descuento por categoría: %.0f%% si el carrito contiene '%s'",
                porcentaje, categoria);
    }

    @Override
    public Optional<DescuentoAplicado> evaluar(Carrito carrito) {
        if (carrito.contieneCategoria(categoria)) {
            double descuento = carrito.getTotalBase() * (porcentaje / 100.0);
            return Optional.of(new DescuentoAplicado(descripcion(), descuento));
        }
        return Optional.empty();
    }
}
