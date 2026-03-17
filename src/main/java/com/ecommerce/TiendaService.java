package com.ecommerce;

import java.util.ArrayList;
import java.util.List;

public class TiendaService {
    private final Catalogo catalogo;
    private final Carrito carrito;
    private final List<ReglaDescuento> reglasDescuento;
    private final List<Orden> ordenes;
    private int siguienteOrdenId = 1;

    public TiendaService(Catalogo catalogo, Carrito carrito, List<ReglaDescuento> reglasDescuento) {
        this.catalogo = catalogo;
        this.carrito = carrito;
        this.reglasDescuento = new ArrayList<>(reglasDescuento);
        this.ordenes = new ArrayList<>();
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public List<ReglaDescuento> getReglasDescuento() {
        return new ArrayList<>(reglasDescuento);
    }

    public List<Orden> getOrdenes() {
        return new ArrayList<>(ordenes);
    }

    public void crearProducto(int id, String nombre, String categoria, double precio) {
        catalogo.agregarProducto(new Producto(id, nombre, categoria, precio));
    }

    public void editarProducto(int id, String nombre, String categoria, double precio) {
        Producto producto = catalogo.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe un producto con ese id."));
        producto.setNombre(nombre);
        producto.setCategoria(categoria);
        producto.setPrecio(precio);
    }

    public void eliminarProducto(int id) {
        if (!catalogo.eliminarProducto(id)) {
            throw new IllegalArgumentException("No existe un producto con ese id.");
        }
    }

    public void agregarAlCarrito(int productoId, int cantidad) {
        Producto producto = catalogo.buscarPorId(productoId)
                .orElseThrow(() -> new IllegalArgumentException("El id del producto no existe."));
        carrito.agregarProducto(producto, cantidad);
    }

    public void quitarDelCarrito(int productoId) {
        if (!carrito.quitarProducto(productoId)) {
            throw new IllegalArgumentException("Ese producto no está en el carrito.");
        }
    }

    public ResultadoCompra confirmarCompra() {
        if (carrito.estaVacio()) {
            throw new IllegalStateException("No se puede confirmar una compra con el carrito vacío.");
        }

        double totalBase = carrito.getTotalBase();
        List<DescuentoAplicado> aplicados = new ArrayList<>();
        for (ReglaDescuento regla : reglasDescuento) {
            regla.evaluar(carrito).ifPresent(aplicados::add);
        }

        double descuentosTotales = aplicados.stream()
                .mapToDouble(DescuentoAplicado::getMonto)
                .sum();

        double totalFinal = Math.max(0, totalBase - descuentosTotales);
        List<ItemCarrito> snapshot = new ArrayList<>(carrito.getItems());
        Orden orden = new Orden(siguienteOrdenId++, snapshot, totalBase, aplicados, totalFinal);
        ordenes.add(orden);
        carrito.vaciar();

        return new ResultadoCompra(totalBase, aplicados, totalFinal, orden);
    }
}
