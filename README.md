# Ecommerce CLI M4

Proyecto de consola en Java para practicar POO, control de flujo, colecciones, validaciones, excepciones y pruebas unitarias.

## Qué incluye

- Flujo **ADMIN**:
  - listar productos
  - buscar por nombre o categoría
  - crear producto
  - editar producto
  - eliminar producto con confirmación
  - ordenar por nombre o precio
- Flujo **USUARIO**:
  - listar / buscar productos
  - agregar al carrito
  - quitar del carrito
  - ver carrito con subtotales y total base
  - ver descuentos activos
  - confirmar compra
- Reglas automáticas de descuento:
  - `DescuentoPorMonto(min, porcentaje)`
  - `DescuentoPorCategoria(categoria, porcentaje)`
- Excepción personalizada:
  - `CantidadInvalidaException`
- Pruebas JUnit:
  - total del carrito
  - cantidad inválida
  - aplicación de descuentos

## Requisitos

- Java 17+
- Maven 3.9+

## Ejecutar tests

```bash
mvn test
```

## Ejecutar la aplicación

```bash
mvn compile
java -cp target/classes com.ecommerce.Main
```

## Menú principal

```text
1) ADMIN
2) USUARIO
0) Salir
```

## Ejemplo breve de compra

1. Ingresar a **USUARIO**
2. Listar productos
3. Agregar producto por ID y cantidad
4. Ver descuentos activos
5. Confirmar compra
6. El sistema muestra:
   - total base
   - descuentos aplicados
   - total final
   - orden creada en memoria
7. El carrito queda vacío

## Repositorio GitHub


https://github.com/Torfel0312/ecommerce-cli-m4
