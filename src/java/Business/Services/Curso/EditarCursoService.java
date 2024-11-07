package Business.Services.Curso;

import Business.Contracts.ICurso.IEditarCurso;
import Domain.Model.Curso;
import java.sql.SQLException;

/**
 * Servicio para editar un curso.
 *
 * @autor Ander
 */
public class EditarCursoService {

    private final IEditarCurso editarCursoRepository;

    /**
     * Constructor que inyecta el repositorio de editar curso.
     *
     * @param editarCursoRepository El repositorio para editar cursos.
     */
    public EditarCursoService(IEditarCurso editarCursoRepository) {
        this.editarCursoRepository = editarCursoRepository;
    }

    /**
     * Edita un curso utilizando el repositorio.
     *
     * @param curso El curso a editar.
     * @throws SQLException Si ocurre un error al interactuar con la base de
     * datos.
     */
    public void editarCurso(Curso curso) throws SQLException {
        editarCursoRepository.editarCurso(curso);
    }
}
