package Business.Contracts.ICurso;

import Domain.Model.Curso;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface para el repositorio de cursos. Define métodos para operaciones CRUD
 * sobre cursos.
 */
public interface ICursoRepository {

    void guardarCurso(Curso curso) throws SQLException;

    Curso buscarCurso(int id) throws SQLException;

    void editarCurso(Curso curso) throws SQLException;

    void eliminarCurso(int id, int usuarioId) throws SQLException;

    List<Curso> listarCursos() throws SQLException;

    List<Curso> listarCursosPorUsuarioId(int usuarioId) throws SQLException;

    List<Curso> listarCursosInscritosPorUsuarioId(int usuarioId) throws SQLException;
}
