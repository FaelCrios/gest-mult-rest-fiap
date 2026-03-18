package infrastructure.mappers;

import domain.entities.ItemCardapio;
import infrastructure.persistence.entities.ItemCardapioEntity;
import infrastructure.persistence.entities.RestauranteEntity;
import org.springframework.stereotype.Component;

@Component
public class ItemCardapioMapper {

    public ItemCardapioEntity toEntity(ItemCardapio domain, RestauranteEntity restaurante) {
        if (domain == null) return null;

        ItemCardapioEntity entity = new ItemCardapioEntity();
        entity.setId(domain.getId());
        entity.setNome(domain.getNome());
        entity.setDescricao(domain.getDescricao());
        entity.setPreco(domain.getPreco());
        entity.setDisponivelApenasNoRestaurante(domain.getDisponivelApenasNoRestaurante());
        entity.setCaminhoFoto(domain.getCaminhoFoto());
        entity.setRestaurante(restaurante);

        return entity;
    }

    public ItemCardapio toDomain(ItemCardapioEntity entity) {
        if (entity == null) return null;

        return new ItemCardapio(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getPreco(),
                entity.getDisponivelApenasNoRestaurante(),
                entity.getCaminhoFoto(),
                entity.getRestaurante().getId()
        );
    }
}