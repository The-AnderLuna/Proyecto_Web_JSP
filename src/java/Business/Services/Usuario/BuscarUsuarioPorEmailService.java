package Business.Services.Usuario;

import Business.Contracts.IUsuario.IBuscarUsuarioPorEmail;
import Business.Exceptions.UsuarioNoEncontradoException;
import Domain.Model.Usuario;
import Infrastructure.Persistence.UsuarioPersistence;
import java.sql.SQLException;

/**
 * Clase de servicio para buscar usuarios por email.
 *
 * @autor Ander
 */
public class BuscarUsuarioPorEmailService implements IBuscarUsuarioPorEmail {

    private final UsuarioPersistence usuarioPersistence;

    /**
     * Constructor que inicializa el servicio con una implementación de
     * UsuarioPersistence.
     */
    public BuscarUsuarioPorEmailService() {
        this.usuarioPersistence = new UsuarioPersistence();
    }

    /**
     * Busca un usuario por su email en la base de datos utilizando la
     * persistencia de usuarios.
     *
     * @param email El email del usuario a buscar.
     * @return El objeto Usuario si se encuentra.
     * @throws SQLException Si ocurre un error en la base de datos.
     * @throws UsuarioNoEncontradoException Si el usuario no se encuentra.
     */
    @Override
    public Usuario buscarUsuarioPorEmail(String email) throws SQLException, UsuarioNoEncontradoException {
        Usuario usuario = usuarioPersistence.buscarUsuarioPorEmail(email);
        if (usuario == null) {
            throw new UsuarioNoEncontradoException("Usuario no encontrado");
        }
        return usuario;
    }
}
