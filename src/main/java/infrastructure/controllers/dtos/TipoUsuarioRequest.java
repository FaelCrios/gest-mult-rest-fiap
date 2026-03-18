package infrastructure.controllers.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record TipoUsuarioRequest(
        @Schema(description = "Nome do perfil", example = "Dono de Restaurante")
        String nome
) {}