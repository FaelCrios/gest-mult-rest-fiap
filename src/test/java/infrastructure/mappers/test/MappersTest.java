package infrastructure.mappers.test;

import domain.entities.TipoUsuario;
import infrastructure.mappers.TipoUsuarioMapper;
import infrastructure.persistence.entities.TipoUsuarioEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MappersTest {

    @Test
    void deveMapearTipoUsuario() {
        TipoUsuarioMapper mapper = new TipoUsuarioMapper();

        TipoUsuario dominio = new TipoUsuario();
        dominio.setId(1L);
        dominio.setNome("Admin");

        TipoUsuarioEntity entity = mapper.toEntity(dominio);
        assertEquals(1L, entity.getId());
        assertEquals("Admin", entity.getNome());

        TipoUsuarioEntity novaEntity = new TipoUsuarioEntity();
        novaEntity.setId(2L);
        novaEntity.setNome("Cliente");

        TipoUsuario novoDominio = mapper.toDomain(novaEntity);
        assertEquals(2L, novoDominio.getId());
        assertEquals("Cliente", novoDominio.getNome());
    }

    @Test
    void deveLidarComNulosNoTipoUsuarioMapper() {
        TipoUsuarioMapper mapper = new TipoUsuarioMapper();
        assertNull(mapper.toEntity((TipoUsuario) null));
        assertNull(mapper.toDomain((TipoUsuarioEntity) null));
    }
}