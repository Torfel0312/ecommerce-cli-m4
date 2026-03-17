package com.ecommerce;

public class DescuentoAplicado {
    private final String nombreRegla;
    private final double monto;

    public DescuentoAplicado(String nombreRegla, double monto) {
        this.nombreRegla = nombreRegla;
        this.monto = monto;
    }

    public String getNombreRegla() {
        return nombreRegla;
    }

    public double getMonto() {
        return monto;
    }

    @Override
    public String toString() {
        return String.format("%s -> -%.2f", nombreRegla, monto);
    }
}
