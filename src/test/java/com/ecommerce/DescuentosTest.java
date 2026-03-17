package com.ecommerce;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DescuentosTest {

    @Test
    void aplica_descuentos_automaticos_al_confirmar_compra() {
        Catalogo catalogo = new Catalogo();
        Producto notebook = new Producto(1, "Notebook", "Tecnologia", 120000);
        catalogo.agregarProducto(notebook);

        Carrito carrito = new Carrito();
        List<ReglaDescuento> reglas = List.of(
                new DescuentoPorMonto(100000, 10),
                new DescuentoPorCategoria("Tecnologia", 5)
        );

        TiendaService service = new TiendaService(catalogo, carrito, reglas);
        service.agregarAlCarrito(1, 1);

        ResultadoCompra resultado = service.confirmarCompra();

        assertEquals(120000, resultado.getTotalBase(), 0.001);
        assertEquals(2, resultado.getDescuentosAplicados().size());
        assertEquals(102000, resultado.getTotalFinal(), 0.001);
    }
}
