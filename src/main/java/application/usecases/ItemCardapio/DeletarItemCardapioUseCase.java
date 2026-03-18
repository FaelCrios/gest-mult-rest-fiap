package application.usecases.ItemCardapio;

import domain.exceptions.BusinessException;
import domain.gateways.ItemCardapioRepository;

public class DeletarItemCardapioUseCase {

    private final ItemCardapioRepository itemCardapioRepository;

    public DeletarItemCardapioUseCase(ItemCardapioRepository itemCardapioRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
    }

    public void executar(Long id){
        if(!itemCardapioRepository.existe(id)){
            throw new BusinessException("Não foi possível encontrar o item no cardapio");
        }
        itemCardapioRepository.deletar(id);
    }
}
