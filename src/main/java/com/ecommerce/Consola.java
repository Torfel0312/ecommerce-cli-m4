package com.ecommerce;

import java.util.List;
import java.util.Scanner;

public class Consola {
    private final Scanner scanner;
    private final TiendaService service;

    public Consola(TiendaService service) {
        this.scanner = new Scanner(System.in);
        this.service = service;
    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println("=== MENÚ PRINCIPAL ===");
            System.out.println("1) ADMIN");
            System.out.println("2) USUARIO");
            System.out.println("0) Salir");
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> menuAdmin();
                case 2 -> menuUsuario();
                case 0 -> System.out.println("Hasta pronto.");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void menuAdmin() {
        int opcion;
        do {
            System.out.println("\n=== ADMIN ===");
            System.out.println("1) Listar productos");
            System.out.println("2) Buscar productos");
            System.out.println("3) Crear producto");
            System.out.println("4) Editar producto");
            System.out.println("5) Eliminar producto");
            System.out.println("6) Ordenar por nombre");
            System.out.println("7) Ordenar por precio");
            System.out.println("0) Volver");
            opcion = leerEntero("Seleccione una opción: ");

            try {
                switch (opcion) {
                    case 1 -> listarProductos(service.getCatalogo().listarTodos());
                    case 2 -> buscarProductos();
                    case 3 -> crearProducto();
                    case 4 -> editarProducto();
                    case 5 -> eliminarProducto();
                    case 6 -> listarProductos(service.getCatalogo().listarOrdenadoPorNombre());
                    case 7 -> listarProductos(service.getCatalogo().listarOrdenadoPorPrecio());
                    case 0 -> { }
                    default -> System.out.println("Opción inválida.");
                }
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private void menuUsuario() {
        int opcion;
        do {
            System.out.println("\n=== USUARIO ===");
            System.out.println("1) Listar productos");
            System.out.println("2) Buscar productos");
            System.out.println("3) Agregar al carrito");
            System.out.println("4) Quitar del carrito");
            System.out.println("5) Ver carrito");
            System.out.println("6) Ver descuentos activos");
            System.out.println("7) Confirmar compra");
            System.out.println("0) Volver");
            opcion = leerEntero("Seleccione una opción: ");

            try {
                switch (opcion) {
                    case 1 -> listarProductos(service.getCatalogo().listarTodos());
                    case 2 -> buscarProductos();
                    case 3 -> agregarAlCarrito();
                    case 4 -> quitarDelCarrito();
                    case 5 -> verCarrito();
                    case 6 -> verDescuentos();
                    case 7 -> confirmarCompra();
                    case 0 -> { }
                    default -> System.out.println("Opción inválida.");
                }
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private void listarProductos(List<Producto> productos) {
        System.out.println("\n--- Productos ---");
        System.out.print(service.getCatalogo().resumen(productos));
    }

    private void buscarProductos() {
        String termino = leerTexto("Ingrese nombre o categoría a buscar: ");
        List<Producto> resultados = service.getCatalogo().buscarPorNombreOCategoria(termino);
        listarProductos(resultados);
    }

    private void crearProducto() {
        int id = leerEntero("ID único: ");
        String nombre = leerTexto("Nombre: ");
        String categoria = leerTexto("Categoría: ");
        double precio = leerDouble("Precio (>0): ");
        service.crearProducto(id, nombre, categoria, precio);
        System.out.println("Producto creado correctamente.");
    }

    private void editarProducto() {
        int id = leerEntero("ID del producto a editar: ");
        String nombre = leerTexto("Nuevo nombre: ");
        String categoria = leerTexto("Nueva categoría: ");
        double precio = leerDouble("Nuevo precio: ");
        service.editarProducto(id, nombre, categoria, precio);
        System.out.println("Producto editado correctamente.");
    }

    private void eliminarProducto() {
        int id = leerEntero("ID del producto a eliminar: ");
        String confirmacion = leerTexto("Confirma eliminación? (s/n): ");
        if (confirmacion.equalsIgnoreCase("s")) {
            service.eliminarProducto(id);
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    private void agregarAlCarrito() {
        int id = leerEntero("ID del producto: ");
        int cantidad = leerEntero("Cantidad (>0): ");
        service.agregarAlCarrito(id, cantidad);
        System.out.println("Producto agregado al carrito.");
    }

    private void quitarDelCarrito() {
        int id = leerEntero("ID del producto a quitar: ");
        service.quitarDelCarrito(id);
        System.out.println("Producto quitado del carrito.");
    }

    private void verCarrito() {
        System.out.println("\n--- Carrito ---");
        System.out.print(service.getCarrito().resumen());
    }

    private void verDescuentos() {
        System.out.println("\n--- Descuentos activos ---");
        for (ReglaDescuento regla : service.getReglasDescuento()) {
            System.out.println("- " + regla.descripcion());
        }
    }

    private void confirmarCompra() {
        ResultadoCompra resultado = service.confirmarCompra();
        System.out.println("\n--- Compra confirmada ---");
        System.out.printf("TOTAL base: %.2f%n", resultado.getTotalBase());
        if (resultado.getDescuentosAplicados().isEmpty()) {
            System.out.println("No se aplicaron descuentos.");
        } else {
            System.out.println("Descuentos aplicados:");
            for (DescuentoAplicado d : resultado.getDescuentosAplicados()) {
                System.out.println(" * " + d);
            }
        }
        System.out.printf("TOTAL final: %.2f%n", resultado.getTotalFinal());
        System.out.println("Orden creada:");
        System.out.print(resultado.getOrden().resumen());
        System.out.println("El carrito fue vaciado.");
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un entero válido.");
            }
        }
    }

    private double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }
}
