package application.usecases.TipoUsuario;

import domain.entities.TipoUsuario;
import domain.gateways.TipoUsuarioRepository;

public class CriarTipoUsuarioUseCase {

    private final TipoUsuarioRepository tipoUsuarioRepository;

    public CriarTipoUsuarioUseCase(TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public TipoUsuario executar(TipoUsuario tipoUsuario){
        tipoUsuario.validarNome();
        return tipoUsuarioRepository.salvar(tipoUsuario);
    }

}
