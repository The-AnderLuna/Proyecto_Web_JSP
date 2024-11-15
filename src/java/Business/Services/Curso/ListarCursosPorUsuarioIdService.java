package Business.Services.Curso;

import Business.Contracts.ICurso.ICursoRepository;
import Domain.Model.Curso;
import java.sql.SQLException;
import java.util.List;

public class ListarCursosPorUsuarioIdService {

    private final ICursoRepository cursoRepository;

    public ListarCursosPorUsuarioIdService(ICursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> listarCursosPorUsuarioId(int usuarioId) throws SQLException {
        return cursoRepository.listarCursosPorUsuarioId(usuarioId);
    }
}
