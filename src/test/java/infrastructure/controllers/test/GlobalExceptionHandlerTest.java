package infrastructure.controllers.test;

import domain.exceptions.BusinessException;
import infrastructure.controllers.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {

    @Test
    void deveTratarBusinessExceptionComStatus400() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        BusinessException ex = new BusinessException("Regra violada");

        ResponseEntity<Map<String, String>> resposta = handler.handleBusinessException(ex);

        assertEquals(400, resposta.getStatusCode().value());
        assertEquals("Regra violada", resposta.getBody().get("erro"));
    }
}