package Application.Handlers.Usuario;

import Business.Exceptions.UsuarioNoEncontradoException;
import Business.Services.Usuario.BuscarUsuarioPorEmailService;
import Business.Services.Usuario.EditarUsuarioService;
import Domain.Model.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@WebServlet("/buscarYEditarUsuario")
public class BuscarYEditarUsuarioHandler extends HttpServlet {

    private final BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;
    private final EditarUsuarioService editarUsuarioService;

    public BuscarYEditarUsuarioHandler() {
        this.buscarUsuarioPorEmailService = new BuscarUsuarioPorEmailService();
        this.editarUsuarioService = new EditarUsuarioService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        // Si hay un id, estamos actualizando el usuario; de lo contrario, estamos buscando al usuario
        String id = request.getParameter("id");
        if (id != null) {
            try {
                Usuario usuario = new Usuario();
                usuario.setId(Integer.parseInt(id));
                usuario.setNombre(request.getParameter("nombre"));
                usuario.setApellidos(request.getParameter("apellidos"));
                usuario.setEmail(request.getParameter("emailOriginal"));  // Utilizamos el email original que no puede cambiar
                usuario.setTelefono(request.getParameter("telefono"));
                usuario.setPassword(request.getParameter("passwordOriginal"));  // Utilizamos la contraseña original que no puede cambiar
                usuario.setRol(request.getParameter("rol"));
                usuario.setEstado(request.getParameter("estado"));
                usuario.setFechaRegistro(new Date());

                editarUsuarioService.editarUsuario(usuario);

                request.setAttribute("message", "Usuario actualizado con éxito.");
                request.getRequestDispatcher("/Views/Forms/Usuarios/editar_usuario.jsp").forward(request, response);
            } catch (SQLException | UsuarioNoEncontradoException e) {
                request.setAttribute("error", "Error al actualizar el usuario: " + e.getMessage());
                request.getRequestDispatcher("/Views/Forms/Usuarios/editar_usuario.jsp").forward(request, response);
            }
        } else {
            try {
                Usuario usuario = buscarUsuarioPorEmailService.buscarUsuarioPorEmail(email);

                request.setAttribute("id", usuario.getId());
                request.setAttribute("nombre", usuario.getNombre());
                request.setAttribute("apellidos", usuario.getApellidos());
                request.setAttribute("email", usuario.getEmail());
                request.setAttribute("telefono", usuario.getTelefono());
                request.setAttribute("password", usuario.getPassword());
                request.setAttribute("rol", usuario.getRol());
                request.setAttribute("estado", usuario.getEstado());

                request.getRequestDispatcher("/Views/Forms/Usuarios/editar_usuario.jsp").forward(request, response);
            } catch (SQLException | UsuarioNoEncontradoException e) {
                request.setAttribute("error", "Usuario no encontrado: " + e.getMessage());
                request.getRequestDispatcher("/Views/Forms/Usuarios/editar_usuario.jsp").forward(request, response);
            }
        }
    }
}
