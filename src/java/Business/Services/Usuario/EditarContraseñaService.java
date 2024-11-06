package Business.Services.Usuario;

import Business.Contracts.IUsuario.IEditarContraseña;
import Business.Exceptions.UsuarioNoEncontradoException;
import Infrastructure.Persistence.UsuarioPersistence;
import java.sql.SQLException;

/**
 * Clase de servicio para editar la contraseña de un usuario.
 *
 * @autor Ander
 */
public class EditarContraseñaService implements IEditarContraseña {

    private final UsuarioPersistence usuarioPersistence;

    /**
     * Constructor que inicializa el servicio con una implementación de
     * UsuarioPersistence.
     */
    public EditarContraseñaService() {
        this.usuarioPersistence = new UsuarioPersistence();
    }

    /**
     * Edita la contraseña de un usuario en la base de datos utilizando la
     * persistencia de usuarios.
     *
     * @param email El email del usuario.
     * @param nuevaContraseña La nueva contraseña del usuario.
     * @throws SQLException Si ocurre un error en la base de datos.
     * @throws UsuarioNoEncontradoException Si el usuario no se encuentra.
     */
    @Override
    public void editarContraseña(String email, String nuevaContraseña) throws SQLException, UsuarioNoEncontradoException {
        usuarioPersistence.editarContraseña(email, nuevaContraseña);
    }
}
