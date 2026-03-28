# daza-post2-u6

Proyecto Java Web MVC tradicional extendido con autenticación, validaciones robustas e internacionalización (es/en).

## Arquitectura

- Model: Producto, ProductoDAO, Usuario
- Service: ProductoService
- Controller: ProductoServlet, LoginServlet, LogoutServlet, IdiomaServlet
- Views JSP: login, lista, formulario, error

## Funcionalidades

- Listar productos
- Crear producto
- Editar producto
- Eliminar producto
- Login con HttpSession (admin/viewer)
- Restricciones por rol (viewer solo lectura)
- Selector de idioma persistido en sesión
- Validaciones en servidor y PRG

## Prerrequisitos

- JDK 17+
- Maven 3.8+
- Tomcat 10.x

## Ejecución

1. `mvn clean package`
2. Desplegar el WAR en Tomcat
3. Navegar a `/mvc-productos-auth-i18n/login`

## Capturas

Agregar evidencias en `capturas/`.
