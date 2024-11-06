package Infrastructure.Tests.Usuario;

import Infrastructure.Database.ConnectionMySql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class TestDatabaseConnection {

    public static void main(String[] args) {
        String query = "SELECT * FROM Usuarios";

        try (Connection conn = ConnectionMySql.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");

                System.out.println("ID: " + id);
                System.out.println("Nombre: " + nombre);
                System.out.println("Email: " + email);
                System.out.println("-----------------------");
            }

        } catch (SQLException e) {
            System.err.println("Error al conectar o consultar la base de datos: " + e.getMessage());
        }
    }
}
