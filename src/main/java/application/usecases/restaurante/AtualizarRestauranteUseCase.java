package application.usecases.restaurante;

import domain.entities.Restaurante;
import domain.exceptions.BusinessException;
import domain.gateways.RestauranteRepository;

public class AtualizarRestauranteUseCase {
    private final RestauranteRepository repository;

    public AtualizarRestauranteUseCase(RestauranteRepository repository) {
        this.repository = repository;
    }

    public Restaurante executar(Long id, Restaurante dadosAtualizados) {
        Restaurante existente = repository.buscarPorId(id)
                .orElseThrow(() -> new BusinessException("Restaurante não encontrado"));

        existente.setNome(dadosAtualizados.getNome());
        existente.setEndereco(dadosAtualizados.getEndereco());
        existente.setTipoCozinha(dadosAtualizados.getTipoCozinha());
        existente.setHorarioFuncionamento(dadosAtualizados.getHorarioFuncionamento());

        return repository.salvar(existente);
    }
}