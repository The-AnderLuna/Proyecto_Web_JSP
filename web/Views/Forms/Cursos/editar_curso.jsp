<%-- 
    Document   : editar_curso
    Created on : 13 nov 2024, 0:51:15
    Author     : Ander
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Buscar y Editar Curso</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/Views/Css/styles.css">
    </head>
    <body>
        <div class="container">
            <h1>Buscar y Editar Curso</h1>

            <!-- Formulario para buscar curso por ID -->
            <form action="<%= request.getContextPath()%>/buscarYEditarCurso" method="post">
                <div>
                    <label for="cursoId">ID del Curso:</label>
                    <input type="text" id="cursoId" name="cursoId" required>
                </div>
                <button type="submit">Buscar Curso</button>
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

            <!-- Formulario de edición que solo se muestra si el curso es encontrado -->
            <%
                String nombreCurso = (String) request.getAttribute("nombre");
                if (nombreCurso != null) {
            %>
            <form action="<%= request.getContextPath()%>/buscarYEditarCurso" method="post">
                <input type="hidden" name="id" value="<%= request.getAttribute("id")%>">
                <div>
                    <label for="nombre">Nombre del Curso:</label>
                    <input type="text" id="nombre" name="nombre" value="<%= nombreCurso%>" required>
                </div>
                <div>
                    <label for="duracion">Duración (horas):</label>
                    <input type="number" id="duracion" name="duracion" value="<%= request.getAttribute("duracion")%>" step="0.5" required>
                </div>
                <div>
                    <label for="estudiantes">Número de Estudiantes:</label>
                    <input type="number" id="estudiantes" name="estudiantes" value="<%= request.getAttribute("estudiantes")%>">
                </div>
                <div>
                    <label for="fechaInicio">Fecha de Inicio:</label>
                    <input type="date" id="fechaInicio" name="fechaInicio" value="<%= request.getAttribute("fechaInicio")%>" required>
                </div>
                <div>
                    <label for="usuarioId">ID del Profesor:</label>
                    <input type="text" id="usuarioId" name="usuarioId" value="<%= request.getAttribute("usuarioId")%>" required>
                </div>
                <button type="submit">Actualizar Curso</button>
            </form>
            <%
                }
            %>

            <p><a href="<%= request.getContextPath()%>/Views/Forms/Cursos/Curso.jsp">Volver</a></p>
        </div>
    </body>
</html>
