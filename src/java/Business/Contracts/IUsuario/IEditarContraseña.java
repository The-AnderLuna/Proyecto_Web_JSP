package Business.Contracts.IUsuario;

import Business.Exceptions.UsuarioNoEncontradoException;
import java.sql.SQLException;

/**
 * Interface específica para editarContraseña
 *
 * @author Ander
 */
public interface IEditarContraseña {

    void editarContraseña(String email, String nuevaContraseña) throws SQLException, UsuarioNoEncontradoException;

}
