package domain.entities;

import domain.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurante {
    private Long id;
    private String nome;
    private String endereco;
    private String tipoCozinha;
    private String horarioFuncionamento;
    private Usuario dono;

    public void validarDono() {
        if (this.dono == null) {
            throw new BusinessException("O restaurante deve ter um dono associado.");
        }

        if (this.dono.getTipo() == null || !"Dono de Restaurante".equals(this.dono.getTipo().getNome())) {
            throw new BusinessException("O usuário atribuído deve ser do tipo 'Dono de Restaurante'.");
        }
    }
}