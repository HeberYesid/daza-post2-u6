package com.universidad.mvc.controller;

import com.universidad.mvc.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final Map<String, String> CREDS = Map.of(
            "admin", "admin123",
            "viewer", "viewer123"
    );

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null || !CREDS.containsKey(username) || !CREDS.get(username).equals(password)) {
            request.setAttribute("error", "Credenciales inválidas");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            return;
        }

        String rol = "admin".equals(username) ? "ADMIN" : "VIEWER";
        Usuario usuario = new Usuario(username, username + "@demo.local", rol);
        HttpSession session = request.getSession();
        session.setAttribute("usuario", usuario);

        response.sendRedirect(request.getContextPath() + "/productos");
    }
}
