package Domain.Model;

import java.util.Date;
import java.util.regex.Pattern;

public class Usuario {

    private int id;
    private String password;
    private String nombre;
    private String apellidos;
    private String rol;
    private String email;
    private String telefono;
    private String estado;
    private Date fechaRegistro;

    // Constructor
    public Usuario(int id, String password, String nombre, String apellidos, String rol, String email, String telefono, String estado, Date fechaRegistro) {
        this.setPassword(password);    // Llamamos al setter que tiene la validación
        this.setNombre(nombre);           // Validación de nombre
        this.setApellidos(apellidos);     // Validación de apellidos
        this.setRol(rol);                 // Validación de rol
        this.setEmail(email);             // Validación de email
        this.setTelefono(telefono);       // Validación de teléfono (opcional)
        this.setFechaRegistro(fechaRegistro);  // Validación de fecha de registro
        this.id = id;
        this.estado = estado;
    }

    // Getters y Setters con validaciones
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("La contraseña es obligatoria.");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
        }
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        if (apellidos == null || apellidos.isEmpty()) {
            throw new IllegalArgumentException("Los apellidos son obligatorios.");
        }
        this.apellidos = apellidos;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        if (rol == null || rol.isEmpty()) {
            throw new IllegalArgumentException("El rol es obligatorio.");
        }
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (email == null || email.isEmpty() || !Pattern.matches(emailPattern, email)) {
            throw new IllegalArgumentException("El email es obligatorio y debe tener un formato válido.");
        }
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono != null && !telefono.matches("\\d{10}")) {
            throw new IllegalArgumentException("El número de teléfono debe tener 10 dígitos.");
        }
        this.telefono = telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        if (fechaRegistro == null) {
            throw new IllegalArgumentException("La fecha de registro es obligatoria.");
        }
        this.fechaRegistro = fechaRegistro;
    }
}
