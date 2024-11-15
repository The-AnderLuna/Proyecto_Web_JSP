package Application.Handlers.Curso;

import Business.Services.Curso.ListarCursosPorUsuarioIdService;
import Domain.Model.Curso;
import Infrastructure.Persistence.CursoPersistence;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listarCursosPorUsuario")
public class ListarCursosPorUsuarioHandler extends HttpServlet {

    private final ListarCursosPorUsuarioIdService listarCursosPorUsuarioIdService;

    public ListarCursosPorUsuarioHandler() {
        this.listarCursosPorUsuarioIdService = new ListarCursosPorUsuarioIdService(new CursoPersistence());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuarioId") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp?error=not_logged_in");
            return;
        }

        Integer usuarioId = (Integer) session.getAttribute("usuarioId");
        System.out.println("Usuario ID en sesión: " + usuarioId); // Mensaje de depuración
        try {
            List<Curso> cursos = listarCursosPorUsuarioIdService.listarCursosPorUsuarioId(usuarioId);
            System.out.println("Cursos encontrados: " + cursos.size()); // Mensaje de depuración
            for (Curso curso : cursos) {
                System.out.println(curso); // Imprimir cada curso para verificar
            }
            request.setAttribute("cursos", cursos);
            request.getRequestDispatcher("/Views/Forms/Cursos/listar_curso.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Error al listar los cursos: " + e.getMessage());
            request.getRequestDispatcher("/Views/Forms/Cursos/listar_curso.jsp").forward(request, response);
        }
    }
}
