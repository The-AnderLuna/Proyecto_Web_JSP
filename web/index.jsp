<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Iniciar Sesión</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/Views/Css/styles.css">
    </head>
    <body>
        <div class="container">
            <h1>Iniciar Sesión</h1>
            <c:if test="${not empty message}">
                <p class="success">${message}</p>
            </c:if>
            <c:if test="${not empty error}">
                <p class="error">${error}</p>
            </c:if>
            <form action="<%= request.getContextPath()%>/loginHandler" method="post">
                <label for="email">Correo Electrónico:</label>
                <input type="email" id="email" name="email" required>
                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required>
                <button type="submit">Iniciar Sesión</button>
            </form>
            <p>¿No tienes una cuenta? <a href="<%= request.getContextPath()%>/Views/Forms/Login/register.jsp">Regístrate aquí</a></p>
            <p><a href="<%= request.getContextPath()%>/Views/Forms/Login/Reset_Request.jsp">¿Olvidaste tu contraseña?</a></p>
        </div>
    </body>
</html>
