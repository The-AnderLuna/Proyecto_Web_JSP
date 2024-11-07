package Business.Services.Curso;

import Business.Contracts.ICurso.IListarCursos;
import Domain.Model.Curso;
import java.sql.SQLException;
import java.util.List;

/**
 * Servicio para listar cursos.
 *
 * @autor Ander
 */
public class ListarCursosService {

    private final IListarCursos listarCursosRepository;

    /**
     * Constructor que inyecta el repositorio de listar cursos.
     *
     * @param listarCursosRepository El repositorio para listar cursos.
     */
    public ListarCursosService(IListarCursos listarCursosRepository) {
        this.listarCursosRepository = listarCursosRepository;
    }

    /**
     * Lista todos los cursos utilizando el repositorio.
     *
     * @return Una lista de objetos Curso.
     * @throws SQLException Si ocurre un error al interactuar con la base de
     * datos.
     */
    public List<Curso> listarCursos() throws SQLException {
        return listarCursosRepository.listarCursos();
    }
}
