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

    @PostMapping("/adicionar")
    public String criarAtividade(){
        return "Atividade Criado";
    }

    @GetMapping("/listar")
    public List<AtividadesModel> listarAtividades(){
        return atividadeservice.listarAtividades();
    }

    @GetMapping("/listar/{id}")
    public AtividadesModel listarPorId(@PathVariable Long id){
        return atividadeservice.listarPorId(id);
    }

    @PutMapping("/atualizar/{id}")
    public String alterarAtividadePorID(){
        return "Alterar Atividade";
    }

    @DeleteMapping("/deletar/{id}")
    public String deletarAtividadePorID(){
        return "Atividade Deletado";
    }
}
