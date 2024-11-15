package Business.Services.Curso;

import Business.Contracts.ICurso.IEliminarCurso;
import java.sql.SQLException;

public class EliminarCursoService {

    private final IEliminarCurso eliminarCursoRepository;

    public EliminarCursoService(IEliminarCurso eliminarCursoRepository) {
        this.eliminarCursoRepository = eliminarCursoRepository;
    }

    public void eliminarCurso(int id, int usuarioId) throws SQLException {
        eliminarCursoRepository.eliminarCurso(id, usuarioId);
    }
}
