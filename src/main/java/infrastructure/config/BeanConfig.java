package infrastructure.config;

import application.usecases.ItemCardapio.*;
import application.usecases.TipoUsuario.BuscarTiposUsuarioUseCase;
import application.usecases.TipoUsuario.CriarTipoUsuarioUseCase;
import application.usecases.Usuario.*;
import application.usecases.restaurante.*;
import domain.gateways.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public CriarTipoUsuarioUseCase criarTipoUsuarioUseCase(TipoUsuarioRepository repository) {
        return new CriarTipoUsuarioUseCase(repository);
    }

    @Bean
    public CriaUsuarioUseCase criaUsuarioUseCase(UsuarioRepository repository) {
        return new CriaUsuarioUseCase(repository);
    }

    @Bean
    public CadastrarRestauranteUseCase cadastrarRestauranteUseCase(
            RestauranteRepository restauranteRepository,
            UsuarioRepository usuarioRepository) {

        return new CadastrarRestauranteUseCase(restauranteRepository, usuarioRepository);
    }

    @Bean
    public BuscarRestaurantesUseCase buscarRestaurantesUseCase(RestauranteRepository repository) {
        return new BuscarRestaurantesUseCase(repository);
    }

    @Bean
    public BuscarTiposUsuarioUseCase buscarTiposUsuarioUseCase(TipoUsuarioRepository repository) {
        return new BuscarTiposUsuarioUseCase(repository);
    }

    @Bean
    public BuscarUsuariosUseCase buscarUsuariosUseCase(UsuarioRepository repository) {
        return new BuscarUsuariosUseCase(repository);
    }

    @Bean
    public BuscarRestaurantePorIdUseCase buscarRestaurantePorIdUseCase(RestauranteRepository repository) {
        return new BuscarRestaurantePorIdUseCase(repository);
    }

    @Bean
    public AtualizarRestauranteUseCase atualizarRestauranteUseCase(RestauranteRepository repository) {
        return new AtualizarRestauranteUseCase(repository);
    }

    @Bean
    public DeletarRestauranteUseCase deletarRestauranteUseCase(RestauranteRepository repository) {
        return new DeletarRestauranteUseCase(repository);
    }

    @Bean
    public BuscarUsuarioPorIdUseCase buscarUsuarioPorEmailUseCase(UsuarioRepository repository) {
        return new BuscarUsuarioPorIdUseCase(repository);
    }

    @Bean
    public AtualizarUsuarioUseCase atualizarUsuarioUseCase(UsuarioRepository repository) {
        return new AtualizarUsuarioUseCase(repository);
    }

    @Bean
    public DeletarUsuarioUseCase deletarUsuarioUseCase(UsuarioRepository repository) {
        return new DeletarUsuarioUseCase(repository);
    }

    @Bean
    public AdicionarItemCardapioUseCase adicionarItemCardapioUseCase(ItemCardapioRepository itemRepository, RestauranteRepository restauranteRepository) {
        return new AdicionarItemCardapioUseCase(itemRepository, restauranteRepository);
    }

    @Bean
    public AtualizarItemCardapioUseCase atualizarItemCardapioUseCase(ItemCardapioRepository itemRepository) {
        return new AtualizarItemCardapioUseCase(itemRepository);
    }

    @Bean
    public BuscarItemCardapioPoIdUseCase buscarItemCardapioPoIdUseCase(ItemCardapioRepository itemRepository) {
        return new BuscarItemCardapioPoIdUseCase(itemRepository);
    }

    @Bean
    public DeletarItemCardapioUseCase deletarItemCardapioUseCase(ItemCardapioRepository itemRepository) {
        return new DeletarItemCardapioUseCase(itemRepository);
    }

    @Bean
    public ListarTodosItemCardapioUseCase listarTodosItemCardapioUseCase(ItemCardapioRepository itemRepository) {
        return new ListarTodosItemCardapioUseCase(itemRepository);
    }

}