package infrastructure.controllers.test;

import application.usecases.Usuario.*;
import domain.entities.Usuario;
import infrastructure.controllers.UsuarioController;
import infrastructure.controllers.dtos.UsuarioRequest;
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
class UsuarioControllerTest {

    @Mock private CriaUsuarioUseCase criaUsuarioUseCase;
    @Mock private BuscarUsuariosUseCase buscarUsuariosUseCase;
    @Mock private BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;
    @Mock private AtualizarUsuarioUseCase atualizarUsuarioUseCase;
    @Mock private DeletarUsuarioUseCase deletarUsuarioUseCase;

    @InjectMocks
    private UsuarioController controller;

    @Test
    void deveCriarUsuario() {
        UsuarioRequest request = new UsuarioRequest("Teste", "teste@email.com", 1L);
        Usuario mockRetorno = new Usuario();
        mockRetorno.setId(1L);

        when(criaUsuarioUseCase.executar(any(Usuario.class))).thenReturn(mockRetorno);

        ResponseEntity<Usuario> response = controller.criar(request);

        assertEquals(201, response.getStatusCode().value());
        assertNotNull(response.getBody());
    }

    @Test
    void deveListarUsuarios() {
        when(buscarUsuariosUseCase.executar()).thenReturn(List.of(new Usuario()));
        ResponseEntity<List<Usuario>> response = controller.listar();
        assertEquals(200, response.getStatusCode().value());
    }
}