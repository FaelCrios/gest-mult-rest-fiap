package domain.gateways;

import domain.entities.TipoUsuario;

import java.util.List;


public interface TipoUsuarioRepository {
    TipoUsuario salvar(TipoUsuario tipo);
    List<TipoUsuario> listarTodos();
}
