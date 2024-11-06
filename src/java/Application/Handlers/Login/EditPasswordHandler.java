package Application.Handlers.Login;

import Business.Services.Usuario.EditarContraseñaService;
import Business.Exceptions.UsuarioNoEncontradoException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/editPassword")
public class EditPasswordHandler extends HttpServlet {

    private final EditarContraseñaService editarContraseñaService;

    public EditPasswordHandler() {
        this.editarContraseñaService = new EditarContraseñaService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String nuevaContraseña = request.getParameter("password");

        try {
            editarContraseñaService.editarContraseña(email, nuevaContraseña);
            request.setAttribute("message", "Contraseña actualizada con éxito.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (UsuarioNoEncontradoException e) {
            request.setAttribute("error", "No se ha encontrado un usuario con ese correo electrónico.");
            request.getRequestDispatcher("/Views/Forms/Login/Edit_Password.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Ha ocurrido un error en la base de datos: " + e.getMessage());
            request.getRequestDispatcher("/Views/Forms/Login/Edit_Password.jsp").forward(request, response);
        }
    }
}
