package Infrastructure.Persistence;

import Domain.Model.Usuario;
import Infrastructure.Database.ConnectionMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import Business.Exceptions.DuplicateUserException;
import Business.Exceptions.UsuarioNoEncontradoException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para manejar la persistencia de usuarios en la base de datos.
 *
 * @autor Ander
 */
public class UsuarioPersistence {

    // Método para guardar usuarios
    public int guardarUsuario(Usuario usuario) throws SQLException, DuplicateUserException {
        String sql = "INSERT INTO Usuarios (password, nombre, apellidos, rol, email, telefono, estado, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionMySql.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, usuario.getPassword());
            pstmt.setString(2, usuario.getNombre());
            pstmt.setString(3, usuario.getApellidos());
            pstmt.setString(4, usuario.getRol());
            pstmt.setString(5, usuario.getEmail());
            pstmt.setString(6, usuario.getTelefono());
            pstmt.setString(7, usuario.getEstado());
            pstmt.setDate(8, new java.sql.Date(usuario.getFechaRegistro().getTime()));

            pstmt.executeUpdate();

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

    // Método para buscar usuario por email
    public Usuario buscarUsuarioPorEmail(String email) throws SQLException, UsuarioNoEncontradoException {
        String sql = "SELECT * FROM Usuarios WHERE email = ?";

        try (Connection conn = ConnectionMySql.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

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
                throw new UsuarioNoEncontradoException("Usuario con email " + email + " no encontrado.");
            }
        }
    }
// Método para contar el número de usuarios

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

// Método para editar un usuario existente
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

    // Método para listar todos los usuarios
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

    // Método para Editar Contraseña
    public void editarContraseña(String email, String nuevaContraseña) throws SQLException, UsuarioNoEncontradoException {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El email no puede ser nulo o vacío.");
        }
        if (nuevaContraseña == null || nuevaContraseña.isEmpty()) {
            throw new IllegalArgumentException("La nueva contraseña no puede ser nula o vacía.");
        }

        String sql = "UPDATE Usuarios SET password = ? WHERE email = ?";

        try (Connection conn = ConnectionMySql.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nuevaContraseña);
            pstmt.setString(2, email);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new UsuarioNoEncontradoException("Usuario con email " + email + " no encontrado.");
            }
        }
    }

}
