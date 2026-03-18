package application.usecases.ItemCardapio;

import domain.entities.ItemCardapio;
import domain.gateways.ItemCardapioRepository;

import java.util.List;

public class ListarTodosItemCardapioUseCase {

    private final ItemCardapioRepository itemCardapioRepository;

    public ListarTodosItemCardapioUseCase(ItemCardapioRepository itemCardapioRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
    }

    public List<ItemCardapio> executar(){
        return itemCardapioRepository.listarTodos();
    }
}
