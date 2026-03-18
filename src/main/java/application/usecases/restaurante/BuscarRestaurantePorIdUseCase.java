package application.usecases.restaurante;

import domain.entities.Restaurante;
import domain.exceptions.BusinessException;
import domain.gateways.RestauranteRepository;

public class BuscarRestaurantePorIdUseCase {
    private final RestauranteRepository repository;

    public BuscarRestaurantePorIdUseCase(RestauranteRepository repository) {
        this.repository = repository;
    }

    public Restaurante executar(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new BusinessException("Restaurante não encontrado"));
    }
}