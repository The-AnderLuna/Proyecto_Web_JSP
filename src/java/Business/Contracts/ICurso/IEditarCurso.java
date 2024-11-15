package Business.Contracts.ICurso;

import Domain.Model.Curso;
import java.sql.SQLException;

/**
 * Interface específica para editar cursos.
 *
 * @autor Ander
 */
public interface IEditarCurso {

    void editarCurso(Curso curso) throws SQLException;
}
