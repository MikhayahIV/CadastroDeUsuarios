package _ix.jake.CadastroDeUsuarios.Atividades.model;

import _ix.jake.CadastroDeUsuarios.Usuarios.model.UsuariosModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
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


    private String dificuldade;

    @OneToMany(mappedBy = "atividades")
    @JsonIgnore
    private List<UsuariosModel> usuarios;

}
