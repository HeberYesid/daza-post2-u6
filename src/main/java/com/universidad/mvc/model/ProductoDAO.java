package com.universidad.mvc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductoDAO {

    private static final List<Producto> PRODUCTOS = new ArrayList<>();
    private static final AtomicInteger ID_GEN = new AtomicInteger(1);

    static {
        PRODUCTOS.add(new Producto(ID_GEN.getAndIncrement(), "Laptop", "Tecnologia", 3200.0, 8));
        PRODUCTOS.add(new Producto(ID_GEN.getAndIncrement(), "Silla", "Oficina", 780.0, 15));
    }

    public List<Producto> listar() {
        return new ArrayList<>(PRODUCTOS);
    }

    public Optional<Producto> buscarPorId(int id) {
        return PRODUCTOS.stream().filter(p -> p.getId() == id).findFirst();
    }

    public void guardar(Producto producto) {
        if (producto.getId() == 0) {
            producto.setId(ID_GEN.getAndIncrement());
            PRODUCTOS.add(producto);
            return;
        }

        buscarPorId(producto.getId()).ifPresent(existente -> {
            existente.setNombre(producto.getNombre());
            existente.setCategoria(producto.getCategoria());
            existente.setPrecio(producto.getPrecio());
            existente.setStock(producto.getStock());
        });
    }

    public void eliminar(int id) {
        PRODUCTOS.removeIf(p -> p.getId() == id);
    }
}
