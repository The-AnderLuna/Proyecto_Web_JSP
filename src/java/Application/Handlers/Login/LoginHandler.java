package Application.Handlers.Login;

import Business.Services.Login.AutenticarUsuarioService;
import Business.Exceptions.UsuarioNoEncontradoException;
import Domain.Model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/loginHandler")
public class LoginHandler extends HttpServlet {

    private final AutenticarUsuarioService autenticarUsuarioService;

    public LoginHandler() {
        this.autenticarUsuarioService = new AutenticarUsuarioService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            response.sendRedirect("index.jsp?error=empty_fields");
            return;
        }

        try {
            Usuario usuario = autenticarUsuarioService.autenticarUsuario(email, password);

            HttpSession session = request.getSession();
            session.setAttribute("usuarioNombre", usuario.getNombre());
            session.setAttribute("usuarioApellidos", usuario.getApellidos());
            session.setAttribute("usuarioEmail", usuario.getEmail());
            session.setAttribute("usuarioRol", usuario.getRol()); // Asegúrate de que el rol se guarda en la sesión

            response.sendRedirect(request.getContextPath() + "/Views/Forms/Login/Welcome.jsp");
        } catch (UsuarioNoEncontradoException e) {
            response.sendRedirect(request.getContextPath() + "/index.jsp?error=invalid_credentials");
        } catch (SQLException e) {
            response.sendRedirect(request.getContextPath() + "/index.jsp?error=database_error");
        }
    }
}
