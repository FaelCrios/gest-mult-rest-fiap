package infrastructure.gateway;

import domain.entities.Restaurante;
import domain.gateways.RestauranteRepository;
import infrastructure.mappers.RestauranteMapper;
import infrastructure.persistence.repositories.SpringRestauranteRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RestauranteRepositoryAdapter implements RestauranteRepository {

    private final SpringRestauranteRepository springRepository;
    private final RestauranteMapper mapper;

    public RestauranteRepositoryAdapter(SpringRestauranteRepository springRepository, RestauranteMapper mapper) {
        this.springRepository = springRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        var entity = mapper.toEntity(restaurante);
        var entitySalva = springRepository.save(entity);
        return mapper.toDomain(entitySalva);
    }

    @Override
    public List<Restaurante> listarTodos() {
        return springRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Restaurante> buscarPorId(Long id) {
        return springRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        springRepository.deleteById(id);
    }

    @Override
    public boolean existe(Long id) {
        return springRepository.existsById(id);
    }
}