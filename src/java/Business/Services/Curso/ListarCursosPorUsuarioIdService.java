package Business.Services.Curso;

import Business.Contracts.ICurso.IListarCursosPorUsuarioId;
import Domain.Model.Curso;
import java.sql.SQLException;
import java.util.List;

/**
 * Servicio para listar los cursos creados por un usuario específico.
 *
 * @autor Ander
 */
public class ListarCursosPorUsuarioIdService {

    private final IListarCursosPorUsuarioId listarCursosPorUsuarioIdRepository;

    /**
     * Constructor que inyecta el repositorio de listar cursos por usuario ID.
     *
     * @param listarCursosPorUsuarioIdRepository El repositorio para listar los
     * cursos por usuario ID.
     */
    public ListarCursosPorUsuarioIdService(IListarCursosPorUsuarioId listarCursosPorUsuarioIdRepository) {
        this.listarCursosPorUsuarioIdRepository = listarCursosPorUsuarioIdRepository;
    }

    /**
     * Lista todos los cursos creados por un usuario específico utilizando el
     * repositorio.
     *
     * @param usuarioId El ID del usuario.
     * @return Una lista de objetos Curso.
     * @throws SQLException Si ocurre un error al interactuar con la base de
     * datos.
     */
    public List<Curso> listarCursosPorUsuarioId(int usuarioId) throws SQLException {
        return listarCursosPorUsuarioIdRepository.listarCursosPorUsuarioId(usuarioId);
    }
}
