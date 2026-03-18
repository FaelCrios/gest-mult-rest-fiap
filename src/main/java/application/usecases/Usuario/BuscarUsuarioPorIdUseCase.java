package application.usecases.Usuario;

import domain.entities.Usuario;
import domain.exceptions.BusinessException;
import domain.gateways.UsuarioRepository;

public class BuscarUsuarioPorIdUseCase {

    private final UsuarioRepository usuarioRepository;

    public BuscarUsuarioPorIdUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario executar(Long id) {
        return usuarioRepository.buscarPorId(id)
                .orElseThrow(()-> new BusinessException("Usuário não encontrado"));
    }
}
