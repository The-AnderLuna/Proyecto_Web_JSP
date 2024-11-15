package Application.Handlers.RegistroCurso;

import Business.Contracts.ICurso.IListarCursos;
import Business.Services.Curso.ListarCursosService;
import Infrastructure.Persistence.CursoPersistence;
import Domain.Model.Curso;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/cursosDisponibles")
public class CursosDisponiblesHandler extends HttpServlet {

    private final ListarCursosService listarCursosService;

    public CursosDisponiblesHandler() {
        this.listarCursosService = new ListarCursosService((IListarCursos) new CursoPersistence());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Curso> cursos = listarCursosService.listarCursos();
            request.setAttribute("cursos", cursos);
            request.getRequestDispatcher("/Views/Forms/RegistroCursos/CursosDisponibles.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "Error al listar los cursos disponibles: " + e.getMessage());
            request.getRequestDispatcher("/Views/Forms/RegistroCursos/CursosDisponibles.jsp").forward(request, response);
        }
    }
}
