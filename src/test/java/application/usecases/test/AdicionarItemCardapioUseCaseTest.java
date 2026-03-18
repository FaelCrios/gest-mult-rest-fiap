package application.usecases.test;

import application.usecases.ItemCardapio.AdicionarItemCardapioUseCase;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdicionarItemCardapioUseCaseTest {

    @Mock
    private ItemCardapioRepository itemRepository;

    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private AdicionarItemCardapioUseCase useCase;

    @Test
    void deveAdicionarItem_QuandoRestauranteExiste() {
        Long idRestaurante = 1L;
        ItemCardapio item = new ItemCardapio();
        item.setNome("Prato Feito");

        when(restauranteRepository.buscarPorId(idRestaurante)).thenReturn(Optional.of(new Restaurante()));
        when(itemRepository.salvar(item, idRestaurante)).thenReturn(item);

        ItemCardapio salvo = useCase.executar(item, idRestaurante);

        assertNotNull(salvo);
        verify(itemRepository).salvar(item, idRestaurante);
    }

    @Test
    void deveLancarErro_QuandoRestauranteNaoExiste() {
        Long idInexistente = 99L;
        when(restauranteRepository.buscarPorId(idInexistente)).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () ->
                useCase.executar(new ItemCardapio(), idInexistente)
        );
        verify(itemRepository, never()).salvar(any(), any());
    }
}