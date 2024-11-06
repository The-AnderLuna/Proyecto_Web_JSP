package Business.Contracts.IUsuario;

import Business.Exceptions.UsuarioNoEncontradoException;
import Domain.Model.Usuario;
import java.sql.SQLException;

/**
 * Interface específica para editarUsuario
 *
 * @author Ander
 */
public interface IEditarUsuario {

    void editarUsuario(Usuario usuario) throws SQLException, UsuarioNoEncontradoException;

}
