<%-- 
    Document   : crear_curso
    Created on : 12 nov 2024, 21:08:42
    Author     : Ander
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Crear Curso</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/Views/Css/styles.css">
    </head>
    <body>
        <div class="container">
            <h1>Crear Curso</h1>

            <!-- Mostrar mensaje de éxito o error -->
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

            <!-- Formulario para crear curso -->
            <form action="<%= request.getContextPath()%>/crearCurso" method="post">
                <div>
                    <label for="nombre">Nombre del Curso:</label>
                    <input type="text" id="nombre" name="nombre" required>
                </div>
                <div>
                    <label for="duracion">Duración (horas):</label>
                    <input type="number" id="duracion" name="duracion" step="0.1" required>
                </div>
                <div>
                    <label for="estudiantes">Número de Estudiantes:</label>
                    <input type="number" id="estudiantes" name="estudiantes" required>
                </div>
                <div>
                    <label for="fecha_inicio">Fecha de Inicio:</label>
                    <input type="date" id="fecha_inicio" name="fecha_inicio" required>
                </div>
                <button type="submit">Crear Curso</button>
            </form>

            <p><a href="<%= request.getContextPath()%>/Views/Forms/Cursos/Curso.jsp">Volver</a></p>
        </div>
    </body>
</html>
