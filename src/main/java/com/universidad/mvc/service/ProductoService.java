package com.universidad.mvc.service;

import com.universidad.mvc.model.Producto;
import com.universidad.mvc.model.ProductoDAO;

import java.util.List;
import java.util.Optional;

public class ProductoService {

    private final ProductoDAO dao = new ProductoDAO();

    public List<Producto> listar() {
        return dao.listar();
    }

    public Optional<Producto> buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public void guardar(Producto producto) {
        validar(producto);
        dao.guardar(producto);
    }

    public void eliminar(int id) {
        dao.eliminar(id);
    }

    private void validar(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (producto.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        if (producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
    }
}
