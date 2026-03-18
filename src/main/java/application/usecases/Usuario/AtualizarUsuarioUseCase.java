package application.usecases.Usuario;

import domain.entities.Usuario;
import domain.exceptions.BusinessException;
import domain.gateways.UsuarioRepository;

public class AtualizarUsuarioUseCase {
    private final UsuarioRepository repository;

    public AtualizarUsuarioUseCase(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario executar(Long id, Usuario dadosAtualizados) {
        Usuario existente = repository.buscarPorId(id)
                .orElseThrow(() -> new BusinessException("Restaurante não encontrado"));

        existente.setNome(dadosAtualizados.getNome());
        existente.setEmail(dadosAtualizados.getEmail());
        return repository.salvar(existente);
    }
}
