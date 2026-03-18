package domain.entities;

import domain.exceptions.BusinessException;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private TipoUsuario tipo;

    public boolean podeGerenciarRestaurante() {
        return "Dono de Restaurante".equalsIgnoreCase(this.tipo.getNome());
    }

    public void validar() {
        if (nome == null || email == null || tipo == null) {
            throw new BusinessException("Campos obrigatórios ausentes.");
        }
    }
}
