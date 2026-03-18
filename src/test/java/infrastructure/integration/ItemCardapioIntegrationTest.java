package infrastructure.integration;

import infrastructure.persistence.entities.ItemCardapioEntity;
import infrastructure.persistence.entities.RestauranteEntity;
import infrastructure.persistence.repositories.SpringItemCardapioRepository;
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
class ItemCardapioIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpringItemCardapioRepository springItemCardapioRepository;

    @Test
    void deveRetornarListaDeItensCardapioComStatus200_TesteIntegracao() throws Exception {
        RestauranteEntity restaurante = new RestauranteEntity();
        restaurante.setId(1L);

        ItemCardapioEntity entidadeMock = new ItemCardapioEntity();
        entidadeMock.setId(10L);
        entidadeMock.setNome("Pizza Margherita");
        entidadeMock.setDescricao("Molho de tomate, mozzarella e manjericão");
        entidadeMock.setPreco(45.0);
        entidadeMock.setDisponivelApenasNoRestaurante(false);
        entidadeMock.setRestaurante(restaurante);

        when(springItemCardapioRepository.findAll()).thenReturn(List.of(entidadeMock));

        mockMvc.perform(get("/api/itens-cardapio")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(10))
                .andExpect(jsonPath("$[0].nome").value("Pizza Margherita"))
                .andExpect(jsonPath("$[0].preco").value(45.0));
    }
}