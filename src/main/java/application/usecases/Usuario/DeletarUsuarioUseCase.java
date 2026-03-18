package application.usecases.Usuario;

import domain.exceptions.BusinessException;
import domain.gateways.UsuarioRepository;

public class DeletarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public DeletarUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void executar(Long id) {
       if(!usuarioRepository.existe(id)){
           throw new BusinessException("Usuário não encontrado");
       };
       usuarioRepository.deletar(id);
    }
}
