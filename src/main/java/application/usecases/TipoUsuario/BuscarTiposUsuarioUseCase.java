package application.usecases.TipoUsuario;

import domain.entities.TipoUsuario;
import domain.gateways.TipoUsuarioRepository;
import java.util.List;

public class BuscarTiposUsuarioUseCase {
    private final TipoUsuarioRepository repository;

    public BuscarTiposUsuarioUseCase(TipoUsuarioRepository repository) {
        this.repository = repository;
    }

    public List<TipoUsuario> executar() {
        return repository.listarTodos();
    }
}