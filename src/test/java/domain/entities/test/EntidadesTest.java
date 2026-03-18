package domain.entities.test;

import domain.entities.Restaurante;
import domain.entities.TipoUsuario;
import domain.entities.Usuario;
import domain.exceptions.BusinessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntidadesTest {

    @Test
    void usuarioDeveValidarCamposObrigatorios() {
        Usuario u = new Usuario();

        assertThrows(BusinessException.class, u::validar);

        u.setNome("João");
        assertThrows(BusinessException.class, u::validar);

        u.setEmail("joao@email.com");
        assertThrows(BusinessException.class, u::validar);

        u.setTipo(new TipoUsuario(1L, "Cliente"));
        assertDoesNotThrow(u::validar);
    }

    @Test
    void usuarioDeveVerificarSePodeGerenciarRestaurante() {
        Usuario u = new Usuario();
        TipoUsuario tipo = new TipoUsuario();
        u.setTipo(tipo);

        tipo.setNome("Dono de Restaurante");
        assertTrue(u.podeGerenciarRestaurante());

        tipo.setNome("dono de restaurante");
        assertTrue(u.podeGerenciarRestaurante());

        tipo.setNome("Cliente");
        assertFalse(u.podeGerenciarRestaurante());
    }

    @Test
    void restauranteDeveValidarDono() {
        Restaurante r = new Restaurante();

        assertThrows(BusinessException.class, r::validarDono, "O restaurante deve ter um dono associado.");

        Usuario u = new Usuario();
        r.setDono(u);

        assertThrows(BusinessException.class, r::validarDono, "O usuário atribuído deve ser do tipo 'Dono de Restaurante'.");

        TipoUsuario t = new TipoUsuario();
        u.setTipo(t);

        t.setNome("Cliente");
        assertThrows(BusinessException.class, r::validarDono);

        t.setNome("Dono de Restaurante");
        assertDoesNotThrow(r::validarDono);
    }

    @Test
    void tipoUsuarioDeveValidarNome() {
        TipoUsuario t = new TipoUsuario();

        assertThrows(BusinessException.class, t::validarNome);

        t.setNome("");
        assertThrows(BusinessException.class, t::validarNome);

        t.setNome("Admin");
        assertDoesNotThrow(t::validarNome);
    }
}