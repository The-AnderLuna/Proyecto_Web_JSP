package Domain.Model;

import java.util.Date;

public class Curso {

    private int id;
    private String nombre;
    private float duracion;
    private int estudiantes;
    private Date fechaInicio;
    private int usuarioId;

    // Constructor
    public Curso() {
    }
    
// Constructor sin ID (para nuevos cursos)

    public Curso(String nombre, float duracion, int estudiantes, Date fechaInicio, int usuarioId) {
        this.setNombre(nombre);        // Validación de nombre
        this.setDuracion(duracion);    // Validación de duración
        this.setEstudiantes(estudiantes);  // Validación de estudiantes
        this.setFechaInicio(fechaInicio);  // Validación de fecha de inicio

        this.usuarioId = usuarioId;
    }

    public Curso(int id, String nombre, float duracion, int estudiantes, Date fechaInicio, int usuarioId) {
        this.setNombre(nombre);        // Validación de nombre
        this.setDuracion(duracion);    // Validación de duración
        this.setEstudiantes(estudiantes);  // Validación de estudiantes
        this.setFechaInicio(fechaInicio);  // Validación de fecha de inicio
        this.id = id;
        this.usuarioId = usuarioId;
    }

    // Getters y Setters con validaciones
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del curso es obligatorio.");
        }
        this.nombre = nombre;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        if (duracion <= 0) {
            throw new IllegalArgumentException("La duración del curso debe ser mayor a cero.");
        }
        this.duracion = duracion;
    }

    public int getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(int estudiantes) {
        if (estudiantes < 0) {
            throw new IllegalArgumentException("El número de estudiantes no puede ser negativo.");
        }
        this.estudiantes = estudiantes;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        if (fechaInicio == null) {
            throw new IllegalArgumentException("La fecha de inicio es obligatoria.");
        }
        this.fechaInicio = fechaInicio;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setNumeroEstudiantes(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
