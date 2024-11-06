<%-- 
    Document   : register
    Created on : 31 oct 2024, 13:05:18
    Author     : Ander
--%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Registro</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/Views/Css/styles.css">
    </head>
    <body>
        <div class="container">
            <h1>Registro de Usuario</h1>
            <c:if test="${not empty error}">
                <p class="error">${error}</p>
            </c:if>
            <c:if test="${not empty message}">
                <p class="success">${message}</p>
            </c:if>
            <form action="<%= request.getContextPath()%>/registrar" method="post">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" required>

                <label for="apellidos">Apellidos:</label>
                <input type="text" id="apellidos" name="apellidos" required>

                <label for="rol">Rol:</label>
                <select id="rol" name="rol" required>
                    <option value="Profesor">Profesor</option>
                    <option value="Estudiante">Estudiante</option>
                </select>

                <label for="email">Correo Electrónico:</label>
                <input type="email" id="email" name="email" required>

                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required>

                <label for="telefono">Teléfono:</label>
                <input type="text" id="telefono" name="telefono" required>

                <button type="submit">Registrarse</button>
            </form>

            <p>¿Ya tienes una cuenta? <a href="<%= request.getContextPath()%>/index.jsp">Inicia Sesión aquí</a></p>
            <p><a href="<%= request.getContextPath()%>/index.jsp">Volver al inicio</a></p>
        </div>
    </body>
</html>
