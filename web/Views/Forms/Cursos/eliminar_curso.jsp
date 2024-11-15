<%-- 
    Document   : eliminar_curso
    Created on : 12 nov 2024, 21:41:51
    Author     : Ander
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Eliminar Curso</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/Views/Css/styles.css">
    </head>
    <body>
        <div class="container">
            <h1>Eliminar Curso</h1>

            <!-- Mostrar mensaje de éxito o error -->
            <c:if test="${not empty message}">
                <p class="success">${message}</p>
            </c:if>
            <c:if test="${not empty error}">
                <p class="error">${error}</p>
            </c:if>

            <!-- Formulario para eliminar curso -->
            <form action="<%= request.getContextPath()%>/eliminarCurso" method="post">
                <div>
                    <label for="cursoId">ID del Curso:</label>
                    <input type="text" id="cursoId" name="cursoId" required>
                </div>
                <button type="submit">Eliminar Curso</button>
            </form>

            <p><a href="<%= request.getContextPath()%>/Views/Forms/Cursos/Curso.jsp">Volver</a></p>
        </div>
    </body>
</html>

