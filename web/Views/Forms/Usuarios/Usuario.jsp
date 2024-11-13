<%-- 
    Document   : Usuario
    Created on : 5 nov 2024, 22:03:21
    Author     : Ander
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Usuarios</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/Views/Css/styles.css">
    </head>
    <body>
        <%
            String nombre = (String) session.getAttribute("usuarioNombre");
            if (nombre == null) {
                response.sendRedirect(request.getContextPath() + "/index.jsp?error=not_logged_in");
                return;
            }
        %>
        <div class="container">
            <h1>Usuarios</h1>
            <p>Aquí puedes gestionar los usuarios.</p>

            <!-- Botón para Crear Usuario -->
            <form action="<%= request.getContextPath()%>/Views/Forms/Usuarios/crear_usuario.jsp" method="get">
                <button type="submit">Crear Usuario</button>
            </form>

            <!-- Botón para Buscar Usuario -->
            <form action="<%= request.getContextPath()%>/Views/Forms/Usuarios/buscar_usuario.jsp" method="get">
                <button type="submit">Buscar Usuario</button>
            </form>

            <!-- Botón para Editar Usuario -->
            <form action="<%= request.getContextPath()%>/Views/Forms/Usuarios/editar_usuario.jsp" method="get">
                <button type="submit">Editar Usuario</button>
            </form>

            <!-- Botón para Listar Usuarios -->
            <form action="<%= request.getContextPath()%>/Views/Forms/Usuarios/listar_usuario.jsp" method="post">
                <button type="submit">Listar Usuarios</button>
            </form>

            <p><a href="<%= request.getContextPath()%>/Views/Forms/Login/Welcome.jsp">Volver</a></p>
        </div>
    </body>
</html>

