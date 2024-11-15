<%-- 
    Document   : listar_usuario
    Created on : 13 nov 2024, 0:51:28
    Author     : Ander
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Domain.Model.Usuario" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Listar Usuarios</title>
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

            .fecha-registro {
                width: 180px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Lista de Usuarios</h1>

            <!-- Mensaje de error si ocurre algún problema -->
            <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
            %>
            <p class="error"><%= error%></p>
            <%
                }
            %>

            <!-- Tabla para mostrar la lista de usuarios -->
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Apellidos</th>
                        <th>Email</th>
                        <th>Teléfono</th>
                        <th>Rol</th>
                        <th>Estado</th>
                        <th class="fecha-registro">Fecha de Registro</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
                        if (usuarios != null && !usuarios.isEmpty()) {
                            for (Usuario usuario : usuarios) {
                    %>
                    <tr>
                        <td><%= usuario.getId()%></td>
                        <td><%= usuario.getNombre()%></td>
                        <td><%= usuario.getApellidos()%></td>
                        <td><%= usuario.getEmail()%></td>
                        <td><%= usuario.getTelefono()%></td>
                        <td><%= usuario.getRol()%></td>
                        <td><%= usuario.getEstado()%></td>
                        <td class="fecha-registro"><%= usuario.getFechaRegistro()%></td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="8">No se encontraron usuarios.</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>

            <p><a href="<%= request.getContextPath()%>/Views/Forms/Usuarios/Usuario.jsp">Volver</a></p>
        </div>
    </body>
</html>
