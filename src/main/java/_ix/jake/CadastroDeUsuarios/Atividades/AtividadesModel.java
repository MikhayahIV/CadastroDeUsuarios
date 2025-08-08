package _ix.jake.CadastroDeUsuarios.Atividades;

import _ix.jake.CadastroDeUsuarios.Usuarios.UsuariosModel;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_Atividades")
public class AtividadesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Dificuldade dificuldade;

    @OneToMany(mappedBy = "atividades")
    private List<UsuariosModel> usuarios;

}
