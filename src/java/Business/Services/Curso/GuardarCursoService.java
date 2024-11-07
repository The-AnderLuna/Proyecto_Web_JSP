package Business.Services.Curso;

import Business.Contracts.ICurso.IGuardarCurso;
import Domain.Model.Curso;
import java.sql.SQLException;

/**
 * Servicio para guardar un curso.
 *
 * @autor Ander
 */
public class GuardarCursoService {

    private final IGuardarCurso guardarCursoRepository;

    /**
     * Constructor que inyecta el repositorio de guardar curso.
     *
     * @param guardarCursoRepository El repositorio para guardar cursos.
     */
    public GuardarCursoService(IGuardarCurso guardarCursoRepository) {
        this.guardarCursoRepository = guardarCursoRepository;
    }

    /**
     * Guarda un curso utilizando el repositorio.
     *
     * @param curso El curso a guardar.
     * @return El ID del curso guardado.
     * @throws SQLException Si ocurre un error al interactuar con la base de
     * datos.
     */
    public int guardarCurso(Curso curso) throws SQLException {
        return guardarCursoRepository.guardarCurso(curso);
    }
}
