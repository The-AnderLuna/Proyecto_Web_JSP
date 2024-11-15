package Business.Contracts.ICurso;

import Domain.Model.Curso;
import java.sql.SQLException;

/**
 * Interface específica para guardar cursos.
 *
 * @autor Ander
 */
public interface IGuardarCurso {

    void guardarCurso(Curso curso) throws SQLException;
}
    