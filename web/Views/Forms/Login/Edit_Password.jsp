<%-- 
    Document   : Edit_Password
    Created on : 5 nov 2024, 21:25:18
    Author     : Ander
--%>

<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Editar Contraseña</title>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/Views/Css/styles.css">
    </head>
    <body>
        <div class="container">
            <h1>Editar Contraseña</h1>
            <form action="<%= request.getContextPath()%>/editPassword" method="post">
                <input type="hidden" name="email" value="<%= request.getParameter("email")%>">
                <label for="password">Nueva Contraseña:</label>
                <input type="password" id="password" name="password" required>
                <button type="submit">Actualizar Contraseña</button>
            </form>
        </div>
    </body>
</html>
