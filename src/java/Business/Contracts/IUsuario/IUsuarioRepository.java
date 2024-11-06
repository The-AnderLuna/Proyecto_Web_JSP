package Business.Contracts.IUsuario;

import Domain.Model.Usuario;
import Business.Exceptions.DuplicateUserException;
import Business.Exceptions.UsuarioNoEncontradoException;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface para el repositorio de usuarios. Define métodos para operaciones
 * CRUD sobre usuarios.
 *
 * @autor Ander
 */
public interface IUsuarioRepository {

    /**
     * Guarda un usuario en la base de datos.
     *
     * @param usuario El objeto Usuario que se va a guardar.
     * @return El ID del usuario guardado.
     * @throws SQLException Si ocurre un error en la base de datos.
     * @throws DuplicateUserException Si el usuario ya existe.
     */
    int guardarUsuario(Usuario usuario) throws SQLException, DuplicateUserException;

    /**
     * Busca un usuario por su email en la base de datos.
     *
     * @param email El email del usuario a buscar.
     * @return El objeto Usuario si se encuentra.
     * @throws SQLException Si ocurre un error en la base de datos.
     * @throws UsuarioNoEncontradoException Si el usuario no se encuentra.
     */
    Usuario buscarUsuarioPorEmail(String email) throws SQLException, UsuarioNoEncontradoException;

    /**
     * Cuenta el número total de usuarios en la base de datos.
     *
     * @return El número total de usuarios.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    int contarUsuarios() throws SQLException;

    /**
     * Edita un usuario existente en la base de datos.
     *
     * @param usuario El objeto Usuario que se va a editar.
     * @throws SQLException Si ocurre un error en la base de datos.
     * @throws UsuarioNoEncontradoException Si el usuario no se encuentra.
     */
    void editarUsuario(Usuario usuario) throws SQLException, UsuarioNoEncontradoException;

    /**
     * Lista todos los usuarios en la base de datos.
     *
     * @return Una lista de objetos Usuario.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    List<Usuario> listarUsuarios() throws SQLException;

    /**
     * Edita la contraseña de un usuario en la base de datos.
     *
     * @param email El email del usuario.
     * @param nuevaContraseña La nueva contraseña del usuario.
     * @throws SQLException Si ocurre un error en la base de datos.
     * @throws UsuarioNoEncontradoException Si el usuario no se encuentra.
     */
    void editarContraseña(String email, String nuevaContraseña) throws SQLException, UsuarioNoEncontradoException;

}
