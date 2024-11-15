package Application.Handlers.Curso;

import Business.Services.Curso.BuscarCursoService;
import Business.Services.Curso.EditarCursoService;
import Domain.Model.Curso;
import Infrastructure.Persistence.CursoPersistence;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/buscarYEditarCurso")
public class BuscarYEditarCursoHandler extends HttpServlet {

    private final BuscarCursoService buscarCursoService;
    private final EditarCursoService editarCursoService;

    public BuscarYEditarCursoHandler() {
        this.buscarCursoService = new BuscarCursoService(new CursoPersistence());
        this.editarCursoService = new EditarCursoService(new CursoPersistence());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cursoIdStr = request.getParameter("cursoId");

        // Si hay un id, estamos actualizando el curso; de lo contrario, estamos buscando el curso
        String id = request.getParameter("id");
        if (id != null) {
            try {
                Curso curso = new Curso();
                curso.setId(Integer.parseInt(id));
                curso.setNombre(request.getParameter("nombre"));
                curso.setDuracion(Float.parseFloat(request.getParameter("duracion")));
                curso.setEstudiantes(Integer.parseInt(request.getParameter("estudiantes")));

                String fechaInicioStr = request.getParameter("fechaInicio");
                if (fechaInicioStr == null || fechaInicioStr.isEmpty()) {
                    throw new ParseException("La fecha de inicio no puede estar vacía", 0);
                }

                curso.setFechaInicio(new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicioStr));
                curso.setUsuarioId(Integer.parseInt(request.getParameter("usuarioId")));

                editarCursoService.editarCurso(curso);

                request.setAttribute("message", "Curso actualizado con éxito.");
                request.getRequestDispatcher("/Views/Forms/Cursos/editar_curso.jsp").forward(request, response);
            } catch (SQLException | ParseException e) {
                request.setAttribute("error", "Error al actualizar el curso: " + e.getMessage());
                request.getRequestDispatcher("/Views/Forms/Cursos/editar_curso.jsp").forward(request, response);
            }
        } else {
            try {
                Curso curso = buscarCursoService.buscarCursoPorId(Integer.parseInt(cursoIdStr));

                request.setAttribute("id", curso.getId());
                request.setAttribute("nombre", curso.getNombre());
                request.setAttribute("duracion", curso.getDuracion());
                request.setAttribute("estudiantes", curso.getEstudiantes());
                request.setAttribute("fechaInicio", new SimpleDateFormat("yyyy-MM-dd").format(curso.getFechaInicio()));
                request.setAttribute("usuarioId", curso.getUsuarioId());

                request.getRequestDispatcher("/Views/Forms/Cursos/editar_curso.jsp").forward(request, response);
            } catch (SQLException e) {
                request.setAttribute("error", "Curso no encontrado: " + e.getMessage());
                request.getRequestDispatcher("/Views/Forms/Cursos/editar_curso.jsp").forward(request, response);
            }
        }
    }
}
