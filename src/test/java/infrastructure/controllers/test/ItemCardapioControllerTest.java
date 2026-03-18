package infrastructure.controllers.test;

import application.usecases.ItemCardapio.*;
import domain.entities.ItemCardapio;
import infrastructure.controllers.ItemCardapioController;
import infrastructure.controllers.dtos.ItemCardapioRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemCardapioControllerTest {

    @Mock private AdicionarItemCardapioUseCase adicionarUseCase;
    @Mock private ListarTodosItemCardapioUseCase listarUseCase;
    @Mock private DeletarItemCardapioUseCase deletarUseCase;

    @InjectMocks
    private ItemCardapioController controller;

    @Test
    void deveAdicionarItemEndpoint() {
        ItemCardapioRequest req = new ItemCardapioRequest("Suco", "Gelado", 10.0, false, "", 1L);
        ItemCardapio mockItem = new ItemCardapio();
        mockItem.setNome("Suco");

        when(adicionarUseCase.executar(any(), eq(1L))).thenReturn(mockItem);

        ResponseEntity<ItemCardapio> response = controller.adicionar(req);

        assertEquals(201, response.getStatusCode().value());
        assertEquals("Suco", response.getBody().getNome());
    }

    @Test
    void deveDeletarItemEndpoint() {
        ResponseEntity<Void> response = controller.deletar(1L);
        assertEquals(204, response.getStatusCode().value());
        verify(deletarUseCase).executar(1L);
    }
}