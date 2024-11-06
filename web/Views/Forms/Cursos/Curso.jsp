<%-- 
    Document   : Curso
    Created on : 5 nov 2024, 22:06:20
    Author     : Ander
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Cursos</title>
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
            <h1>Cursos</h1>
            <p>Aquí puedes gestionar los cursos.</p>

            <!-- Botón para Crear Curso -->
            <form action="<%= request.getContextPath()%>/crear_curso.jsp" method="get">
                <button type="submit">Crear Curso</button>
            </form>

            <!-- Botón para Buscar Curso -->
            <form action="<%= request.getContextPath()%>/buscar_curso.jsp" method="get">
                <button type="submit">Buscar Curso</button>
            </form>

            <!-- Botón para Editar Curso -->
            <form action="<%= request.getContextPath()%>/editar_curso.jsp" method="get">
                <button type="submit">Editar Curso</button>
            </form>

            <!-- Botón para Listar Cursos -->
            <form action="<%= request.getContextPath()%>/Application/Handlers/Handle_Listar_Cursos" method="post">
                <button type="submit">Listar Cursos</button>
            </form>

            <!-- Botón para Eliminar Curso -->
            <form action="<%= request.getContextPath()%>/eliminar_curso.jsp" method="get">
                <button type="submit">Eliminar Curso</button>
            </form>

            <p><a href="<%= request.getContextPath()%>/Views/Forms/Login/Welcome.jsp">Volver</a></p>
        </div>
    </body>
</html>

