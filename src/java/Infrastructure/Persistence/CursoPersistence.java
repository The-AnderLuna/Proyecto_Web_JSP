package Infrastructure.Persistence;

import Business.Contracts.ICurso.ICursoRepository;
import Business.Contracts.ICurso.IEditarCurso;
import Business.Contracts.ICurso.IEliminarCurso;
import Business.Contracts.ICurso.IGuardarCurso;
import Business.Contracts.ICurso.IListarCursos;
import Domain.Model.Curso;
import Infrastructure.Database.ConnectionMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoPersistence implements ICursoRepository, IGuardarCurso, IEditarCurso, IEliminarCurso,IListarCursos {

    @Override
    public void guardarCurso(Curso curso) throws SQLException {
        String sql = "INSERT INTO Cursos (nombre, duracion, estudiantes, fecha_inicio, usuario_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionMySql.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, curso.getNombre());
            pstmt.setFloat(2, curso.getDuracion());
            pstmt.setInt(3, curso.getEstudiantes());
            pstmt.setDate(4, new java.sql.Date(curso.getFechaInicio().getTime()));
            pstmt.setInt(5, curso.getUsuarioId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al crear el curso: " + e.getMessage(), e);
        }
    }

    @Override
    public Curso buscarCurso(int id) throws SQLException {
        String sql = "SELECT * FROM Cursos WHERE id = ?";
        Curso curso = null;

        try (Connection conn = ConnectionMySql.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
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
            throw new SQLException("Error al buscar el curso por ID: " + e.getMessage(), e);
        }

        return curso;
    }

    @Override
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

    @Override
    public void eliminarCurso(int id, int usuarioId) throws SQLException {
        String sql = "DELETE FROM Cursos WHERE id = ? AND usuario_id = ?";

        try (Connection conn = ConnectionMySql.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setInt(2, usuarioId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar el curso: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Curso> listarCursos() throws SQLException {
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

    @Override
    public List<Curso> listarCursosPorUsuarioId(int usuarioId) throws SQLException {
        String sql = "SELECT * FROM Cursos WHERE usuario_id = ?";
        List<Curso> cursos = new ArrayList<>();

        System.out.println("Iniciando la conexión a la base de datos para usuario_id: " + usuarioId);

        try (Connection conn = ConnectionMySql.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, usuarioId);
            System.out.println("Ejecutando consulta: " + pstmt);

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
                    System.out.println("Curso encontrado: " + curso); // Mensaje de depuración
                    cursos.add(curso);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los cursos del usuario: " + e.getMessage());
            throw new SQLException("Error al listar los cursos del usuario: " + e.getMessage(), e);
        }

        System.out.println("Total de cursos encontrados: " + cursos.size());
        return cursos;
    }

    
}
