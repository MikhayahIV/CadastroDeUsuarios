package _ix.jake.CadastroDeUsuarios;

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

    public UsuariosModel(Long id, int idade, String email, String nome) {
        this.id = id;
        this.idade = idade;
        this.email = email;
        this.nome = nome;
    }

    public UsuariosModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
