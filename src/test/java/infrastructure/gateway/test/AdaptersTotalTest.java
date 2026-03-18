package infrastructure.gateway.test;

import domain.entities.*;
import infrastructure.gateway.*;
import infrastructure.mappers.*;
import infrastructure.persistence.entities.*;
import infrastructure.persistence.repositories.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdaptersTotalTest {

    @Mock private SpringUsuarioRepository springUsuarioRepository;
    @Mock private UsuarioMapper usuarioMapper;
    @InjectMocks private UsuarioRepositoryAdapter usuarioAdapter;

    @Mock private SpringRestauranteRepository springRestauranteRepository;
    @Mock private RestauranteMapper restauranteMapper;
    @InjectMocks private RestauranteRepositoryAdapter restauranteAdapter;

    @Mock private SpringTipoUsuarioRepository springTipoUsuarioRepository;
    @Mock private TipoUsuarioMapper tipoUsuarioMapper;
    @InjectMocks private TipoUsuarioRepositoryAdapter tipoUsuarioAdapter;

    @Test
    void testeUsuarioAdapter() {
        Usuario dominio = new Usuario();
        UsuarioEntity entity = new UsuarioEntity();

        when(usuarioMapper.toEntity(dominio)).thenReturn(entity);
        when(springUsuarioRepository.save(entity)).thenReturn(entity);
        when(usuarioMapper.toDomain(entity)).thenReturn(dominio);

        assertNotNull(usuarioAdapter.salvar(dominio));

        when(springUsuarioRepository.findById(1L)).thenReturn(Optional.of(entity));
        assertTrue(usuarioAdapter.buscarPorId(1L).isPresent());

        when(springUsuarioRepository.findAll()).thenReturn(List.of(entity));
        assertFalse(usuarioAdapter.listarTodos().isEmpty());

        when(springUsuarioRepository.existsById(1L)).thenReturn(true);
        assertTrue(usuarioAdapter.existe(1L));

        usuarioAdapter.deletar(1L);
        verify(springUsuarioRepository).deleteById(1L);
    }

    @Test
    void testeRestauranteAdapter() {
        Restaurante dominio = new Restaurante();
        RestauranteEntity entity = new RestauranteEntity();

        when(restauranteMapper.toEntity(dominio)).thenReturn(entity);
        when(springRestauranteRepository.save(entity)).thenReturn(entity);
        when(restauranteMapper.toDomain(entity)).thenReturn(dominio);

        assertNotNull(restauranteAdapter.salvar(dominio));

        when(springRestauranteRepository.findById(1L)).thenReturn(Optional.of(entity));
        assertTrue(restauranteAdapter.buscarPorId(1L).isPresent());

        when(springRestauranteRepository.findAll()).thenReturn(List.of(entity));
        assertFalse(restauranteAdapter.listarTodos().isEmpty());

        when(springRestauranteRepository.existsById(1L)).thenReturn(true);
        assertTrue(restauranteAdapter.existe(1L));

        restauranteAdapter.deletar(1L);
        verify(springRestauranteRepository).deleteById(1L);
    }
}