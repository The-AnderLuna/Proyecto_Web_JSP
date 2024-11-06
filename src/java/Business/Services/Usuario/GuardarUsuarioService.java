package Business.Services.Usuario;

import Business.Contracts.IUsuario.IGuardarUsuario;
import Business.Exceptions.DuplicateUserException;
import Domain.Model.Usuario;
import Infrastructure.Persistence.UsuarioPersistence;
import java.sql.SQLException;

/**
 * Clase de servicio para guardar usuarios.
 *
 * @autor Ander
 */
public class GuardarUsuarioService implements IGuardarUsuario {

    private final UsuarioPersistence usuarioPersistence;

    /**
     * Constructor que inicializa el servicio con una implementación de
     * UsuarioPersistence.
     */
    public GuardarUsuarioService() {
        this.usuarioPersistence = new UsuarioPersistence();
    }

    /**
     * Guarda un usuario en la base de datos utilizando la persistencia de
     * usuarios.
     *
     * @param usuario El objeto Usuario que se va a guardar.
     * @return El ID del usuario guardado.
     * @throws SQLException Si ocurre un error en la base de datos.
     * @throws DuplicateUserException Si el usuario ya existe.
     */
    @Override
    public int guardarUsuario(Usuario usuario) throws SQLException, DuplicateUserException {
        return usuarioPersistence.guardarUsuario(usuario);
    }
}
