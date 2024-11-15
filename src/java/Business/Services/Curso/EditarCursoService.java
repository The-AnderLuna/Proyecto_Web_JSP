package Business.Services.Curso;

import Business.Contracts.ICurso.IEditarCurso;
import Domain.Model.Curso;
import java.sql.SQLException;

/**
 * Servicio para editar un curso.
 */
public class EditarCursoService implements IEditarCurso {

    private final IEditarCurso editarCursoRepository;

    public EditarCursoService(IEditarCurso editarCursoRepository) {
        this.editarCursoRepository = editarCursoRepository;
    }

    @Override
    public void editarCurso(Curso curso) throws SQLException {
        editarCursoRepository.editarCurso(curso);
    }
}
