package infrastructure.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tech Challenge - Sistema de Gestão de Restaurantes")
                        .version("2.0")
                        .description("API desenvolvida para a Fase 2 da Pós Tech FIAP - Arquitetura e Desenvolvimento Java. " +
                                "Permite a gestão de tipos de usuários, restaurantes e cardápios."));
    }
}
