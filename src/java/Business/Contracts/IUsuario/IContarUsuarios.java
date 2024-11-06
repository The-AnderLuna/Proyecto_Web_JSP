package Business.Contracts.IUsuario;

import java.sql.SQLException;

/**
 * Interface específica para contarUsuarios
 *
 * @author Ander
 */
public interface IContarUsuarios {

    int contarUsuarios() throws SQLException;

}
