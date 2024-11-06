package Business.Contracts.ILogin;

import Domain.Model.Usuario;
import Business.Exceptions.UsuarioNoEncontradoException;
import java.sql.SQLException;

/**
 * Interface para autenticar un usuario.
 *
 * @autor Ander
 */
public interface IAutenticarUsuario {

    /**
     * Método para autenticar un usuario durante el login.
     *
     * @param email El email del usuario.
     * @param password La contraseña del usuario.
     * @return El objeto Usuario si la autenticación es exitosa.
     * @throws SQLException Si ocurre un error en la base de datos.
     * @throws UsuarioNoEncontradoException Si el usuario no es encontrado o la
     * contraseña no coincide.
     */
    Usuario autenticarUsuario(String email, String password) throws SQLException, UsuarioNoEncontradoException;
}
