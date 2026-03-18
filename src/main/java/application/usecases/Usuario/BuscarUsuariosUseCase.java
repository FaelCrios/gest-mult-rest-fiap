package application.usecases.Usuario;

import domain.entities.Usuario;
import domain.gateways.UsuarioRepository;
import java.util.List;

public class BuscarUsuariosUseCase {
    private final UsuarioRepository repository;

    public BuscarUsuariosUseCase(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> executar() {
        return repository.listarTodos();
    }
}