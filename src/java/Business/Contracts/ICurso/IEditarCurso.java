package Business.Contracts.ICurso;

import Domain.Model.Curso;
import java.sql.SQLException;

/**
 * Interface específica para Guardar curso
 *
 * @author Ander
 */
public interface IEditarCurso {

    void editarCurso(Curso curso) throws SQLException;
}
