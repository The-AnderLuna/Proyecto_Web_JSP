package Business.Services.Curso;

import Business.Contracts.ICurso.ICursoRepository;
import Domain.Model.Curso;
import java.sql.SQLException;

public class BuscarCursoService {

    private final ICursoRepository cursoRepository;

    public BuscarCursoService(ICursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso buscarCursoPorId(int id) throws SQLException {
        return cursoRepository.buscarCurso(id);
    }
}
