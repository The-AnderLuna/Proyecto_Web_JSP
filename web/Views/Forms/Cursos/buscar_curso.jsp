<%-- 
    Document   : buscar_curso
    Created on : 12 nov 2024, 21:26:57
    Author     : Ander
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Buscar Curso</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/Views/Css/styles.css">
    </head>
    <body>
        <div class="container">
            <h1>Buscar Curso</h1>

            <!-- Mostrar mensaje de error -->
            <c:if test="${not empty error}">
                <p class="error">${error}</p>
            </c:if>

            <!-- Formulario para buscar curso -->
            <form action="<%= request.getContextPath()%>/buscarCurso" method="post">
                <div>
                    <label for="cursoId">ID del Curso:</label>
                    <input type="text" id="cursoId" name="cursoId" required>
                </div>
                <button type="submit">Buscar Curso</button>
            </form>

            <c:if test="${not empty curso}">
                <h2>Detalle del Curso</h2>
                <p><strong>ID:</strong> ${curso.id}</p>
                <p><strong>Nombre:</strong> ${curso.nombre}</p>
                <p><strong>Duración:</strong> ${curso.duracion} horas</p>
                <p><strong>Número de Estudiantes:</strong> ${curso.estudiantes}</p>
                <p><strong>Fecha de Inicio:</strong> ${curso.fechaInicio}</p>
                <p><strong>Usuario ID:</strong> ${curso.usuarioId}</p>
            </c:if>

            <p><a href="<%= request.getContextPath()%>/Views/Forms/Cursos/Curso.jsp">Volver</a></p>
        </div>
    </body>
</html>

