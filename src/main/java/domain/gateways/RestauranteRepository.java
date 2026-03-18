package domain.gateways;

import domain.entities.Restaurante;
import java.util.Optional;
import java.util.List;

public interface RestauranteRepository {
    Restaurante salvar(Restaurante restaurante);
    List<Restaurante> listarTodos();
    Optional<Restaurante> buscarPorId(Long id);
    void deletar(Long id);
    boolean existe(Long id);
}