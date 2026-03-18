package application.usecases.ItemCardapio;

import domain.entities.ItemCardapio;
import domain.exceptions.BusinessException;
import domain.gateways.ItemCardapioRepository;

public class BuscarItemCardapioPoIdUseCase {

    private final ItemCardapioRepository itemCardapioRepository;

    public BuscarItemCardapioPoIdUseCase(ItemCardapioRepository itemCardapioRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
    }

    public ItemCardapio executar(Long id){
        return itemCardapioRepository.buscarPorId(id)
                .orElseThrow(()->new BusinessException("Não foi possível encontrar o item no cardápio"));
    }
}
