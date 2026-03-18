package infrastructure.integration;

import infrastructure.persistence.entities.RestauranteEntity;
import infrastructure.persistence.repositories.SpringRestauranteRepository;
import org.example.Main;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=password",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class RestauranteIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpringRestauranteRepository springRestauranteRepository;

    @Test
    void deveRetornarListaDeRestaurantesComStatus200_TesteIntegracao() throws Exception {
        RestauranteEntity entidadeMock = new RestauranteEntity();
        entidadeMock.setId(1L);
        entidadeMock.setNome("Restaurante Ponto Final");
        entidadeMock.setEndereco("Avenida Principal, 100");
        entidadeMock.setTipoCozinha("Portuguesa");
        entidadeMock.setHorarioFuncionamento("10h - 22h");

        when(springRestauranteRepository.findAll()).thenReturn(List.of(entidadeMock));

        mockMvc.perform(get("/api/restaurantes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("Restaurante Ponto Final"))
                .andExpect(jsonPath("$[0].tipoCozinha").value("Portuguesa"));
    }
}