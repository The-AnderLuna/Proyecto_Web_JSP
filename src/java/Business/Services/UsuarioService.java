package Business.Services;

import Domain.Model.Usuario;
import Infrastructure.Persistence.UsuarioPersistence;
import Business.Exceptions.DuplicateUserException;
import Business.Exceptions.UsuarioNoEncontradoException;
import java.util.List;

import java.sql.SQLException;

/**
 * Servicio para manejar la lógica de negocio relacionada con los usuarios.
 */
public class UsuarioService {

    private final UsuarioPersistence usuarioPersistence;

    // Constructor para inyectar la dependencia de persistencia
    public UsuarioService(UsuarioPersistence usuarioPersistence) {
        this.usuarioPersistence = usuarioPersistence;
    }

    /**
     * Método para guardar un usuario, delegando la operación a la capa de
     * persistencia.
     *
     * @param usuario El usuario a guardar
     * @throws SQLException Si ocurre un error de base de datos
     * @throws DuplicateUserException Si el usuario ya existe (clave duplicada)
     */
    public void guardarUsuario(Usuario usuario) throws SQLException, DuplicateUserException {
        usuarioPersistence.guardarUsuario(usuario);
    }

    /**
     * Método para buscar un usuario por email.
     *
     * @param email El email del usuario a buscar
     * @return El usuario encontrado
     * @throws SQLException Si ocurre un error de base de datos
     * @throws UsuarioNoEncontradoException Si el usuario no existe
     */
    public Usuario buscarUsuarioPorEmail(String email) throws SQLException, UsuarioNoEncontradoException {
        return usuarioPersistence.buscarUsuarioPorEmail(email);
    }

    /**
     * Método para contar el número de usuarios.
     *
     * @return El número total de usuarios
     * @throws SQLException Si ocurre un error de base de datos
     */
    public int contarUsuarios() throws SQLException {
        return usuarioPersistence.contarUsuarios();
    }

    /**
     * Método para editar un usuario existente.
     *
     * @param usuario El usuario a editar
     * @throws SQLException Si ocurre un error de base de datos
     * @throws UsuarioNoEncontradoException Si el usuario no existe
     */
    public void editarUsuario(Usuario usuario) throws SQLException, UsuarioNoEncontradoException {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo.");
        }
        usuarioPersistence.editarUsuario(usuario);
    }

    /**
     * Método para listar todos los usuarios.
     *
     * @return Lista de usuarios
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public List<Usuario> listarUsuarios() throws SQLException {
        return usuarioPersistence.listarUsuarios();
    }

    /**
     * Método para editar la contraseña de un usuario por email.
     *
     * @param email El email del usuario.
     * @param nuevaContraseña La nueva contraseña.
     * @throws SQLException Si ocurre un error de base de datos.
     * @throws UsuarioNoEncontradoException Si el usuario no existe.
     */
    public void editarContraseña(String email, String nuevaContraseña) throws SQLException, UsuarioNoEncontradoException {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El email no puede ser nulo o vacío.");
        }
        if (nuevaContraseña == null || nuevaContraseña.isEmpty()) {
            throw new IllegalArgumentException("La nueva contraseña no puede ser nula o vacía.");
        }
        usuarioPersistence.editarContraseña(email, nuevaContraseña);
    }
}
