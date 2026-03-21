package application.usecases.test;

import application.usecases.Usuario.CriaUsuarioUseCase;
import domain.entities.Usuario;
import domain.gateways.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriaUsuarioUseCaseTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private CriaUsuarioUseCase useCase;

    @Test
    void deveCriarUsuario_QuandoDadosValidos() {

        Usuario usuario = new Usuario();
        usuario.setNome("Teste da Silva");
        usuario.setEmail("teste@email.com");

        domain.entities.TipoUsuario tipo = new domain.entities.TipoUsuario();
        tipo.setId(1L);
        tipo.setNome("Cliente");
        usuario.setTipo(tipo);

        when(repository.salvar(usuario)).thenReturn(usuario);

        Usuario resultado = useCase.executar(usuario);

        assertNotNull(resultado);
        verify(repository).salvar(usuario);
    }
}