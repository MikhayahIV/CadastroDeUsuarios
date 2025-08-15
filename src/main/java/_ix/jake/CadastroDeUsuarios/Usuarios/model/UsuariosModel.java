package _ix.jake.CadastroDeUsuarios.Usuarios.model;

import _ix.jake.CadastroDeUsuarios.Atividades.model.AtividadesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_cadastro_de_usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuariosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name= "image_url")
    private String imgUrl;

    @Column(unique = true)
    private String email;

    private String cargo;

    private int idade;

    @ManyToOne
    @JoinColumn(name = "atividade_id")
    private AtividadesModel atividades;

}
