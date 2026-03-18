package domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemCardapio {
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private Boolean disponivelApenasNoRestaurante;
    private String caminhoFoto;
    private Long restauranteId;
}