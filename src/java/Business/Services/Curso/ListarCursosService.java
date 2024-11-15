package Business.Services.Curso;

import Business.Contracts.ICurso.IListarCursos;
import Domain.Model.Curso;
import java.sql.SQLException;
import java.util.List;

/**
 * Servicio para listar todos los cursos.
 */
public class ListarCursosService implements IListarCursos {

    private final IListarCursos listarCursosRepository;

    /**
     * Constructor que inyecta el repositorio de cursos.
     *
     * @param listarCursosRepository El repositorio para listar los cursos.
     */
    public ListarCursosService(IListarCursos listarCursosRepository) {
        this.listarCursosRepository = listarCursosRepository;
    }

    /**
     * Lista todos los cursos utilizando el repositorio.
     *
     * @return Una lista de todos los cursos.
     * @throws SQLException Si ocurre un error al interactuar con la base de
     * datos.
     */
    @Override
    public List<Curso> listarCursos() throws SQLException {
        return listarCursosRepository.listarCursos();
    }
}
