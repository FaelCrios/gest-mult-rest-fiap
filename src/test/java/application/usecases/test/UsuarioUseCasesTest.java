package application.usecases.test;

import application.usecases.Usuario.*;
import domain.entities.Usuario;
import domain.entities.TipoUsuario;
import domain.exceptions.BusinessException;
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

@ExtendWith(MockitoExtension.class)
class UsuarioUseCasesTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private CriaUsuarioUseCase criaUsuarioUseCase;
    @InjectMocks
    private AtualizarUsuarioUseCase atualizarUsuarioUseCase;

    @Test
    void deveCriarUsuario_ComDadosValidos() {
        Usuario u = new Usuario();
        u.setNome("Ana");
        u.setEmail("ana@teste.com");
        TipoUsuario tipo = new TipoUsuario();
        tipo.setId(1L);
        u.setTipo(tipo);

        when(repository.salvar(any())).thenReturn(u);

        Usuario criado = criaUsuarioUseCase.executar(u);
        assertNotNull(criado);
    }

    @Test
    void deveFalharCriacao_SemEmail() {
        Usuario u = new Usuario();
        u.setNome("Ana");

        assertThrows(BusinessException.class, () -> criaUsuarioUseCase.executar(u));
    }

    @Test
    void deveAtualizarUsuario() {
        Usuario antigo = new Usuario();
        antigo.setId(1L);
        antigo.setNome("Ana");

        Usuario novo = new Usuario();
        novo.setNome("Ana Silva");
        novo.setEmail("ana.silva@teste.com");

        when(repository.buscarPorId(1L)).thenReturn(Optional.of(antigo));
        when(repository.salvar(any())).thenAnswer(i -> i.getArgument(0));

        Usuario atualizado = atualizarUsuarioUseCase.executar(1L, novo);

        assertEquals("Ana Silva", atualizado.getNome());
        assertEquals("ana.silva@teste.com", atualizado.getEmail());
    }
}