<%-- 
    Document   : crear_usuario
    Created on : 13 nov 2024, 0:45:53
    Author     : Ander
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Crear Usuario</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/Views/Css/styles.css">
    </head>
    <body>
        <div class="container">
            <h1>Crear Nuevo Usuario</h1>
            <%
                String message = (String) request.getAttribute("message");
                String error = (String) request.getAttribute("error");
                Integer usuarioId = (Integer) request.getAttribute("usuarioId");
            %>
            <%
                if (message != null) {
            %>
            <p class="success"><%= message%></p>
            <p>ID del Usuario: <%= usuarioId%></p>
            <%
            } else if (error != null) {
            %>
            <p class="error"><%= error%></p>
            <%
                }
            %>
            <form action="<%= request.getContextPath()%>/crearUsuario" method="post">
                <div>
                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" required>
                </div>
                <div>
                    <label for="apellidos">Apellidos:</label>
                    <input type="text" id="apellidos" name="apellidos" required>
                </div>
                <div>
                    <label for="email">Correo Electrónico:</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div>
                    <label for="telefono">Teléfono:</label>
                    <input type="text" id="telefono" name="telefono" required>
                </div>
                <div>
                    <label for="password">Contraseña:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div>
                    <label for="rol">Rol:</label>
                    <select id="rol" name="rol" required>
                        <option value="estudiante">Estudiante</option>
                        <option value="profesor">Profesor</option>
                    </select>
                </div>
                <div>
                    <label for="estado">Estado:</label>
                    <select id="estado" name="estado" required>
                        <option value="activo">Activo</option>
                        <option value="inactivo">Inactivo</option>
                    </select>
                </div>
                <button type="submit">Crear Usuario</button>
            </form>
            <p><a href="<%= request.getContextPath()%>/Views/Forms/Usuarios/Usuario.jsp">Volver</a></p>
        </div>
    </body>
</html>

