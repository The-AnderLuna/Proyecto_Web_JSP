package Business.Contracts.ICurso;

import Domain.Model.Curso;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface específica para Listar Cursos Inscritos Por Usuario
 *
 * @author Ander
 */
public interface IListarCursosInscritosPorUsuarioId {

    List<Curso> listarCursosInscritosPorUsuarioId(int usuarioId) throws SQLException;
}
