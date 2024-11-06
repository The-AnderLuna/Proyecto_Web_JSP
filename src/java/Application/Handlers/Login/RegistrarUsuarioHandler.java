package Application.Handlers.Login;

import Business.Services.Usuario.GuardarUsuarioService;
import Business.Exceptions.DuplicateUserException;
import Domain.Model.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@WebServlet("/registrar")
public class RegistrarUsuarioHandler extends HttpServlet {

    private final GuardarUsuarioService guardarUsuarioService;

    public RegistrarUsuarioHandler() {
        this.guardarUsuarioService = new GuardarUsuarioService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Views/Forms/Login/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String rol = request.getParameter("rol");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String telefono = request.getParameter("telefono");

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setRol(rol);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setTelefono(telefono);
        usuario.setFechaRegistro(new Date());

        try {
            int id = guardarUsuarioService.guardarUsuario(usuario);
            request.setAttribute("message", "Usuario registrado con éxito.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Error en la base de datos. Intenta nuevamente.");
            request.getRequestDispatcher("/Views/Forms/Login/register.jsp").forward(request, response);
        } catch (DuplicateUserException e) {
            request.setAttribute("error", "El usuario con el correo electrónico o teléfono ya existe.");
            request.getRequestDispatcher("/Views/Forms/Login/register.jsp").forward(request, response);
        }
    }
}
