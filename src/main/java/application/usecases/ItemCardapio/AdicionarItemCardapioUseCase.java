package application.usecases.ItemCardapio;

import domain.exceptions.BusinessException;
import domain.entities.ItemCardapio;
import domain.gateways.ItemCardapioRepository;
import domain.gateways.RestauranteRepository;

public class AdicionarItemCardapioUseCase {
    private final ItemCardapioRepository itemRepository;
    private final RestauranteRepository restauranteRepository;

    public AdicionarItemCardapioUseCase(ItemCardapioRepository itemRepository,
                                        RestauranteRepository restauranteRepository) {
        this.itemRepository = itemRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public ItemCardapio executar(ItemCardapio item, Long restauranteId) {
        restauranteRepository.buscarPorId(restauranteId)
                .orElseThrow(() -> new BusinessException("Restaurante não encontrado."));

        return itemRepository.salvar(item, restauranteId);
    }
}