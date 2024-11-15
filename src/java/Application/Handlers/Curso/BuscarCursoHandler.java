package Application.Handlers.Curso;

import Business.Contracts.ICurso.ICursoRepository;
import Business.Services.Curso.BuscarCursoService;
import Domain.Model.Curso;
import Infrastructure.Persistence.CursoPersistence;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/buscarCurso")
public class BuscarCursoHandler extends HttpServlet {

    private final BuscarCursoService buscarCursoService;

    public BuscarCursoHandler() {
        ICursoRepository cursoRepository = new CursoPersistence();
        this.buscarCursoService = new BuscarCursoService(cursoRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Views/Forms/Cursos/buscar_curso.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cursoIdStr = request.getParameter("cursoId");

        if (cursoIdStr == null || cursoIdStr.isEmpty()) {
            request.setAttribute("error", "El ID del curso es obligatorio.");
            request.getRequestDispatcher("/Views/Forms/Cursos/buscar_curso.jsp").forward(request, response);
            return;
        }

        try {
            int cursoId = Integer.parseInt(cursoIdStr);
            Curso curso = buscarCursoService.buscarCursoPorId(cursoId);

            if (curso == null) {
                request.setAttribute("error", "Curso no encontrado.");
            } else {
                request.setAttribute("curso", curso);
            }
            request.getRequestDispatcher("/Views/Forms/Cursos/buscar_curso.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "El ID del curso debe ser un número.");
            request.getRequestDispatcher("/Views/Forms/Cursos/buscar_curso.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Error al buscar el curso: " + e.getMessage());
            request.getRequestDispatcher("/Views/Forms/Cursos/buscar_curso.jsp").forward(request, response);
        }
    }
}
