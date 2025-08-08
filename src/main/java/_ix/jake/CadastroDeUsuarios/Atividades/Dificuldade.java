package _ix.jake.CadastroDeUsuarios.Atividades;

public enum Dificuldade {
    S("Muito Dificil"),
    A("Dificil"),
    B("Medio"),
    C("Facil"),
    E("Iniciante");

    private String descricao;

    Dificuldade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
