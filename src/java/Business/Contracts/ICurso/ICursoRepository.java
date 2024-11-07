package Business.Contracts.ICurso;

import Domain.Model.Curso;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface para el repositorio de cursos. Define métodos para operaciones CRUD
 * sobre cursos.
 *
 * @autor Ander
 */
public interface ICursoRepository {

    /**
     * Guarda un curso en la base de datos.
     *
     * @param curso El objeto Curso que se va a guardar.
     * @return El ID del curso guardado.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    int guardarCurso(Curso curso) throws SQLException;

    /**
     * Busca un curso por su ID en la base de datos.
     *
     * @param id El ID del curso a buscar.
     * @return El objeto Curso si se encuentra.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    Curso buscarCursoPorId(int id) throws SQLException;

    /**
     * Edita un curso existente en la base de datos.
     *
     * @param curso El objeto Curso que se va a editar.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    void editarCurso(Curso curso) throws SQLException;

    /**
     * Elimina un curso de la base de datos.
     *
     * @param id El ID del curso a eliminar.
     * @param usuarioId El ID del usuario que creó el curso.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    void eliminarCurso(int id, int usuarioId) throws SQLException;

    /**
     * Lista todos los cursos en la base de datos.
     *
     * @return Una lista de objetos Curso.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    List<Curso> listarCursos() throws SQLException;

    /**
     * Lista todos los cursos creados por un usuario específico.
     *
     * @param usuarioId El ID del usuario que creó los cursos.
     * @return Una lista de objetos Curso.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    List<Curso> listarCursosPorUsuarioId(int usuarioId) throws SQLException;

    /**
     * Lista todos los cursos en los que un usuario está inscrito.
     *
     * @param usuarioId El ID del usuario inscrito en los cursos.
     * @return Una lista de objetos Curso.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    List<Curso> listarCursosInscritosPorUsuarioId(int usuarioId) throws SQLException;
}
