package Application.Handlers.Usuario;

import Business.Services.Usuario.ListarUsuariosService;
import Domain.Model.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listarUsuarios")
public class ListarUsuariosHandler extends HttpServlet {

    private final ListarUsuariosService listarUsuariosService;

    public ListarUsuariosHandler() {
        this.listarUsuariosService = new ListarUsuariosService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Usuario> usuarios = listarUsuariosService.listarUsuarios();
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("/Views/Forms/Usuarios/listar_usuario.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Error al listar los usuarios: " + e.getMessage());
            request.getRequestDispatcher("/Views/Forms/Usuarios/listar_usuario.jsp").forward(request, response);
        }
    }
}
