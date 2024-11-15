package Business.Services.Curso;

import Business.Contracts.ICurso.IGuardarCurso;
import Domain.Model.Curso;
import java.sql.SQLException;

/**
 * Servicio para guardar un curso.
 */
public class GuardarCursoService implements IGuardarCurso {

    private final IGuardarCurso guardarCursoRepository;

    /**
     * Constructor que inyecta el repositorio de cursos.
     *
     * @param guardarCursoRepository El repositorio para guardar el curso.
     */
    public GuardarCursoService(IGuardarCurso guardarCursoRepository) {
        this.guardarCursoRepository = guardarCursoRepository;
    }

    /**
     * Guarda un curso utilizando el repositorio.
     *
     * @param curso El curso que se va a guardar.
     * @throws SQLException Si ocurre un error al interactuar con la base de
     * datos.
     */
    @Override
    public void guardarCurso(Curso curso) throws SQLException {
        guardarCursoRepository.guardarCurso(curso);
    }
}
