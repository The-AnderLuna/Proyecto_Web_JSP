<%-- 
    Document   : Reset_Request
    Created on : 31 oct 2024, 18:27:23
    Author     : Ander
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Recuperar Contraseña</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/Views/Css/styles.css">
    </head>
    <body>
        <div class="container">
            <h1>Recuperar Contraseña</h1>
            <c:if test="${not empty error}">
                <p class="error">${error}</p>
            </c:if>
            <form action="<%= request.getContextPath()%>/resetRequest" method="post">
                <label for="email">Correo Electrónico:</label>
                <input type="email" id="email" name="email" required>
                <button type="submit">Buscar Correo</button>
                <p><a href="<%= request.getContextPath()%>/index.jsp">Volver al inicio</a></p>
            </form>
        </div>
    </body>
</html>

