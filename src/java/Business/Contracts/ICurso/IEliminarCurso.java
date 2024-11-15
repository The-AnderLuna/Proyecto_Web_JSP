package Business.Contracts.ICurso;

import java.sql.SQLException;

/**
 * Interface específica para eliminar cursos.
 *
 * @autor Ander
 */
public interface IEliminarCurso {

    void eliminarCurso(int id, int usuarioId) throws SQLException;
}
