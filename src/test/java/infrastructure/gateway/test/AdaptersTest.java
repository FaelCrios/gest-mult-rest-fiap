package infrastructure.gateway.test;

import domain.entities.Usuario;
import infrastructure.gateway.RestauranteRepositoryAdapter;
import infrastructure.gateway.UsuarioRepositoryAdapter;
import infrastructure.mappers.RestauranteMapper;
import infrastructure.mappers.UsuarioMapper;
import infrastructure.persistence.entities.UsuarioEntity;
import infrastructure.persistence.repositories.SpringRestauranteRepository;
import infrastructure.persistence.repositories.SpringUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdaptersTest {

    @Mock private SpringUsuarioRepository springUsuarioRepository;
    @Mock private UsuarioMapper usuarioMapper;
    @InjectMocks private UsuarioRepositoryAdapter usuarioAdapter;

    @Test
    void deveSalvarUsuarioNoAdapter() {
        Usuario dominio = new Usuario();
        UsuarioEntity entity = new UsuarioEntity();

        when(usuarioMapper.toEntity(dominio)).thenReturn(entity);
        when(springUsuarioRepository.save(entity)).thenReturn(entity);
        when(usuarioMapper.toDomain(entity)).thenReturn(dominio);

        Usuario salvo = usuarioAdapter.salvar(dominio);
        assertNotNull(salvo);
    }

    @Test
    void deveBuscarUsuarioNoAdapter() {
        UsuarioEntity entity = new UsuarioEntity();
        when(springUsuarioRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(usuarioMapper.toDomain(entity)).thenReturn(new Usuario());

        Optional<Usuario> encontrado = usuarioAdapter.buscarPorId(1L);
        assertTrue(encontrado.isPresent());
    }

    @Mock private SpringRestauranteRepository springRestauranteRepository;
    @Mock private RestauranteMapper restauranteMapper;
    @InjectMocks private RestauranteRepositoryAdapter restauranteAdapter;

    @Test
    void deveDeletarRestauranteEVerificarExistencia() {
        when(springRestauranteRepository.existsById(1L)).thenReturn(true);
        assertTrue(restauranteAdapter.existe(1L));

        restauranteAdapter.deletar(1L);
        verify(springRestauranteRepository).deleteById(1L);
    }
}