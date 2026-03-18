package application.usecases.test;

import application.usecases.TipoUsuario.BuscarTiposUsuarioUseCase;
import application.usecases.TipoUsuario.CriarTipoUsuarioUseCase;
import domain.entities.TipoUsuario;
import domain.gateways.TipoUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TipoUsuarioUseCasesTest {

    @Mock
    private TipoUsuarioRepository repository;

    @InjectMocks
    private CriarTipoUsuarioUseCase criarUseCase;

    @InjectMocks
    private BuscarTiposUsuarioUseCase buscarUseCase;

    @Test
    void deveCriarTipoUsuario() {
        TipoUsuario tipo = new TipoUsuario();
        tipo.setNome("Dono");

        when(repository.salvar(any())).thenReturn(tipo);

        TipoUsuario resultado = criarUseCase.executar(tipo);

        assertNotNull(resultado);
        assertEquals("Dono", resultado.getNome());
        verify(repository).salvar(any());
    }

    @Test
    void deveBuscarTiposUsuarios() {
        when(repository.listarTodos()).thenReturn(List.of(new TipoUsuario(), new TipoUsuario()));

        List<TipoUsuario> lista = buscarUseCase.executar();

        assertEquals(2, lista.size());
        verify(repository).listarTodos();
    }
}