package _ix.jake.CadastroDeUsuarios.Usuarios;

import _ix.jake.CadastroDeUsuarios.Atividades.AtividadesModel;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_cadastro_de_usuarios")
public class UsuariosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private int idade;

    @ManyToOne
    @JoinColumn()
    private AtividadesModel atividades;

    public UsuariosModel(Long id, int idade, String email, String nome) {
        this.idade = idade;
        this.email = email;
        this.nome = nome;
    }

    public UsuariosModel() {
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
