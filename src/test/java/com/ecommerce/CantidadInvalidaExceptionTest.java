package com.ecommerce;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CantidadInvalidaExceptionTest {

    @Test
    void lanza_excepcion_si_la_cantidad_es_menor_o_igual_a_cero() {
        Carrito carrito = new Carrito();
        Producto producto = new Producto(1, "Libro", "Educacion", 8000);

        assertThrows(CantidadInvalidaException.class,
                () -> carrito.agregarProducto(producto, 0));
    }
}
