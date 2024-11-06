package Business.Contracts.IUsuario;

import Business.Exceptions.UsuarioNoEncontradoException;
import Domain.Model.Usuario;
import java.sql.SQLException;

/**
 * Interface específica para buscarUsuarioPorEmail
 *
 * @author Ander
 */
public interface IBuscarUsuarioPorEmail {

    Usuario buscarUsuarioPorEmail(String email) throws SQLException, UsuarioNoEncontradoException;
}
