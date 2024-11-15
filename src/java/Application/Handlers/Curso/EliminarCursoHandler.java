package Application.Handlers.Curso;

import Business.Services.Curso.EliminarCursoService;
import Business.Services.Curso.BuscarCursoService;
import Infrastructure.Persistence.CursoPersistence;
import Domain.Model.Curso;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/eliminarCurso")
public class EliminarCursoHandler extends HttpServlet {

    private final EliminarCursoService eliminarCursoService;
    private final BuscarCursoService buscarCursoService;

    public EliminarCursoHandler() {
        CursoPersistence cursoPersistence = new CursoPersistence();
        this.eliminarCursoService = new EliminarCursoService(cursoPersistence);
        this.buscarCursoService = new BuscarCursoService(cursoPersistence);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Verificar la sesión
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuarioId") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp?error=not_logged_in");
            return;
        }

        try {
            // Obtener parámetros
            int cursoId = Integer.parseInt(request.getParameter("cursoId"));
            int usuarioId = (Integer) session.getAttribute("usuarioId");

            // Verificar que el curso pertenece al usuario
            Curso curso = buscarCursoService.buscarCursoPorId(cursoId);
            if (curso != null && curso.getUsuarioId() == usuarioId) {
                // Eliminar el curso
                eliminarCursoService.eliminarCurso(cursoId, usuarioId);
                response.sendRedirect(request.getContextPath()
                        + "/listarCursosPorUsuario?mensaje=Curso eliminado exitosamente");
            } else {
                // El curso no pertenece al usuario
                request.setAttribute("error", "No tienes permiso para eliminar este curso.");
                request.getRequestDispatcher("/Views/Forms/Cursos/eliminar_curso.jsp")
                        .forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID de curso inválido");
            request.getRequestDispatcher("/Views/Forms/Cursos/eliminar_curso.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Error al eliminar el curso: " + e.getMessage());
            request.getRequestDispatcher("/Views/Forms/Cursos/eliminar_curso.jsp")
                    .forward(request, response);
        }
    }
}
