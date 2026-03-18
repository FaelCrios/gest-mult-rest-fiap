package domain.entities.test;

import infrastructure.controllers.dtos.*;
import infrastructure.persistence.entities.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class GettersSettersTest {

    @Test
    void deveTestarAtributosDasEntidadesEDtos() {
        UsuarioRequest uReq = new UsuarioRequest("Teste", "t@t.com", 1L);
        assertNotNull(uReq.nome());
        assertNotNull(uReq.email());
        assertNotNull(uReq.tipoUsuarioId());

        TipoUsuarioRequest tuReq = new TipoUsuarioRequest("Admin");
        assertNotNull(tuReq.nome());

        RestauranteRequest rReq = new RestauranteRequest("A", "B", "C", "D", 1L);
        assertNotNull(rReq.nome());
        assertNotNull(rReq.endereco());

        ItemCardapioRequest icReq = new ItemCardapioRequest("A", "B", 10.0, true, "C", 1L);
        assertNotNull(icReq.nome());
        assertNotNull(icReq.preco());

        ItemCardapioAtualizarRequest icaReq = new ItemCardapioAtualizarRequest("A", "B", 10.0, true, "C");
        assertNotNull(icaReq.nome());

        UsuarioEntity ue = new UsuarioEntity();
        ue.setId(1L);
        ue.setNome("T");
        ue.setEmail("E");
        assertNotNull(ue.getId());
        assertNotNull(ue.getNome());
        assertNotNull(ue.getEmail());

        RestauranteEntity re = new RestauranteEntity();
        re.setId(1L);
        re.setNome("R");
        re.setEndereco("E");
        re.setTipoCozinha("Cozinha Italiana");
        re.setHorarioFuncionamento("H");
        assertNotNull(re.getId());
        assertNotNull(re.getNome());
        assertNotNull(re.getEndereco());
        assertNotNull(re.getTipoCozinha());
        assertNotNull(re.getHorarioFuncionamento());

        ItemCardapioEntity ice = new ItemCardapioEntity();
        ice.setId(1L);
        ice.setNome("I");
        ice.setDescricao("D");
        ice.setPreco(10.0);
        ice.setDisponivelApenasNoRestaurante(true);
        ice.setCaminhoFoto("foto.png");
        ice.setRestaurante(new RestauranteEntity());

        assertNotNull(ice.getId());
        assertNotNull(ice.getNome());
        assertNotNull(ice.getDescricao());
        assertNotNull(ice.getPreco());
        assertNotNull(ice.getDisponivelApenasNoRestaurante());
        assertNotNull(ice.getCaminhoFoto());
        assertNotNull(ice.getRestaurante());
    }
}