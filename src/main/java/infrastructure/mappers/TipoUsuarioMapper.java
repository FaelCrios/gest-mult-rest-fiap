package infrastructure.mappers;

import domain.entities.TipoUsuario;
import infrastructure.persistence.entities.TipoUsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class TipoUsuarioMapper {

    public TipoUsuarioEntity toEntity(TipoUsuario domain) {
        if (domain == null) return null;
        TipoUsuarioEntity entity = new TipoUsuarioEntity();
        entity.setId(domain.getId());
        entity.setNome(domain.getNome());
        return entity;
    }

    public TipoUsuario toDomain(TipoUsuarioEntity entity) {
        if (entity == null) return null;
        return new TipoUsuario(entity.getId(), entity.getNome());
    }
}
