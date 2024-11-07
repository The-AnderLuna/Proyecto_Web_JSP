package Business.Services.Curso;

import Business.Contracts.ICurso.IEliminarCurso;
import java.sql.SQLException;

/**
 * Servicio para eliminar un curso.
 *
 * @autor Ander
 */
public class EliminarCursoService {

    private final IEliminarCurso eliminarCursoRepository;

    /**
     * Constructor que inyecta el repositorio de eliminar curso.
     *
     * @param eliminarCursoRepository El repositorio para eliminar cursos.
     */
    public EliminarCursoService(IEliminarCurso eliminarCursoRepository) {
        this.eliminarCursoRepository = eliminarCursoRepository;
    }

    /**
     * Elimina un curso utilizando el repositorio.
     *
     * @param id El ID del curso a eliminar.
     * @param usuarioId El ID del usuario que creó el curso.
     * @throws SQLException Si ocurre un error al interactuar con la base de
     * datos.
     */
    public void eliminarCurso(int id, int usuarioId) throws SQLException {
        eliminarCursoRepository.eliminarCurso(id, usuarioId);
    }
}
