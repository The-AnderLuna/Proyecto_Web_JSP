package Business.Contracts.ICurso;

import Domain.Model.Curso;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface específica para listar cursos por usuario.
 *
 * @autor Ander
 */
public interface IListarCursosPorUsuarioId {

    List<Curso> listarCursosPorUsuarioId(int usuarioId) throws SQLException;
}
