<%-- 
    Document   : home
    Created on : 31 oct 2024, 21:10:58
    Author     : Ander
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Bienvenido</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Views/Css/styles.css">
</head>
<body>
    <%
        String nombre = (String) session.getAttribute("usuarioNombre");
        String apellidos = (String) session.getAttribute("usuarioApellidos");
        if (nombre == null || apellidos == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp?error=not_logged_in");
            return;
        }
    %>
    <div class="container">
        <h1>Bienvenido, <%= nombre + " " + apellidos %></h1>
        <div class="options1">
            <a href="<%= request.getContextPath() %>/Views/Forms/Usuarios/Usuario.jsp" class="option">Usuarios</a>
            <a href="<%= request.getContextPath() %>/Views/Forms/Cursos/Curso.jsp" class="option">Cursos</a>
            <a href="<%= request.getContextPath() %>/logout" class="option logout">Cerrar Sesión</a>
        </div>
    </div>
    <style>
            .options1 {
                display: flex;
                justify-content: center;
                gap: 20px;
                margin-top: 20px;
            }
        .option {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 4px;
            text-align: center;
        }
        .option:hover {
            background-color: #0056b3;
        }
        .logout {
            background-color: #dc3545; 
        }
        .logout:hover {
            background-color: #c82333;
        }
    </style>
</body>
</html>
