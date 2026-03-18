package infrastructure.persistence.repositories;

import infrastructure.persistence.entities.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SpringRestauranteRepository extends JpaRepository<RestauranteEntity, Long> {

    List<RestauranteEntity> findByNomeContainingIgnoreCaseOrTipoCozinhaContainingIgnoreCaseOrEnderecoContainingIgnoreCase(
            String nome, String cozinha, String localizacao);
}