<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css" />
</head>
<body>
<fmt:setLocale value="${sessionScope.lang == null ? 'es' : sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<div class="container" style="max-width:420px;">
    <h1><fmt:message key="title.login" /></h1>

    <c:if test="${not empty error}">
        <p style="color:#b02a37;">${error}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <label><fmt:message key="label.username" /></label>
        <input type="text" name="username" required />

        <label><fmt:message key="label.password" /></label>
        <input type="password" name="password" required />

        <button type="submit"><fmt:message key="label.login" /></button>
    </form>

    <p>
        <a href="${pageContext.request.contextPath}/idioma?lang=es">ES</a> |
        <a href="${pageContext.request.contextPath}/idioma?lang=en">EN</a>
    </p>
    <p style="font-size:0.9rem; color:#596579;">admin/admin123 - viewer/viewer123</p>
</div>
</body>
</html>
