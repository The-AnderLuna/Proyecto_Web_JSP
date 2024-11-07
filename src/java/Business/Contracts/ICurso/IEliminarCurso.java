package Business.Contracts.ICurso;

import java.sql.SQLException;
import Domain.Model.Curso;

/**
 * Interface específica para Eliminar curso
 *
 * @author Ander
 */
public interface IEliminarCurso {

    void eliminarCurso(int id, int usuarioId) throws SQLException;

}
