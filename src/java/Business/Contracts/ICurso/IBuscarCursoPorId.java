package Business.Contracts.ICurso;

import Domain.Model.Curso;
import java.sql.SQLException;

/**
 * Interface específica para Buscar curso
 *
 * @author Ander
 */
public interface IBuscarCursoPorId {

    Curso buscarCursoPorId(int id) throws SQLException;

}
