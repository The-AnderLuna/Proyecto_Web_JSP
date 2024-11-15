<%-- 
    Document   : editar_usuario
    Created on : 13 nov 2024, 0:51:15
    Author     : Ander
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Buscar y Editar Usuario</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/Views/Css/styles.css">
    </head>
    <body>
        <div class="container">
            <h1>Buscar y Editar Usuario</h1>

            <!-- Formulario para buscar usuario por correo -->
            <form action="<%= request.getContextPath()%>/buscarYEditarUsuario" method="post">
                <div>
                    <label for="email">Correo Electrónico:</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <button type="submit">Buscar Usuario</button>
            </form>

            <!-- Mensaje de éxito o error -->
            <%
                String message = (String) request.getAttribute("message");
                String error = (String) request.getAttribute("error");
                if (message != null) {
            %>
            <p class="success"><%= message%></p>
            <%
            } else if (error != null) {
            %>
            <p class="error"><%= error%></p>
            <%
                }
            %>

            <!-- Formulario de edición que solo se muestra si el usuario es encontrado -->
            <%
                String nombre = (String) request.getAttribute("nombre");
                if (nombre != null) {
            %>
            <form action="<%= request.getContextPath()%>/buscarYEditarUsuario" method="post">
                <input type="hidden" name="id" value="<%= request.getAttribute("id")%>">
                <input type="hidden" name="emailOriginal" value="<%= request.getAttribute("email")%>">
                <input type="hidden" name="passwordOriginal" value="<%= request.getAttribute("password")%>">
                <div>
                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" value="<%= nombre%>" required>
                </div>
                <div>
                    <label for="apellidos">Apellidos:</label>
                    <input type="text" id="apellidos" name="apellidos" value="<%= request.getAttribute("apellidos")%>" required>
                </div>
                <div>
                    <label for="telefono">Teléfono:</label>
                    <input type="text" id="telefono" name="telefono" value="<%= request.getAttribute("telefono")%>">
                </div>
                <div>
                    <label for="rol">Rol:</label>
                    <select id="rol" name="rol" required>
                        <option value="estudiante" <%= "estudiante".equals(request.getAttribute("rol")) ? "selected" : ""%>>Estudiante</option>
                        <option value="profesor" <%= "profesor".equals(request.getAttribute("rol")) ? "selected" : ""%>>Profesor</option>
                    </select>
                </div>
                <div>
                    <label for="estado">Estado:</label>
                    <select id="estado" name="estado" required>
                        <option value="activo" <%= "activo".equals(request.getAttribute("estado")) ? "selected" : ""%>>Activo</option>
                        <option value="inactivo" <%= "inactivo".equals(request.getAttribute("estado")) ? "selected" : ""%>>Inactivo</option>
                    </select>
                </div>
                <button type="submit">Actualizar Usuario</button>
            </form>
            <%
                }
            %>

            <p><a href="<%= request.getContextPath()%>/Views/Forms/Usuarios/Usuario.jsp">Volver</a></p>
        </div>
    </body>
</html>
