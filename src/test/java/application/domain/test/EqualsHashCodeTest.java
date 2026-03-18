package application.domain.test;

import infrastructure.controllers.dtos.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EqualsHashCodeTest {

    @Test
    void deveTestarEqualsHashCodeEToStringDosDTOs() {

        UsuarioRequest u1 = new UsuarioRequest("Teste", "t@t.com", 1L);
        UsuarioRequest u2 = new UsuarioRequest("Teste", "t@t.com", 1L);
        UsuarioRequest u3 = new UsuarioRequest("Outro", "o@o.com", 2L);

        assertEquals(u1, u1);
        assertEquals(u1, u2);
        assertNotEquals(u1, u3);
        assertNotEquals(u1, null);
        assertNotEquals(u1, new Object());

        assertEquals(u1.hashCode(), u2.hashCode());
        assertNotNull(u1.toString());

        TipoUsuarioRequest tu1 = new TipoUsuarioRequest("Admin");
        TipoUsuarioRequest tu2 = new TipoUsuarioRequest("Admin");
        TipoUsuarioRequest tu3 = new TipoUsuarioRequest("Cliente");

        assertEquals(tu1, tu2);
        assertNotEquals(tu1, tu3);
        assertEquals(tu1.hashCode(), tu2.hashCode());
        assertNotNull(tu1.toString());

        RestauranteRequest r1 = new RestauranteRequest("A", "B", "C", "D", 1L);
        RestauranteRequest r2 = new RestauranteRequest("A", "B", "C", "D", 1L);
        RestauranteRequest r3 = new RestauranteRequest("X", "Y", "Z", "W", 2L);

        assertEquals(r1, r2);
        assertNotEquals(r1, r3);
        assertEquals(r1.hashCode(), r2.hashCode());
        assertNotNull(r1.toString());

        ItemCardapioRequest ic1 = new ItemCardapioRequest("A", "B", 10.0, true, "C", 1L);
        ItemCardapioRequest ic2 = new ItemCardapioRequest("A", "B", 10.0, true, "C", 1L);
        ItemCardapioRequest ic3 = new ItemCardapioRequest("X", "Y", 20.0, false, "Z", 2L);

        assertEquals(ic1, ic2);
        assertNotEquals(ic1, ic3);
        assertEquals(ic1.hashCode(), ic2.hashCode());
        assertNotNull(ic1.toString());

        ItemCardapioAtualizarRequest ica1 = new ItemCardapioAtualizarRequest("A", "B", 10.0, true, "C");
        ItemCardapioAtualizarRequest ica2 = new ItemCardapioAtualizarRequest("A", "B", 10.0, true, "C");
        ItemCardapioAtualizarRequest ica3 = new ItemCardapioAtualizarRequest("X", "Y", 20.0, false, "Z");

        assertEquals(ica1, ica2);
        assertNotEquals(ica1, ica3);
        assertEquals(ica1.hashCode(), ica2.hashCode());
        assertNotNull(ica1.toString());
    }
}