<%-- 
    Document   : buscar_usuario
    Created on : 13 nov 2024, 0:51:01
    Author     : Ander
--%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Buscar Usuario</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/Views/Css/styles.css">
    </head>
    <body>
        <div class="container">
            <h1>Buscar Usuario por Email</h1>
            <form action="<%= request.getContextPath()%>/buscarUsuario" method="post">
                <div>
                    <label for="email">Correo Electrónico:</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <button type="submit">Buscar Usuario</button>
                <p><a href="<%= request.getContextPath()%>/Views/Forms/Usuarios/Usuario.jsp">Volver</a></p>
            </form>
            <%
                String message = (String) request.getAttribute("message");
                String error = (String) request.getAttribute("error");
                Map<String, Object> usuarioMap = (Map<String, Object>) request.getAttribute("usuarioMap");
            %>
            <%
                if (message != null) {
            %>
            <p class="success"><%= message%></p>
            <div>
                <p>ID: <%= usuarioMap.get("id")%></p>
                <p>Nombre: <%= usuarioMap.get("nombre")%></p>
                <p>Apellidos: <%= usuarioMap.get("apellidos")%></p>
                <p>Email: <%= usuarioMap.get("email")%></p>
                <p>Teléfono: <%= usuarioMap.get("telefono")%></p>
                <p>Rol: <%= usuarioMap.get("rol")%></p>
                <p>Estado: <%= usuarioMap.get("estado")%></p>
                <p>Fecha de Registro: <%= usuarioMap.get("fechaRegistro")%></p>
            </div>
            <%
            } else if (error != null) {
            %>
            <p class="error"><%= error%></p>
            <%
                }
            %>
        </div>
    </body>
</html>
