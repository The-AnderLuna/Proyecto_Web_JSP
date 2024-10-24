package Infrastructure.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ander
 */
public class ConnectionMySql {

    private static final String URL = "jdbc:mysql://localhost:3306/desarrollo_web";
    private static final String USER = "root";
    private static final String PASSWORD = "Ander";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el driver JDBC: " + e.getMessage(), e);
        } catch (SQLException e) {
            throw new SQLException("Error al conectar a la base de datos: " + e.getMessage(), e);
        }
    }
}