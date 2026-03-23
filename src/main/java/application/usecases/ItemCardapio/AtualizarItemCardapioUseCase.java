package application.usecases.ItemCardapio;

import domain.entities.ItemCardapio;
import domain.exceptions.BusinessException;
import domain.gateways.ItemCardapioRepository;

public class AtualizarItemCardapioUseCase {

    private final ItemCardapioRepository itemCardapioRepository;

    public AtualizarItemCardapioUseCase(ItemCardapioRepository itemCardapioRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
    }

    public ItemCardapio executar(Long id, ItemCardapio itemCardapio) {
        ItemCardapio existente = itemCardapioRepository.buscarPorId(id)
                .orElseThrow(() -> new BusinessException("Item do cardápio não encontrado"));

        existente.setNome(itemCardapio.getNome());
        existente.setPreco(itemCardapio.getPreco());
        existente.setDescricao(itemCardapio.getDescricao());
        existente.setCaminhoFoto(itemCardapio.getCaminhoFoto());
        existente.setDisponivelApenasNoRestaurante(itemCardapio.getDisponivelApenasNoRestaurante());

        return itemCardapioRepository.salvar(existente, existente.getRestauranteId());
    }
}
