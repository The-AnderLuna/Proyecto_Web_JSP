package Application.Handlers.Login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Obtener la sesión sin crear una nueva si no existe
        if (session != null) {
            session.invalidate(); // Invalidar la sesión
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp"); // Redirigir a la página de inicio de sesión
    }
}
