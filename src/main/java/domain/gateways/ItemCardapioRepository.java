package domain.gateways;

import domain.entities.ItemCardapio;

import java.util.List;
import java.util.Optional;

public interface ItemCardapioRepository {
    ItemCardapio salvar(ItemCardapio itemCardapio, Long restauranteId);
    List<ItemCardapio> listarTodos();
    Optional<ItemCardapio> buscarPorId(Long id);
    void deletar(Long id);
    boolean existe(Long id);
}
