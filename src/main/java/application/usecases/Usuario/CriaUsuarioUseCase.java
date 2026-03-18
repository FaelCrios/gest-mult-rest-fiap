package application.usecases.Usuario;

import domain.entities.Usuario;
import domain.gateways.UsuarioRepository;

public class CriaUsuarioUseCase {

    private final UsuarioRepository repository;

    public CriaUsuarioUseCase(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario executar(Usuario usuario) {
        usuario.validar();
        return repository.salvar(usuario);
    }
}
