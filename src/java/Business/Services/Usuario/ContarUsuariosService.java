package Business.Services.Usuario;

import Business.Contracts.IUsuario.IContarUsuarios;
import Infrastructure.Persistence.UsuarioPersistence;
import java.sql.SQLException;

/**
 * Clase de servicio para contar el número de usuarios.
 *
 * @autor Ander
 */
public class ContarUsuariosService implements IContarUsuarios {

    private final UsuarioPersistence usuarioPersistence;

    /**
     * Constructor que inicializa el servicio con una implementación de
     * UsuarioPersistence.
     */
    public ContarUsuariosService() {
        this.usuarioPersistence = new UsuarioPersistence();
    }

    /**
     * Cuenta el número total de usuarios en la base de datos utilizando la
     * persistencia de usuarios.
     *
     * @return El número total de usuarios.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    @Override
    public int contarUsuarios() throws SQLException {
        return usuarioPersistence.contarUsuarios();
    }
}
