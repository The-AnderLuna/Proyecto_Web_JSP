package Business.Contracts.IUsuario;

import Domain.Model.Usuario;
import Business.Exceptions.DuplicateUserException;
import java.sql.SQLException;

/**
 * * Interface específica para guardar usuarios
 *
 *
 * @autor Ander
 */
public interface IGuardarUsuario {

    int guardarUsuario(Usuario usuario) throws SQLException, DuplicateUserException;
}
