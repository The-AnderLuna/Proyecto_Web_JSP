package Business.Services.Usuario;

import Business.Contracts.IUsuario.IListarUsuarios;
import Domain.Model.Usuario;
import Infrastructure.Persistence.UsuarioPersistence;
import java.sql.SQLException;
import java.util.List;

/**
 * Clase de servicio para listar todos los usuarios.
 *
 * @autor Ander
 */
public class ListarUsuariosService implements IListarUsuarios {

    private final UsuarioPersistence usuarioPersistence;

    /**
     * Constructor que inicializa el servicio con una implementación de
     * UsuarioPersistence.
     */
    public ListarUsuariosService() {
        this.usuarioPersistence = new UsuarioPersistence();
    }

    /**
     * Lista todos los usuarios en la base de datos utilizando la persistencia
     * de usuarios.
     *
     * @return Una lista de objetos Usuario.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    @Override
    public List<Usuario> listarUsuarios() throws SQLException {
        return usuarioPersistence.listarUsuarios();
    }
}
