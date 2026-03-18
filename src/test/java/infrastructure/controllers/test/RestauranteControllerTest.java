package infrastructure.controllers.test;

import application.usecases.restaurante.*;
import domain.entities.Restaurante;
import infrastructure.controllers.RestauranteController;
import infrastructure.controllers.dtos.RestauranteRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestauranteControllerTest {

    @Mock private CadastrarRestauranteUseCase cadastrarUseCase;
    @Mock private BuscarRestaurantesUseCase buscarUseCase;
    @Mock private BuscarRestaurantePorIdUseCase buscarPorIdUseCase;
    @Mock private AtualizarRestauranteUseCase atualizarUseCase;
    @Mock private DeletarRestauranteUseCase deletarUseCase;

    @InjectMocks
    private RestauranteController controller;

    @Test
    void deveCriarRestauranteEndpoint() {
        RestauranteRequest request = new RestauranteRequest("Grill", "Rua", "BR", "10h", 1L);
        Restaurante mockRetorno = new Restaurante();
        mockRetorno.setId(1L);

        when(cadastrarUseCase.executar(any(Restaurante.class))).thenReturn(mockRetorno);

        ResponseEntity<Restaurante> response = controller.criar(request);

        assertEquals(201, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void deveListarRestaurantesEndpoint() {
        when(buscarUseCase.executar()).thenReturn(List.of(new Restaurante()));

        ResponseEntity<List<Restaurante>> response = controller.listar();

        assertEquals(200, response.getStatusCode().value());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void deveBuscarRestaurantePorIdEndpoint() {
        when(buscarPorIdUseCase.executar(1L)).thenReturn(new Restaurante());

        ResponseEntity<Restaurante> response = controller.buscarPorId(1L);

        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void deveDeletarRestauranteEndpoint() {
        ResponseEntity<Void> response = controller.deletar(1L);

        assertEquals(204, response.getStatusCode().value());
        verify(deletarUseCase).executar(1L);
    }
}