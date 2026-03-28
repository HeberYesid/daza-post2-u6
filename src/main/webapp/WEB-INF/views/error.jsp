<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css" />
</head>
<body>
<fmt:setLocale value="${sessionScope.lang == null ? 'es' : sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<div class="container">
    <h1><fmt:message key="title.error" /></h1>
    <p>${mensaje}</p>
    <a class="btn-sec" href="${pageContext.request.contextPath}/productos"><fmt:message key="label.back" /></a>
</div>
</body>
</html>
