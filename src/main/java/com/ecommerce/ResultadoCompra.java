package com.ecommerce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultadoCompra {
    private final double totalBase;
    private final List<DescuentoAplicado> descuentosAplicados;
    private final double totalFinal;
    private final Orden orden;

    public ResultadoCompra(double totalBase, List<DescuentoAplicado> descuentosAplicados,
                           double totalFinal, Orden orden) {
        this.totalBase = totalBase;
        this.descuentosAplicados = new ArrayList<>(descuentosAplicados);
        this.totalFinal = totalFinal;
        this.orden = orden;
    }

    public double getTotalBase() {
        return totalBase;
    }

    public List<DescuentoAplicado> getDescuentosAplicados() {
        return Collections.unmodifiableList(descuentosAplicados);
    }

    public double getTotalFinal() {
        return totalFinal;
    }

    public Orden getOrden() {
        return orden;
    }
}
