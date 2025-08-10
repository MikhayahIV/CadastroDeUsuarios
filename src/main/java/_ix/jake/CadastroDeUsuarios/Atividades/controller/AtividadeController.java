package _ix.jake.CadastroDeUsuarios.Atividades.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    @PostMapping("/adicionar-atividade")
    public String criarAtividade(){
        return "Atividade Criado";
    }

    @GetMapping("/lista-de-atividades")
    public String mostraratividade(){
        return "Lista de Atividades";
    }

    @GetMapping("/atividade-id")
    public String mostrarAtividadeID(){
        return "Atividade por id";
    }

    @PutMapping("/atualizar-atividade-id")
    public String alterarAtividadePorID(){
        return "Alterar Atividade";
    }

    @DeleteMapping("/deletar-atividade-id")
    public String deletarAtividadePorID(){
        return "Atividade Deletado";
    }
}
