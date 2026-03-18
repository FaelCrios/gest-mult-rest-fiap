package domain.gateways;

import domain.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    Usuario salvar(Usuario usuario);
    Optional<Usuario> buscarPorId(Long id);
    List<Usuario> listarTodos();
    Optional<Usuario> buscarPorEmail(String email);
    void deletar(Long id);
    Boolean existe(Long id);
}
