package _ix.jake.CadastroDeUsuarios.Atividades.model;

import _ix.jake.CadastroDeUsuarios.Atividades.model.enums.Dificuldade;
import _ix.jake.CadastroDeUsuarios.Usuarios.model.UsuariosModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_Atividades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtividadesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Dificuldade dificuldade;

    @OneToMany(mappedBy = "atividades")
    private List<UsuariosModel> usuarios;

}
