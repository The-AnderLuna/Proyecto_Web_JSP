package Infrastructure.Tests.Usuario;

import Domain.Model.Usuario;
import Infrastructure.Persistence.UsuarioPersistence;
import Business.Exceptions.DuplicateUserException;

import java.sql.SQLException;
import java.util.Date;

public class InsertUser {

    public static void main(String[] args) {
        // Crear un objeto Usuario de ejemplo
        Usuario usuario = new Usuario(1, "password123", "Ander", "Gonzalez", "USER", "ander@example.com", "1234567890", "ACTIVO", new Date());

        // Crear instancia de UsuarioPersistence
        UsuarioPersistence usuarioPersistence = new UsuarioPersistence();

        try {
            int id = usuarioPersistence.guardarUsuario(usuario);
            System.out.println("Usuario guardado con ID: " + id);
        } catch (SQLException | DuplicateUserException e) {
            System.err.println("Error al guardar el usuario: " + e.getMessage());
        }
    }
}
