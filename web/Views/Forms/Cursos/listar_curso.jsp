<%-- 
    Document   : listar_curso
    Created on : 12 nov 2024, 21:41:40
    Author     : Ander
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Domain.Model.Curso" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Mis Cursos</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/Views/Css/styles.css">
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
                margin: 20px 0;
            }
            th, td {
                padding: 12px 15px;
                border: 1px solid #ddd;
                text-align: left;
            }
            th {
                background-color: #f4f4f4;
            }
            tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            tr:hover {
                background-color: #f1f1f1;
            }
            .container {
                max-width: 800px;
                margin: auto;
                padding: 20px;
            }
            .success {
                color: green;
            }
            .error {
                color: red;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Mis Cursos</h1>
            <!-- Mensaje de error si ocurre algún problema -->
            <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
            %>
            <p class="error"><%= error%></p>
            <%
                }
            %>

            <!-- Debug info -->
            <div style="background-color: #f8f9fa; padding: 10px; margin: 10px 0; border: 1px solid #ddd;">
                <p>Usuario ID en sesión: <%= session.getAttribute("usuarioId")%></p>
            </div>

            <!-- Tabla para mostrar la lista de cursos -->
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Duración</th>
                        <th>Estudiantes</th>
                        <th>Fecha de Inicio</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Curso> cursos = (List<Curso>) request.getAttribute("cursos");
                        if (cursos != null && !cursos.isEmpty()) {
                            for (Curso curso : cursos) {
                    %>
                    <tr>
                        <td><%= curso.getId()%></td>
                        <td><%= curso.getNombre()%></td>
                        <td><%= curso.getDuracion()%> horas</td>
                        <td><%= curso.getEstudiantes()%></td>
                        <td><%= curso.getFechaInicio()%></td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="5">No se encontraron cursos.</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
            <p><a href="<%= request.getContextPath()%>/Views/Forms/Cursos/Curso.jsp">Volver</a></p>
        </div>
    </body>
</html>