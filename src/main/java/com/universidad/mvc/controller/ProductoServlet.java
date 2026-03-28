package com.universidad.mvc.controller;

import com.universidad.mvc.model.Producto;
import com.universidad.mvc.model.Usuario;
import com.universidad.mvc.service.ProductoService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {

    private final ProductoService service = new ProductoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = usuarioEnSesion(request.getSession(false));
        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.setAttribute("usuario", usuario);
        String accion = request.getParameter("accion");

        if ("nuevo".equals(accion)) {
            if (!esAdmin(usuario)) {
                request.setAttribute("mensaje", "No autorizado para crear productos");
                reenviar(request, response, "/WEB-INF/views/error.jsp");
                return;
            }
            request.setAttribute("producto", new Producto());
            reenviar(request, response, "/WEB-INF/views/formulario.jsp");
            return;
        }

        if ("editar".equals(accion)) {
            if (!esAdmin(usuario)) {
                request.setAttribute("mensaje", "No autorizado para editar productos");
                reenviar(request, response, "/WEB-INF/views/error.jsp");
                return;
            }
            int id = parseInt(request.getParameter("id"));
            Producto producto = service.buscarPorId(id).orElse(new Producto());
            request.setAttribute("producto", producto);
            reenviar(request, response, "/WEB-INF/views/formulario.jsp");
            return;
        }

        if ("eliminar".equals(accion)) {
            if (!esAdmin(usuario)) {
                request.setAttribute("mensaje", "No autorizado para eliminar productos");
                reenviar(request, response, "/WEB-INF/views/error.jsp");
                return;
            }
            int id = parseInt(request.getParameter("id"));
            service.eliminar(id);
            response.sendRedirect(request.getContextPath() + "/productos");
            return;
        }

        request.setAttribute("productos", service.listar());
        reenviar(request, response, "/WEB-INF/views/lista.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Usuario usuario = usuarioEnSesion(request.getSession(false));
        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        if (!esAdmin(usuario)) {
            request.setAttribute("mensaje", "No autorizado para guardar productos");
            reenviar(request, response, "/WEB-INF/views/error.jsp");
            return;
        }

        try {
            Producto producto = new Producto();
            producto.setId(parseInt(request.getParameter("id")));
            producto.setNombre(texto(request.getParameter("nombre")));
            producto.setCategoria(texto(request.getParameter("categoria")));
            producto.setPrecio(parseDouble(request.getParameter("precio")));
            producto.setStock(parseInt(request.getParameter("stock")));

            validar(producto);

            service.guardar(producto);
            response.sendRedirect(request.getContextPath() + "/productos");
        } catch (IllegalArgumentException ex) {
            request.setAttribute("mensaje", ex.getMessage());
            reenviar(request, response, "/WEB-INF/views/error.jsp");
        }
    }

    private void reenviar(HttpServletRequest request, HttpServletResponse response, String vista) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(vista);
        rd.forward(request, response);
    }

    private int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private String texto(String value) {
        return value == null ? "" : value.trim();
    }

    private void validar(Producto producto) {
        if (producto.getNombre().isEmpty() || producto.getNombre().length() < 3) {
            throw new IllegalArgumentException("El nombre debe tener al menos 3 caracteres");
        }
        if (producto.getCategoria().isEmpty()) {
            throw new IllegalArgumentException("La categoria es obligatoria");
        }
        if (producto.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        if (producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
    }

    private Usuario usuarioEnSesion(HttpSession session) {
        if (session == null) {
            return null;
        }
        Object obj = session.getAttribute("usuario");
        if (obj instanceof Usuario usuario) {
            return usuario;
        }
        return null;
    }

    private boolean esAdmin(Usuario usuario) {
        return "ADMIN".equalsIgnoreCase(usuario.getRol());
    }
}
