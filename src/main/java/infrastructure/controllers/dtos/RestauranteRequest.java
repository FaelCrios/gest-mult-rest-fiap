package infrastructure.controllers.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RestauranteRequest(
        @Schema(example = "Tech Grill")
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @Schema(example = "Av. Paulista, 1100")
        @NotBlank(message = "O endereço é obrigatório")
        String endereco,

        @Schema(example = "Brasileira")
        @NotBlank(message = "O tipo de cozinha é obrigatório")
        String tipoCozinha,

        @Schema(example = "09:00 - 22:00")
        @NotBlank(message = "O horário é obrigatório")
        String horarioFuncionamento,

        @Schema(example = "1")
        @NotNull(message = "O ID do dono é obrigatório")
        Long donoId
) {}