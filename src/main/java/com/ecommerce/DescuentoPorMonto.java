package com.ecommerce;

import java.util.Optional;

public class DescuentoPorMonto implements ReglaDescuento {
    private final double minimo;
    private final double porcentaje;

    public DescuentoPorMonto(double minimo, double porcentaje) {
        this.minimo = minimo;
        this.porcentaje = porcentaje;
    }

    @Override
    public String descripcion() {
        return String.format("Descuento por monto: %.0f%% si total base >= %.2f", porcentaje, minimo);
    }

    @Override
    public Optional<DescuentoAplicado> evaluar(Carrito carrito) {
        double totalBase = carrito.getTotalBase();
        if (totalBase >= minimo) {
            double descuento = totalBase * (porcentaje / 100.0);
            return Optional.of(new DescuentoAplicado(descripcion(), descuento));
        }
        return Optional.empty();
    }
}
