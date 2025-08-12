package _ix.jake.CadastroDeUsuarios.Atividades.controller;

import _ix.jake.CadastroDeUsuarios.Atividades.model.AtividadesModel;
import _ix.jake.CadastroDeUsuarios.Atividades.service.AtividadeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    private AtividadeService atividadeservice;

    public AtividadeController(AtividadeService atividadeservice) {
        this.atividadeservice = atividadeservice;
    }

    @PostMapping("/adicionar-atividade")
    public String criarAtividade(){
        return "Atividade Criado";
    }

    @GetMapping("/lista-de-atividades")
    public List<AtividadesModel> listarAtividades(){
        return atividadeservice.listarAtividades();
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
