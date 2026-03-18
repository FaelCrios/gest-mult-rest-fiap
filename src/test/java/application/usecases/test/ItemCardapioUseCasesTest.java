package application.usecases.test;

import application.usecases.ItemCardapio.*;
import domain.entities.ItemCardapio;
import domain.entities.Restaurante;
import domain.exceptions.BusinessException;
import domain.gateways.ItemCardapioRepository;
import domain.gateways.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemCardapioUseCasesTest {

    @Mock
    private ItemCardapioRepository itemRepository;
    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private AdicionarItemCardapioUseCase adicionarUseCase;
    @InjectMocks
    private AtualizarItemCardapioUseCase atualizarUseCase;
    @InjectMocks
    private DeletarItemCardapioUseCase deletarUseCase;

    @Test
    void deveAdicionarItem() {
        ItemCardapio item = new ItemCardapio();
        item.setNome("Pizza");

        when(restauranteRepository.buscarPorId(1L)).thenReturn(Optional.of(new Restaurante()));
        when(itemRepository.salvar(any(), eq(1L))).thenReturn(item);

        ItemCardapio salvo = adicionarUseCase.executar(item, 1L);

        assertNotNull(salvo);
    }

    @Test
    void deveAtualizarItem() {
        ItemCardapio existente = new ItemCardapio();
        existente.setId(10L);
        existente.setNome("Pizza Queijo");

        ItemCardapio novosDados = new ItemCardapio();
        novosDados.setNome("Pizza 4 Queijos");
        novosDados.setPreco(50.0);

        when(itemRepository.buscarPorId(10L)).thenReturn(Optional.of(existente));

        when(itemRepository.salvar(any(ItemCardapio.class), any())).thenAnswer(i -> i.getArgument(0));

        ItemCardapio atualizado = atualizarUseCase.executar(10L, novosDados);

        assertEquals("Pizza 4 Queijos", atualizado.getNome());
        assertEquals(50.0, atualizado.getPreco());
    }

    @Test
    void deveDeletarItem() {
        when(itemRepository.existe(10L)).thenReturn(true);
        deletarUseCase.executar(10L);
        verify(itemRepository).deletar(10L);
    }

    @Test
    void naoDeveDeletarItemInexistente() {
        when(itemRepository.existe(10L)).thenReturn(false);
        assertThrows(BusinessException.class, () -> deletarUseCase.executar(10L));
    }
}