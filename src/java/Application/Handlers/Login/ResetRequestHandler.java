package Application.Handlers.Login;

import Business.Services.Usuario.BuscarUsuarioPorEmailService;
import Business.Exceptions.UsuarioNoEncontradoException;
import Domain.Model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/resetRequest")
public class ResetRequestHandler extends HttpServlet {

    private final BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    public ResetRequestHandler() {
        this.buscarUsuarioPorEmailService = new BuscarUsuarioPorEmailService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        try {
            Usuario usuario = buscarUsuarioPorEmailService.buscarUsuarioPorEmail(email);
            response.sendRedirect(request.getContextPath() + "/Views/Forms/Login/Edit_Password.jsp?email=" + email);
        } catch (UsuarioNoEncontradoException e) {
            request.setAttribute("error", "No se ha encontrado un usuario con ese correo electrónico.");
            request.getRequestDispatcher("/Views/Forms/Login/Reset_Request.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Ha ocurrido un error inesperado: " + e.getMessage());
            request.getRequestDispatcher("/Views/Forms/Login/Reset_Request.jsp").forward(request, response);
        }
    }
}
