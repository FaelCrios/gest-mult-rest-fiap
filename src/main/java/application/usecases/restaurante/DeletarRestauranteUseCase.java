package application.usecases.restaurante;

import domain.exceptions.BusinessException;
import domain.gateways.RestauranteRepository;

public class DeletarRestauranteUseCase {
    private final RestauranteRepository repository;

    public DeletarRestauranteUseCase(RestauranteRepository repository) {
        this.repository = repository;
    }

    public void executar(Long id) {
        if (!repository.existe(id)) {
            throw new BusinessException("Restaurante não encontrado");
        }
        repository.deletar(id);
    }
}