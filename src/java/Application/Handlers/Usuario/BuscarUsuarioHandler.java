package Application.Handlers.Usuario;

import Business.Exceptions.UsuarioNoEncontradoException;
import Business.Services.Usuario.BuscarUsuarioPorEmailService;
import Domain.Model.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/buscarUsuario")
public class BuscarUsuarioHandler extends HttpServlet {

    private final BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    public BuscarUsuarioHandler() {
        this.buscarUsuarioPorEmailService = new BuscarUsuarioPorEmailService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        try {
            Usuario usuario = buscarUsuarioPorEmailService.buscarUsuarioPorEmail(email);

            if (usuario != null) {
                Map<String, Object> usuarioMap = new HashMap<>();
                usuarioMap.put("id", usuario.getId());
                usuarioMap.put("nombre", usuario.getNombre());
                usuarioMap.put("apellidos", usuario.getApellidos());
                usuarioMap.put("email", usuario.getEmail());
                usuarioMap.put("telefono", usuario.getTelefono());
                usuarioMap.put("rol", usuario.getRol());
                usuarioMap.put("estado", usuario.getEstado());
                usuarioMap.put("fechaRegistro", usuario.getFechaRegistro());

                request.setAttribute("message", "Usuario encontrado.");
                request.setAttribute("usuarioMap", usuarioMap);
            } else {
                request.setAttribute("error", "Usuario no encontrado.");
            }
            request.getRequestDispatcher("/Views/Forms/Usuarios/buscar_usuario.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Error en la base de datos. Intenta nuevamente.");
            request.getRequestDispatcher("/Views/Forms/Usuarios/buscar_usuario.jsp").forward(request, response);
        } catch (UsuarioNoEncontradoException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/Views/Forms/Usuarios/buscar_usuario.jsp").forward(request, response);
        }
    }
}
