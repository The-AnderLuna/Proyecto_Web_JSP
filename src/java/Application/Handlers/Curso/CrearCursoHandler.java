package Application.Handlers.Curso;

import Business.Contracts.ICurso.IGuardarCurso;
import Business.Services.Curso.GuardarCursoService;
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

@WebServlet("/crearCurso")
public class CrearCursoHandler extends HttpServlet {

    private final GuardarCursoService guardarCursoService;

    public CrearCursoHandler() {
        // Inyectar el repositorio de cursos en el servicio
        this.guardarCursoService = new GuardarCursoService((IGuardarCurso) new CursoPersistence());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Views/Forms/Cursos/crear_curso.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Verificar si la sesión está activa
        if (session == null) {
            System.out.println("Sesión es null");
            response.sendRedirect(request.getContextPath() + "/index.jsp?error=not_logged_in");
            return;
        }

        // Verificar si el atributo usuarioId está presente en la sesión
        Integer usuarioId = (Integer) session.getAttribute("usuarioId");
        if (usuarioId == null) {
            System.out.println("usuarioId es null");
            response.sendRedirect(request.getContextPath() + "/index.jsp?error=not_logged_in");
            return;
        }

        System.out.println("Usuario ID: " + usuarioId);

        // Validar y obtener datos del formulario
        String nombre = request.getParameter("nombre");
        if (nombre == null || nombre.isEmpty()) {
            request.setAttribute("error", "El nombre del curso es obligatorio.");
            request.getRequestDispatcher("/Views/Forms/Cursos/crear_curso.jsp").forward(request, response);
            return;
        }

        float duracion;
        int estudiantes;
        Date fechaInicio;

        try {
            duracion = Float.parseFloat(request.getParameter("duracion"));
            estudiantes = Integer.parseInt(request.getParameter("estudiantes"));
            fechaInicio = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fecha_inicio"));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "La duración y el número de estudiantes deben ser valores numéricos.");
            request.getRequestDispatcher("/Views/Forms/Cursos/crear_curso.jsp").forward(request, response);
            return;
        } catch (ParseException e) {
            request.setAttribute("error", "Formato de fecha incorrecto. Use 'yyyy-MM-dd'.");
            request.getRequestDispatcher("/Views/Forms/Cursos/crear_curso.jsp").forward(request, response);
            return;
        }

        // Crear y guardar el objeto Curso
        Curso curso = new Curso(nombre, duracion, estudiantes, fechaInicio, usuarioId);

        try {
            guardarCursoService.guardarCurso(curso);
            session.setAttribute("message", "Curso creado con éxito.");
            response.sendRedirect(request.getContextPath() + "/Views/Forms/Cursos/Curso.jsp");
        } catch (SQLException e) {
            session.setAttribute("error", "Error al crear el curso: " + e.getMessage());
            request.getRequestDispatcher("/Views/Forms/Cursos/crear_curso.jsp").forward(request, response);
        }
    }
}
