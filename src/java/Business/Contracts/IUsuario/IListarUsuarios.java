package Business.Contracts.IUsuario;

import Domain.Model.Usuario;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface específica para listarUsuarios
 *
 * @author Ander
 */
public interface IListarUsuarios {

    List<Usuario> listarUsuarios() throws SQLException;

}
