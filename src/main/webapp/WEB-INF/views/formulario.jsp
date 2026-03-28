<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <%@
taglib prefix="c" uri="jakarta.tags.core" %> <%@ taglib prefix="fmt"
uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <title>Formulario Producto</title>
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/css/estilos.css"
    />
  </head>
  <body>
    <fmt:setLocale
      value="${sessionScope.lang == null ? 'es' : sessionScope.lang}"
    />
    <fmt:setBundle basename="messages" />
    <div class="container">
      <h1>
        <c:choose>
          <c:when test="${producto.id == 0}"
            ><fmt:message key="title.newProduct"
          /></c:when>
          <c:otherwise><fmt:message key="title.editProduct" /></c:otherwise>
        </c:choose>
      </h1>

      <form action="${pageContext.request.contextPath}/productos" method="post">
        <input type="hidden" name="id" value="${producto.id}" />

        <label><fmt:message key="label.name" /></label>
        <input type="text" name="nombre" value="${producto.nombre}" required />

        <label><fmt:message key="label.category" /></label>
        <input
          type="text"
          name="categoria"
          value="${producto.categoria}"
          required
        />

        <label><fmt:message key="label.price" /></label>
        <input
          type="number"
          step="0.01"
          min="0"
          name="precio"
          value="${producto.precio}"
          required
        />

        <label><fmt:message key="label.stock" /></label>
        <input
          type="number"
          min="0"
          name="stock"
          value="${producto.stock}"
          required
        />

        <button type="submit"><fmt:message key="label.save" /></button>
        <a class="btn-sec" href="${pageContext.request.contextPath}/productos"
          ><fmt:message key="label.cancel"
        /></a>
      </form>
    </div>
  </body>
</html>
