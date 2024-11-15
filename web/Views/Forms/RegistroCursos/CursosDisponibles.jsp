<%-- 
    Document   : CursosDisponibles
    Created on : 15 nov 2024, 9:28:14
    Author     : Ander
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Domain.Model.Curso" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Cursos Disponibles</title>
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
            /* Estilos para la ventana emergente */
            .modal {
                display: none;
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgb(0,0,0);
                background-color: rgba(0,0,0,0.4);
                padding-top: 60px;
            }
            .modal-content {
                background-color: #fefefe;
                margin: 5% auto;
                padding: 20px;
                border: 1px solid #888;
                width: 80%;
            }
            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }
            .close:hover,
            .close:focus {
                color: black;
                text-decoration: none;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Cursos Disponibles</h1>

            <!-- Mensaje de error si ocurre algún problema -->
            <c:if test="${not empty error}">
                <p class="error">${error}</p>
            </c:if>

            <!-- Mensaje de éxito -->
            <c:if test="${not empty mensaje}">
                <p class="success">${mensaje}</p>
            </c:if>

            <!-- Tabla para mostrar la lista de cursos -->
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Duración</th>
                        <th>Estudiantes</th>
                        <th>Fecha de Inicio</th>
                        <th>Acción</th>
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
                        <td>
                            <form action="<%= request.getContextPath()%>/inscribirseCurso" method="post" onsubmit="return showModal()">
                                <input type="hidden" name="cursoId" value="<%= curso.getId()%>">
                                <input type="hidden" name="usuarioId" value="<%= session.getAttribute("usuarioId")%>">
                                <button type="submit">Inscribirse</button>
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="6">No hay cursos disponibles.</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
            <p><a href="<%= request.getContextPath()%>/Views/Forms/Login/Welcome.jsp">Volver</a></p>
        </div>

        <!-- Ventana emergente -->
        <div id="myModal" class="modal">
            <div class="modal-content">
                <span class="close" onclick="closeModal()">&times;</span>
                <p>¡Gracias por inscribirte al curso!</p>
            </div>
        </div>

        <script>
            function showModal() {
                var modal = document.getElementById("myModal");
                modal.style.display = "block";
                return false;
            }
            function closeModal() {
                var modal = document.getElementById("myModal");
                modal.style.display = "none";
            }
        </script>
    </body>
</html>
