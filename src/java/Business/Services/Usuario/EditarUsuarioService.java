package Business.Services.Usuario;

import Business.Contracts.IUsuario.IEditarUsuario;
import Business.Exceptions.UsuarioNoEncontradoException;
import Domain.Model.Usuario;
import Infrastructure.Persistence.UsuarioPersistence;
import java.sql.SQLException;

/**
 * Clase de servicio para editar un usuario existente.
 *
 * @autor Ander
 */
public class EditarUsuarioService implements IEditarUsuario {

    private final UsuarioPersistence usuarioPersistence;

    /**
     * Constructor que inicializa el servicio con una implementación de
     * UsuarioPersistence.
     */
    public EditarUsuarioService() {
        this.usuarioPersistence = new UsuarioPersistence();
    }

    /**
     * Edita un usuario existente en la base de datos utilizando la persistencia
     * de usuarios.
     *
     * @param usuario El objeto Usuario que se va a editar.
     * @throws SQLException Si ocurre un error en la base de datos.
     * @throws UsuarioNoEncontradoException Si el usuario no se encuentra.
     */
    @Override
    public void editarUsuario(Usuario usuario) throws SQLException, UsuarioNoEncontradoException {
        usuarioPersistence.editarUsuario(usuario);
    }
}
