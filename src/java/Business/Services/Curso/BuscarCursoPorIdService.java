package Business.Services.Curso;

import Business.Contracts.ICurso.IBuscarCursoPorId;
import Domain.Model.Curso;
import java.sql.SQLException;

/**
 * Servicio para buscar un curso por ID.
 *
 * @autor Ander
 */
public class BuscarCursoPorIdService {

    private final IBuscarCursoPorId buscarCursoPorIdRepository;

    /**
     * Constructor que inyecta el repositorio de buscar curso por ID.
     *
     * @param buscarCursoPorIdRepository El repositorio para buscar cursos por
     * ID.
     */
    public BuscarCursoPorIdService(IBuscarCursoPorId buscarCursoPorIdRepository) {
        this.buscarCursoPorIdRepository = buscarCursoPorIdRepository;
    }

    /**
     * Busca un curso por su ID utilizando el repositorio.
     *
     * @param id El ID del curso a buscar.
     * @return El curso encontrado, o null si no se encuentra.
     * @throws SQLException Si ocurre un error al interactuar con la base de
     * datos.
     */
    public Curso buscarCursoPorId(int id) throws SQLException {
        return buscarCursoPorIdRepository.buscarCursoPorId(id);
    }
}
