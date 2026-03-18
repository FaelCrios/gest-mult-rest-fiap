package application.usecases.test;
import application.usecases.restaurante.CadastrarRestauranteUseCase;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Habilita o Mockito
class CadastrarRestauranteUseCaseTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private CadastrarRestauranteUseCase useCase;

    @Test
    void deveCadastrarRestaurante_QuandoDonoForValido() {
        TipoUsuario tipoDono = new TipoUsuario();
        tipoDono.setNome("Dono de Restaurante");

        Usuario dono = new Usuario();
        dono.setId(1L);
        dono.setTipo(tipoDono);

        Restaurante restaurante = new Restaurante();
        restaurante.setDono(dono);

        when(usuarioRepository.buscarPorId(1L)).thenReturn(Optional.of(dono));
        when(restauranteRepository.salvar(any(Restaurante.class))).thenReturn(restaurante);

        Restaurante resultado = useCase.executar(restaurante);

        assertNotNull(resultado);
        verify(restauranteRepository, times(1)).salvar(any(Restaurante.class));
    }

    @Test
    void deveLancarErro_QuandoUsuarioNaoForDono() {
        TipoUsuario tipoCliente = new TipoUsuario();
        tipoCliente.setNome("Cliente"); // Tipo errado

        Usuario cliente = new Usuario();
        cliente.setId(2L);
        cliente.setTipo(tipoCliente);

        Restaurante restaurante = new Restaurante();
        restaurante.setDono(cliente);

        when(usuarioRepository.buscarPorId(2L)).thenReturn(Optional.of(cliente));

        assertThrows(BusinessException.class, () -> useCase.executar(restaurante));

        verify(restauranteRepository, never()).salvar(any());
    }

    @Test
    void deveLancarErro_QuandoUsuarioNaoExiste() {
        Usuario donoInexistente = new Usuario();
        donoInexistente.setId(99L);

        Restaurante restaurante = new Restaurante();
        restaurante.setDono(donoInexistente);

        when(usuarioRepository.buscarPorId(99L)).thenReturn(Optional.empty());

        BusinessException ex = assertThrows(BusinessException.class, () -> useCase.executar(restaurante));
        assertTrue(ex.getMessage().contains("não encontrado"));
    }
}