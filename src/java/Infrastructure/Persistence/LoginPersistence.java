package Infrastructure.Persistence;

import Domain.Model.Usuario;
import Infrastructure.Database.ConnectionMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import Business.Exceptions.UsuarioNoEncontradoException;
import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Clase para manejar la persistencia de datos de login en la base de datos.
 *
 * @autor Ander
 */
public class LoginPersistence {

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
        public Usuario autenticarUsuario(String email, String password) throws SQLException, UsuarioNoEncontradoException {
        String sql = "SELECT * FROM Usuarios WHERE email = ?";

        try (Connection conn = ConnectionMySql.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password");

                    // Verificar contraseña
                    BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), storedPassword);
                    if (result.verified) {
                        return new Usuario(
                                rs.getInt("id"),
                                storedPassword,
                                rs.getString("nombre"),
                                rs.getString("apellidos"),
                                rs.getString("rol"),
                                rs.getString("email"),
                                rs.getString("telefono"),
                                rs.getString("estado"),
                                rs.getDate("fecha_registro")
                        );
                    } else {
                        throw new UsuarioNoEncontradoException("La contraseña es incorrecta.");
                    }
                } else {
                    throw new UsuarioNoEncontradoException("Usuario con email " + email + " no encontrado.");
                }
            }
        }
    }
}
