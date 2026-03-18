package infrastructure.controllers.test;

import application.usecases.TipoUsuario.BuscarTiposUsuarioUseCase;
import application.usecases.TipoUsuario.CriarTipoUsuarioUseCase;
import domain.entities.TipoUsuario;
import infrastructure.controllers.TipoUsuarioController;
import infrastructure.controllers.dtos.TipoUsuarioRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TipoUsuarioControllerTest {

    @Mock private CriarTipoUsuarioUseCase criarUseCase;
    @Mock private BuscarTiposUsuarioUseCase buscarUseCase;

    @InjectMocks
    private TipoUsuarioController controller;

    @Test
    void deveCriarTipoUsuario() {
        TipoUsuarioRequest req = new TipoUsuarioRequest("Admin");
        TipoUsuario tipo = new TipoUsuario();
        tipo.setId(1L);

        when(criarUseCase.executar(any())).thenReturn(tipo);

        ResponseEntity<TipoUsuario> response = controller.criar(req);
        assertEquals(201, response.getStatusCode().value());
    }

    @Test
    void deveListarTiposUsuario() {
        when(buscarUseCase.executar()).thenReturn(List.of(new TipoUsuario()));
        ResponseEntity<List<TipoUsuario>> response = controller.listar();
        assertEquals(200, response.getStatusCode().value());
    }
}