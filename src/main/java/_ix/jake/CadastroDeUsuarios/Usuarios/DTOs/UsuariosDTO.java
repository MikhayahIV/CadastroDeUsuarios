package _ix.jake.CadastroDeUsuarios.Usuarios.DTOs;

import _ix.jake.CadastroDeUsuarios.Atividades.model.AtividadesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuariosDTO {

    private Long id;
    private String nome;
    private String imgUrl;
    private String email;
    private String cargo;
    private int idade;
    private AtividadesModel atividades;
}
