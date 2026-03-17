package com.ecommerce;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarritoTest {

    @Test
    void calcula_total_del_carrito_correctamente() {
        Carrito carrito = new Carrito();
        Producto teclado = new Producto(1, "Teclado", "Tecnologia", 10000);
        Producto mouse = new Producto(2, "Mouse", "Tecnologia", 5000);

        carrito.agregarProducto(teclado, 2);
        carrito.agregarProducto(mouse, 3);

        assertEquals(35000, carrito.getTotalBase(), 0.001);
    }
}
