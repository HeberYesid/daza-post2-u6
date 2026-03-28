<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Productos</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css" />
</head>
<body>
<fmt:setLocale value="${sessionScope.lang == null ? 'es' : sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<div class="container">
    <div style="display:flex;justify-content:space-between;align-items:center;gap:1rem;">
        <h1><fmt:message key="title.products" /></h1>
        <div>
            <span><fmt:message key="label.welcome" />: ${usuario.username} (${usuario.rol})</span>
            |
            <a href="${pageContext.request.contextPath}/idioma?lang=es">ES</a>
            <a href="${pageContext.request.contextPath}/idioma?lang=en">EN</a>
            |
            <a href="${pageContext.request.contextPath}/logout"><fmt:message key="label.logout" /></a>
        </div>
    </div>

    <c:if test="${usuario.rol == 'VIEWER'}">
        <p style="color:#6b7280;"><fmt:message key="msg.viewerLimited" /></p>
    </c:if>

    <c:if test="${usuario.rol == 'ADMIN'}">
        <a class="btn" href="${pageContext.request.contextPath}/productos?accion=nuevo"><fmt:message key="label.newProduct" /></a>
    </c:if>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th><fmt:message key="label.name" /></th>
            <th><fmt:message key="label.category" /></th>
            <th><fmt:message key="label.price" /></th>
            <th><fmt:message key="label.stock" /></th>
            <th><fmt:message key="label.actions" /></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${productos}" var="p">
            <tr>
                <td>${p.id}</td>
                <td>${p.nombre}</td>
                <td>${p.categoria}</td>
                <td>${p.precio}</td>
                <td>${p.stock}</td>
                <td>
                    <c:if test="${usuario.rol == 'ADMIN'}">
                        <a href="${pageContext.request.contextPath}/productos?accion=editar&id=${p.id}"><fmt:message key="label.edit" /></a>
                        |
                        <a href="${pageContext.request.contextPath}/productos?accion=eliminar&id=${p.id}" onclick="return confirm('¿Eliminar?');"><fmt:message key="label.delete" /></a>
                    </c:if>
                    <c:if test="${usuario.rol != 'ADMIN'}">-</c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
