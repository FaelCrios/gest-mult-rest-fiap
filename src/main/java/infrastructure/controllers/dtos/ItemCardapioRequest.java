package infrastructure.controllers.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemCardapioRequest(
        @Schema(description = "Nome do prato", example = "Picanha na Chapa")
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @Schema(description = "Descrição detalhada", example = "Acompanha arroz e fritas")
        @NotBlank(message = "Descrição é obrigatório")
        String descricao,

        @Schema(description = "Preço unitário", example = "89.90")
        @NotNull(message = "Nome é obrigatório")
        Double preco,

        @Schema(description = "Se o item é exclusivo para consumo no local", example = "false")
        @NotNull(message="É necessário informar se o item é exclusivo de consumo no restaurante")
        Boolean disponivelApenasNoRestaurante,

        @Schema(description = "URL ou caminho da foto", example = "/imagens/picanha.jpg")
        @NotBlank(message="É necessário informar um caminho ou endereço para foto")
        String caminhoFoto,

        @Schema(description = "ID do restaurante ao qual o item pertence", example = "1")
        @NotNull(message="É necessário informar se o item é exclusivo de consumo no restaurante")
        Long restauranteId
) {}