package infrastructure.mappers;

import domain.entities.Restaurante;
import infrastructure.persistence.entities.RestauranteEntity;
import org.springframework.stereotype.Component;

@Component
public class RestauranteMapper {

    private final UsuarioMapper usuarioMapper;

    public RestauranteMapper(UsuarioMapper usuarioMapper) {
        this.usuarioMapper = usuarioMapper;
    }

    public RestauranteEntity toEntity(Restaurante domain) {
        if (domain == null) return null;

        RestauranteEntity entity = new RestauranteEntity();
        entity.setId(domain.getId());
        entity.setNome(domain.getNome());
        entity.setEndereco(domain.getEndereco());
        entity.setTipoCozinha(domain.getTipoCozinha());
        entity.setHorarioFuncionamento(domain.getHorarioFuncionamento());

        if (domain.getDono() != null) {
            entity.setDono(usuarioMapper.toEntity(domain.getDono()));
        }

        return entity;
    }

    public Restaurante toDomain(RestauranteEntity entity) {
        if (entity == null) return null;

        Restaurante domain = new Restaurante();
        domain.setId(entity.getId());
        domain.setNome(entity.getNome());
        domain.setEndereco(entity.getEndereco());
        domain.setTipoCozinha(entity.getTipoCozinha());
        domain.setHorarioFuncionamento(entity.getHorarioFuncionamento());

        if (entity.getDono() != null) {
            domain.setDono(usuarioMapper.toDomain(entity.getDono()));
        }

        return domain;
    }
}