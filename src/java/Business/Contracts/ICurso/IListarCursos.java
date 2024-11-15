package Business.Contracts.ICurso;

import Domain.Model.Curso;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface específica para listar cursos.
 *
 * @autor Ander
 */
public interface IListarCursos {

    List<Curso> listarCursos() throws SQLException;
}
