package com.ecommerce;

import java.util.Optional;

public interface ReglaDescuento {
    String descripcion();
    Optional<DescuentoAplicado> evaluar(Carrito carrito);
}
