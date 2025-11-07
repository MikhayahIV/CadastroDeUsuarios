package _ix.jake.CadastroDeUsuarios.Atividades.DTOs;

import _ix.jake.CadastroDeUsuarios.Usuarios.model.UsuariosModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtividadesDTO {

    private Long id;
    private String nome;
    private String dificuldade;
    private List<UsuariosModel> usuarios;
}
