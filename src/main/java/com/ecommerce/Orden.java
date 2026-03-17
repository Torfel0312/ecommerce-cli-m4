package com.ecommerce;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Orden {
    private final int id;
    private final LocalDateTime fecha;
    private final List<ItemCarrito> items;
    private final double totalBase;
    private final List<DescuentoAplicado> descuentosAplicados;
    private final double totalFinal;

    public Orden(int id, List<ItemCarrito> items, double totalBase,
                 List<DescuentoAplicado> descuentosAplicados, double totalFinal) {
        this.id = id;
        this.fecha = LocalDateTime.now();
        this.items = new ArrayList<>(items);
        this.totalBase = totalBase;
        this.descuentosAplicados = new ArrayList<>(descuentosAplicados);
        this.totalFinal = totalFinal;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public List<ItemCarrito> getItems() {
        return Collections.unmodifiableList(items);
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

    public String resumen() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Orden #%d - %s%n", id, fecha));
        for (ItemCarrito item : items) {
            sb.append(String.format("- %s x%d -> %.2f%n",
                    item.getProducto().getNombre(),
                    item.getCantidad(),
                    item.getSubtotal()));
        }
        sb.append(String.format("Total base: %.2f%n", totalBase));
        if (descuentosAplicados.isEmpty()) {
            sb.append("Descuentos aplicados: ninguno\n");
        } else {
            sb.append("Descuentos aplicados:\n");
            for (DescuentoAplicado descuento : descuentosAplicados) {
                sb.append("  * ").append(descuento).append("\n");
            }
        }
        sb.append(String.format("Total final: %.2f%n", totalFinal));
        return sb.toString();
    }
}
