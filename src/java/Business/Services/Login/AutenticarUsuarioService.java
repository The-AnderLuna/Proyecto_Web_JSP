package Business.Services.Login;

import Business.Contracts.ILogin.IAutenticarUsuario;
import Business.Exceptions.UsuarioNoEncontradoException;
import Domain.Model.Usuario;
import Infrastructure.Persistence.UsuarioPersistence;
import java.sql.SQLException;

/**
 * Clase de servicio para autenticar un usuario.
 *
 * @autor Ander
 */
public class AutenticarUsuarioService implements IAutenticarUsuario {

    private final UsuarioPersistence usuarioPersistence;

    /**
     * Constructor que inicializa el servicio con una implementación de
     * UsuarioPersistence.
     */
    public AutenticarUsuarioService() {
        this.usuarioPersistence = new UsuarioPersistence();
    }

    /**
     * Autentica un usuario durante el login utilizando la persistencia de
     * usuarios.
     *
     * @param email El email del usuario.
     * @param password La contraseña del usuario.
     * @return El objeto Usuario si la autenticación es exitosa.
     * @throws SQLException Si ocurre un error en la base de datos.
     * @throws UsuarioNoEncontradoException Si el usuario no es encontrado o la
     * contraseña no coincide.
     */
    @Override
    public Usuario autenticarUsuario(String email, String password) throws SQLException, UsuarioNoEncontradoException {
        return usuarioPersistence.autenticarUsuario(email, password);
    }
}
