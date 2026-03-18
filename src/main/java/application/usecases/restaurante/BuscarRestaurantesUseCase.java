package application.usecases.restaurante;

import domain.entities.Restaurante;
import domain.gateways.RestauranteRepository;
import java.util.List;

public class BuscarRestaurantesUseCase {
    private final RestauranteRepository repository;

    public BuscarRestaurantesUseCase(RestauranteRepository repository) {
        this.repository = repository;
    }

    public List<Restaurante> executar() {
        return repository.listarTodos();
    }
}