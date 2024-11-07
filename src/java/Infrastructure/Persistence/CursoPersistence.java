package Infrastructure.Persistence;

import Domain.Model.Curso;
import Infrastructure.Database.ConnectionMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoPersistence {

    /**
     * Inserta un nuevo curso en la base de datos.
     *
     * @param curso El curso a crear.
     * @throws SQLException Si ocurre un error al interactuar con la base de
     * datos.
     */
    public void crearCurso(Curso curso) throws SQLException {
        String sql = "INSERT INTO Cursos (nombre, duracion, estudiantes, fecha_inicio, usuario_id) VALUES (?, ?, ?, ?, ?)";

        // Intentar establecer una conexión y realizar la inserción
        try (Connection conn = ConnectionMySql.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Establecer los parámetros de la consulta
            pstmt.setString(1, curso.getNombre());
            pstmt.setFloat(2, curso.getDuracion());
            pstmt.setInt(3, curso.getEstudiantes());
            pstmt.setDate(4, new java.sql.Date(curso.getFechaInicio().getTime()));
            pstmt.setInt(5, curso.getUsuarioId());

            // Ejecutar la inserción
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // Manejo de excepciones: lanzar la excepción para ser manejada por el llamador
            throw new SQLException("Error al crear el curso: " + e.getMessage(), e);
        }
    }

    /**
     * Busca un curso por su ID en la base de datos.
     *
     * @param id El ID del curso a buscar.
     * @return Un objeto Curso si se encuentra, de lo contrario, null.
     * @throws SQLException Si ocurre un error al interactuar con la base de
     * datos.
     */
    public Curso buscarCursoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Cursos WHERE id = ?";
        Curso curso = null;

        // Intentar establecer una conexión y realizar la consulta
        try (Connection conn = ConnectionMySql.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Establecer el parámetro de la consulta
            pstmt.setInt(1, id);

            // Ejecutar la consulta y procesar el resultado
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    curso = new Curso(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getFloat("duracion"),
                            rs.getInt("estudiantes"),
                            rs.getDate("fecha_inicio"),
                            rs.getInt("usuario_id")
                    );
                }
            }
        } catch (SQLException e) {
            // Manejo de excepciones: lanzar la excepción para ser manejada por el llamador
            throw new SQLException("Error al buscar el curso por ID: " + e.getMessage(), e);
        }

        return curso;
    }

    /**
     * Listar todos los cursos disponibles en la base de datos.
     *
     * @return Una lista de objetos Curso.
     * @throws SQLException Si ocurre un error al interactuar con la base de
     * datos.
     */
    public List<Curso> listarCursosDisponibles() throws SQLException {
        String sql = "SELECT * FROM Cursos";
        List<Curso> cursos = new ArrayList<>();

        try (Connection conn = ConnectionMySql.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Curso curso = new Curso(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getFloat("duracion"),
                        rs.getInt("estudiantes"),
                        rs.getDate("fecha_inicio"),
                        rs.getInt("usuario_id")
                );
                cursos.add(curso);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al listar los cursos disponibles: " + e.getMessage(), e);
        }

        return cursos;
    }

    /**
     * Lista todos los cursos creados por un usuario específico (profesor).
     *
     * @param usuarioId El ID del usuario (profesor).
     * @return Una lista de objetos Curso.
     * @throws SQLException Si ocurre un error al interactuar con la base de
     * datos.
     */
    public List<Curso> listarCursosPorUsuarioId(int usuarioId) throws SQLException {
        String sql = "SELECT * FROM Cursos WHERE usuario_id = ?";
        List<Curso> cursos = new ArrayList<>();

        try (Connection conn = ConnectionMySql.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, usuarioId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Curso curso = new Curso(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getFloat("duracion"),
                            rs.getInt("estudiantes"),
                            rs.getDate("fecha_inicio"),
                            rs.getInt("usuario_id")
                    );
                    cursos.add(curso);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al listar los cursos del usuario: " + e.getMessage(), e);
        }

        return cursos;
    }

    /**
     * Lista todos los cursos en los que un usuario (estudiante) está inscrito.
     *
     * @param usuarioId El ID del usuario (estudiante).
     * @return Una lista de objetos Curso.
     * @throws SQLException Si ocurre un error al interactuar con la base de
     * datos.
     */
    public List<Curso> listarCursosInscritosPorUsuarioId(int usuarioId) throws SQLException {
        String sql = "SELECT c.* FROM Cursos c "
                + "JOIN RegistroCursos rc ON c.id = rc.curso_id "
                + "WHERE rc.usuario_id = ?";
        List<Curso> cursos = new ArrayList<>();

        try (Connection conn = ConnectionMySql.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, usuarioId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Curso curso = new Curso(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getFloat("duracion"),
                            rs.getInt("estudiantes"),
                            rs.getDate("fecha_inicio"),
                            rs.getInt("usuario_id")
                    );
                    cursos.add(curso);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al listar los cursos inscritos por el usuario: " + e.getMessage(), e);
        }

        return cursos;
    }

    /**
     * Actualiza los detalles de un curso específico.
     *
     * @param curso Objeto Curso con los detalles actualizados.
     * @throws SQLException Si ocurre un error al interactuar con la base de
     * datos.
     */
    public void editarCurso(Curso curso) throws SQLException {
        String sql = "UPDATE Cursos SET nombre = ?, duracion = ?, estudiantes = ?, fecha_inicio = ? WHERE id = ? AND usuario_id = ?";

        try (Connection conn = ConnectionMySql.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, curso.getNombre());
            pstmt.setFloat(2, curso.getDuracion());
            pstmt.setInt(3, curso.getEstudiantes());
            pstmt.setDate(4, new java.sql.Date(curso.getFechaInicio().getTime()));
            pstmt.setInt(5, curso.getId());
            pstmt.setInt(6, curso.getUsuarioId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al editar el curso: " + e.getMessage(), e);
        }
    }

    /**
     * Elimina un curso específico.
     *
     * @param cursoId El ID del curso a eliminar.
     * @param usuarioId El ID del usuario que creó el curso.
     * @throws SQLException Si ocurre un error al interactuar con la base de
     * datos.
     */
    public void eliminarCurso(int cursoId, int usuarioId) throws SQLException {
        String sql = "DELETE FROM Cursos WHERE id = ? AND usuario_id = ?";

        try (Connection conn = ConnectionMySql.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cursoId);
            pstmt.setInt(2, usuarioId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar el curso: " + e.getMessage(), e);
        }
    }

}
