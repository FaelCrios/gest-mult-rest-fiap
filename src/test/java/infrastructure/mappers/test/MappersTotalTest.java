package infrastructure.mappers.test;

import domain.entities.*;
import infrastructure.mappers.*;
import infrastructure.persistence.entities.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MappersTotalTest {

    @Test
    void testItemCardapioMapper() {
        ItemCardapioMapper mapper = new ItemCardapioMapper();

        assertNull(mapper.toDomain((ItemCardapioEntity) null));
        assertNull(mapper.toEntity(null, null));

        infrastructure.persistence.entities.RestauranteEntity restauranteEntity = new infrastructure.persistence.entities.RestauranteEntity();
        restauranteEntity.setId(99L);

        ItemCardapio domain = new ItemCardapio();
        domain.setId(1L);
        domain.setNome("X");
        domain.setPreco(10.0);

        ItemCardapioEntity entity = mapper.toEntity(domain, restauranteEntity);
        assertNotNull(entity);
        assertEquals(domain.getId(), entity.getId());
        assertEquals(99L, entity.getRestaurante().getId());

        ItemCardapio domain2 = mapper.toDomain(entity);
        assertNotNull(domain2);
        assertEquals(entity.getId(), domain2.getId());
    }

    @Test
    void testRestauranteMapper() {
        UsuarioMapper usuarioMapper = new UsuarioMapper(new TipoUsuarioMapper());

        RestauranteMapper mapper = new RestauranteMapper(usuarioMapper);

        assertNull(mapper.toDomain((RestauranteEntity) null));
        assertNull(mapper.toEntity((Restaurante) null));

        Restaurante domain = new Restaurante();
        domain.setId(1L);
        domain.setNome("Restaurante X");

        Usuario dono = new Usuario();
        dono.setId(5L);
        domain.setDono(dono);

        RestauranteEntity entity = mapper.toEntity(domain);

        assertNotNull(entity);
        assertEquals(domain.getId(), entity.getId());
        assertNotNull(entity.getDono());
        assertEquals(5L, entity.getDono().getId());

        Restaurante domain2 = mapper.toDomain(entity);

        assertNotNull(domain2);
        assertEquals(entity.getId(), domain2.getId());
        assertNotNull(domain2.getDono());
        assertEquals(5L, domain2.getDono().getId());
    }

    @Test
    void testTipoUsuarioMapper() {
        TipoUsuarioMapper mapper = new TipoUsuarioMapper();
        assertNull(mapper.toDomain((TipoUsuarioEntity) null));
        assertNull(mapper.toEntity((TipoUsuario) null));

        TipoUsuario domain = new TipoUsuario();
        domain.setId(1L); domain.setNome("Dono");
        TipoUsuarioEntity entity = mapper.toEntity(domain);
        assertNotNull(entity);

        TipoUsuario domain2 = mapper.toDomain(entity);
        assertNotNull(domain2);
    }

    @Test
    void testUsuarioMapper() {
        TipoUsuarioMapper tipoMapper = new TipoUsuarioMapper();
        UsuarioMapper mapper = new UsuarioMapper(tipoMapper);

        assertNull(mapper.toDomain((UsuarioEntity) null));
        assertNull(mapper.toEntity((Usuario) null));

        Usuario domain = new Usuario();
        domain.setId(1L);
        domain.setNome("User");
        domain.setEmail("user@teste.com");

        TipoUsuario tipo = new TipoUsuario();
        tipo.setId(2L);
        tipo.setNome("Cliente");
        domain.setTipo(tipo);

        UsuarioEntity entity = mapper.toEntity(domain);

        assertNotNull(entity);
        assertEquals(domain.getId(), entity.getId());
        assertNotNull(entity.getTipo());
        assertEquals(2L, entity.getTipo().getId());

        Usuario domain2 = mapper.toDomain(entity);

        assertNotNull(domain2);
        assertEquals(entity.getId(), domain2.getId());
        assertNotNull(domain2.getTipo());
        assertEquals(2L, domain2.getTipo().getId());
    }
}