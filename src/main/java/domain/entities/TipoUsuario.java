package domain.entities;

import domain.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoUsuario {
    private Long id;
    private String nome;

    public void validarNome(){
        if(nome == null || nome.isEmpty()){
            throw new BusinessException("O nome do tipo é obrigatório");
        }
    }
}
