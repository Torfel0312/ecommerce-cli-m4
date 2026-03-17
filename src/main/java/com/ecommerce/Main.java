package com.ecommerce;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Catalogo catalogo = new Catalogo();
        catalogo.agregarProducto(new Producto(1, "Notebook Pro", "Tecnologia", 950000));
        catalogo.agregarProducto(new Producto(2, "Mouse Gamer", "Tecnologia", 25000));
        catalogo.agregarProducto(new Producto(3, "Cafe de especialidad", "Alimentos", 12000));
        catalogo.agregarProducto(new Producto(4, "Cuaderno ejecutivo", "Oficina", 5500));

        Carrito carrito = new Carrito();
        List<ReglaDescuento> descuentos = List.of(
                new DescuentoPorMonto(100000, 10),
                new DescuentoPorCategoria("Tecnologia", 5)
        );

        TiendaService service = new TiendaService(catalogo, carrito, descuentos);
        Consola consola = new Consola(service);
        consola.iniciar();
    }
}
