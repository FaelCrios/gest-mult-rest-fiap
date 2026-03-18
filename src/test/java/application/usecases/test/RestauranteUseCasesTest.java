package application.usecases.test;

import application.usecases.restaurante.*;
import domain.entities.Restaurante;
import domain.entities.TipoUsuario;
import domain.entities.Usuario;
import domain.exceptions.BusinessException;
import domain.gateways.RestauranteRepository;
import domain.gateways.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestauranteUseCasesTest {

    @Mock
    private RestauranteRepository restauranteRepository;
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private CadastrarRestauranteUseCase cadastrarUseCase;
    @InjectMocks
    private BuscarRestaurantesUseCase buscarTodosUseCase;
    @InjectMocks
    private BuscarRestaurantePorIdUseCase buscarPorIdUseCase;
    @InjectMocks
    private AtualizarRestauranteUseCase atualizarUseCase;
    @InjectMocks
    private DeletarRestauranteUseCase deletarUseCase;

    @Test
    void deveCadastrarRestaurante_QuandoDonoValido() {
        Usuario dono = criarDonoValido();
        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Grill");
        restaurante.setDono(dono);

        when(usuarioRepository.buscarPorId(1L)).thenReturn(Optional.of(dono));
        when(restauranteRepository.salvar(any())).thenReturn(restaurante);

        Restaurante resultado = cadastrarUseCase.executar(restaurante);

        assertNotNull(resultado);
        verify(restauranteRepository).salvar(any());
    }

    @Test
    void naoDeveCadastrar_QuandoDonoInvalido() {
        Usuario cliente = new Usuario();
        cliente.setId(2L);
        TipoUsuario tipo = new TipoUsuario();
        tipo.setNome("Cliente");
        cliente.setTipo(tipo);

        Restaurante restaurante = new Restaurante();
        restaurante.setDono(cliente);

        when(usuarioRepository.buscarPorId(2L)).thenReturn(Optional.of(cliente));

        assertThrows(BusinessException.class, () -> cadastrarUseCase.executar(restaurante));
    }

    @Test
    void deveListarTodosRestaurantes() {
        when(restauranteRepository.listarTodos()).thenReturn(List.of(new Restaurante(), new Restaurante()));

        List<Restaurante> lista = buscarTodosUseCase.executar();

        assertEquals(2, lista.size());
    }

    @Test
    void deveBuscarPorId_QuandoExiste() {
        Restaurante r = new Restaurante();
        r.setId(1L);
        when(restauranteRepository.buscarPorId(1L)).thenReturn(Optional.of(r));

        Restaurante resultado = buscarPorIdUseCase.executar(1L);

        assertEquals(1L, resultado.getId());
    }

    @Test
    void deveFalharBuscaPorId_QuandoNaoExiste() {
        when(restauranteRepository.buscarPorId(1L)).thenReturn(Optional.empty());
        assertThrows(BusinessException.class, () -> buscarPorIdUseCase.executar(1L));
    }

    @Test
    void deveAtualizarRestaurante() {
        Restaurante antigo = new Restaurante();
        antigo.setId(1L);
        antigo.setNome("Antigo");

        Restaurante novosDados = new Restaurante();
        novosDados.setNome("Novo Nome");
        novosDados.setEndereco("Rua Nova");

        when(restauranteRepository.buscarPorId(1L)).thenReturn(Optional.of(antigo));
        when(restauranteRepository.salvar(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Restaurante atualizado = atualizarUseCase.executar(1L, novosDados);

        assertEquals("Novo Nome", atualizado.getNome());
        assertEquals("Rua Nova", atualizado.getEndereco());
    }

    @Test
    void deveDeletarRestaurante() {
        when(restauranteRepository.existe(1L)).thenReturn(true);

        deletarUseCase.executar(1L);

        verify(restauranteRepository).deletar(1L);
    }

    @Test
    void naoDeveDeletar_QuandoNaoExiste() {
        when(restauranteRepository.existe(1L)).thenReturn(false);
        assertThrows(BusinessException.class, () -> deletarUseCase.executar(1L));
    }

    private Usuario criarDonoValido() {
        Usuario u = new Usuario();
        u.setId(1L);
        TipoUsuario t = new TipoUsuario();
        t.setNome("Dono de Restaurante");
        u.setTipo(t);
        return u;
    }
}