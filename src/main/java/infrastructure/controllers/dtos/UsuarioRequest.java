package infrastructure.controllers.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequest(
        @Schema(description = "Nome completo", example = "Rafael Colin")
        @NotBlank(message="O nome é obrigatório")
        String nome,

        @Schema(description = "E-mail válido", example = "rafael@email.com")
        @NotBlank(message = "O e-mail é obrigatório")
        String email,

        @Schema(description = "ID do tipo de usuário (ex: 1 para Dono)", example = "1")
        @NotNull(message = "O Id do tipo usuário é necessário")
        Long tipoUsuarioId
) {}