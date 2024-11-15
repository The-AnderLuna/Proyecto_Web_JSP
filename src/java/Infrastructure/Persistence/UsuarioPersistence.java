package Infrastructure.Persistence;

import Business.Contracts.IUsuario.IUsuarioRepository;
import Domain.Model.Usuario;
import Infrastructure.Database.ConnectionMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import Business.Exceptions.DuplicateUserException;
import Business.Exceptions.UsuarioNoEncontradoException;
import at.favre.lib.crypto.bcrypt.BCrypt;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para manejar la persistencia de usuarios en la base de datos.
 *
 * @autor Ander
 */
public class UsuarioPersistence {

    /**
     * Método para guardar un usuario en la base de datos.
     *
     * @param usuario El objeto Usuario que se va a guardar.
     * @return El ID del usuario guardado.
     * @throws SQLException Si ocurre un error en la base de datos.
     * @throws DuplicateUserException Si el usuario ya existe.
     */
    public int guardarUsuario(Usuario usuario) throws SQLException, DuplicateUserException {
        String sql = "INSERT INTO Usuarios (password, nombre, apellidos, rol, email, telefono, estado, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        // Hash de la contraseña antes de guardarla
        String hashedPassword = BCrypt.withDefaults().hashToString(12, usuario.getPassword().toCharArray());

        try (Connection conn = ConnectionMySql.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, hashedPassword);
            pstmt.setString(2, usuario.getNombre());
            pstmt.setString(3, usuario.getApellidos());
            pstmt.setString(4, usuario.getRol());
            pstmt.setString(5, usuario.getEmail());
            pstmt.setString(6, usuario.getTelefono());
            pstmt.setString(7, usuario.getEstado());
            pstmt.setDate(8, new java.sql.Date(usuario.getFechaRegistro().getTime()));

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("No se pudo insertar el usuario, ninguna fila afectada.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Devuelve el ID generado
                } else {
                    throw new SQLException("No se pudo obtener el ID del usuario insertado.");
                }
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new DuplicateUserException("El usuario con el email o teléfono ya existe.");
            } else {
                throw e;
            }
        }
    }

    /**
     * Método para buscar un usuario por su email en la base de datos.
     *
     * @param email El email del usuario a buscar.
     * @retg Método para contar el número de usuarios en la base de datos.
     *
     * @return El número total de usuarios.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public Usuario buscarUsuarioPorEmail(String email) throws SQLException {
        String sql = "SELECT * FROM Usuarios WHERE email = ?";

        try (Connection conn = ConnectionMySql.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("id"),
                            rs.getString("password"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getString("rol"),
                            rs.getString("email"),
                            rs.getString("telefono"),
                            rs.getString("estado"),
                            rs.getDate("fecha_registro")
                    );
                } else {
                    return null; // Usuario no encontrado
                }
            }
        }
    }

    public int contarUsuarios() throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM Usuarios";

        try (Connection conn = ConnectionMySql.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("total");
            } else {
                throw new SQLException("Error al contar los usuarios.");
            }
        }
    }

    /**
     * Método para editar un usuario existente en la base de datos.
     *
     * @param usuario El objeto Usuario que se va a editar.
     * @throws SQLException Si ocurre un error en la base de datos.
     * @throws UsuarioNoEncontradoException Si el usuario no se encuentra.
     */
    public void editarUsuario(Usuario usuario) throws SQLException, UsuarioNoEncontradoException {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo.");
        }

        String sql = "UPDATE Usuarios SET password = ?, nombre = ?, apellidos = ?, rol = ?, email = ?, telefono = ?, estado = ?, fecha_registro = ? WHERE id = ?";

        try (Connection conn = ConnectionMySql.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getPassword());
            pstmt.setString(2, usuario.getNombre());
            pstmt.setString(3, usuario.getApellidos());
            pstmt.setString(4, usuario.getRol());
            pstmt.setString(5, usuario.getEmail());
            pstmt.setString(6, usuario.getTelefono());
            pstmt.setString(7, usuario.getEstado());
            pstmt.setDate(8, new java.sql.Date(usuario.getFechaRegistro().getTime()));
            pstmt.setInt(9, usuario.getId());

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated == 0) {
                throw new UsuarioNoEncontradoException("Usuario con ID " + usuario.getId() + " no encontrado.");
            }
        }
    }

    /**
     * Método para listar todos los usuarios en la base de datos.
     *
     * @return Una lista de objetos Usuario.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public List<Usuario> listarUsuarios() throws SQLException {
        String sql = "SELECT * FROM Usuarios";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = ConnectionMySql.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("password"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("rol"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getString("estado"),
                        rs.getDate("fecha_registro")
                );
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    /**
     * Método para editar la contraseña de un usuario.
     *
     * @param email El email del usuario.
     * @param nuevaContraseña La nueva contraseña del usuario.
     * @throws SQLException Si ocurre un error en la base de datos.
     * @throws UsuarioNoEncontradoException Si el usuario no se encuentra.
     */
    public void editarContraseña(String email, String nuevaContraseña) throws SQLException, UsuarioNoEncontradoException {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El email no puede ser nulo o vacío.");
        }
        if (nuevaContraseña == null || nuevaContraseña.isEmpty()) {
            throw new IllegalArgumentException("La nueva contraseña no puede ser nula o vacía.");
        }

        // Hash de la nueva contraseña antes de actualizarla
        String hashedPassword = BCrypt.withDefaults().hashToString(12, nuevaContraseña.toCharArray());

        String sql = "UPDATE Usuarios SET password = ? WHERE email = ?";

        try (Connection conn = ConnectionMySql.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, hashedPassword);
            pstmt.setString(2, email);

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated == 0) {
                throw new UsuarioNoEncontradoException("Usuario con email " + email + " no encontrado.");
            }
        }
    }

    /**
     * * Método para autenticar un usuario durante el login.
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
