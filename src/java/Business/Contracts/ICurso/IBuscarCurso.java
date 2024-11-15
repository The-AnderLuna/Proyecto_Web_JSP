package Business.Contracts.ICurso;

import Domain.Model.Curso;
import java.sql.SQLException;

/**
 * Interface específica para buscar cursos por ID.
 *
 * @autor Ander
 */
public interface IBuscarCurso {

    Curso buscarCurso(int id) throws SQLException;
}
