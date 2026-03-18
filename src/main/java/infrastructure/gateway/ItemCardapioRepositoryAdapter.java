package infrastructure.gateway;

import domain.entities.ItemCardapio;
import domain.gateways.ItemCardapioRepository;
import infrastructure.mappers.ItemCardapioMapper;
import infrastructure.persistence.repositories.SpringItemCardapioRepository;
import infrastructure.persistence.repositories.SpringRestauranteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ItemCardapioRepositoryAdapter implements ItemCardapioRepository {
    private final SpringItemCardapioRepository springItemRepository;
    private final SpringRestauranteRepository springRestauranteRepository;
    private final ItemCardapioMapper mapper;

    public ItemCardapioRepositoryAdapter(SpringItemCardapioRepository springItemRepository,
                                         SpringRestauranteRepository springRestauranteRepository,
                                         ItemCardapioMapper mapper) {
        this.springItemRepository = springItemRepository;
        this.springRestauranteRepository = springRestauranteRepository;
        this.mapper = mapper;
    }

    @Override
    public ItemCardapio salvar(ItemCardapio item, Long restauranteId) {
        var restauranteEntity = springRestauranteRepository.findById(restauranteId)
                .orElseThrow(); // Já validado pelo UseCase

        var entity = mapper.toEntity(item, restauranteEntity);
        return mapper.toDomain(springItemRepository.save(entity));
    }

    @Override
    public List<ItemCardapio> listarTodos() {
        return springItemRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ItemCardapio> buscarPorId(Long id) {
        return springItemRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        springItemRepository.deleteById(id);
    }

    @Override
    public boolean existe(Long id) {
        return springItemRepository.existsById(id);
    }
}