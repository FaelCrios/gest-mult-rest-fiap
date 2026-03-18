package application.usecases.restaurante;

import domain.entities.Restaurante;
import domain.entities.Usuario;
import domain.exceptions.BusinessException;
import domain.gateways.RestauranteRepository;
import domain.gateways.UsuarioRepository;

public class CadastrarRestauranteUseCase {
    private final RestauranteRepository restauranteRepository;
    private final UsuarioRepository usuarioRepository;

    public CadastrarRestauranteUseCase(RestauranteRepository restauranteRepository, UsuarioRepository usuarioRepository) {
        this.restauranteRepository = restauranteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Restaurante executar(Restaurante restaurante) {
        if (restaurante.getDono() != null && restaurante.getDono().getId() != null) {
            Usuario donoCompleto = usuarioRepository.buscarPorId(restaurante.getDono().getId())
                    .orElseThrow(() -> new BusinessException("Usuário (Dono) não encontrado com o ID informado."));

            restaurante.setDono(donoCompleto);
        }

        restaurante.validarDono();

        return restauranteRepository.salvar(restaurante);
    }
}