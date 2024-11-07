package Business.Services.Curso;

import Business.Contracts.ICurso.IListarCursosInscritosPorUsuarioId;
import Domain.Model.Curso;
import java.sql.SQLException;
import java.util.List;

/**
 * Servicio para listar los cursos en los que un usuario está inscrito.
 *
 * @autor Ander
 */
public class ListarCursosInscritosPorUsuarioIdService {

    private final IListarCursosInscritosPorUsuarioId listarCursosInscritosPorUsuarioIdRepository;

    /**
     * Constructor que inyecta el repositorio de listar cursos inscritos por
     * usuario ID.
     *
     * @param listarCursosInscritosPorUsuarioIdRepository El repositorio para
     * listar los cursos inscritos por usuario ID.
     */
    public ListarCursosInscritosPorUsuarioIdService(IListarCursosInscritosPorUsuarioId listarCursosInscritosPorUsuarioIdRepository) {
        this.listarCursosInscritosPorUsuarioIdRepository = listarCursosInscritosPorUsuarioIdRepository;
    }

    /**
     * Lista todos los cursos en los que un usuario está inscrito utilizando el
     * repositorio.
     *
     * @param usuarioId El ID del usuario.
     * @return Una lista de objetos Curso.
     * @throws SQLException Si ocurre un error al interactuar con la base de
     * datos.
     */
    public List<Curso> listarCursosInscritosPorUsuarioId(int usuarioId) throws SQLException {
        return listarCursosInscritosPorUsuarioIdRepository.listarCursosInscritosPorUsuarioId(usuarioId);
    }
}
