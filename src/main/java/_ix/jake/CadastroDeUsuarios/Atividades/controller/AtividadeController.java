package _ix.jake.CadastroDeUsuarios.Atividades.controller;

import _ix.jake.CadastroDeUsuarios.Atividades.DTOs.AtividadesDTO;
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
    public AtividadesDTO criarAtividade(@RequestBody AtividadesDTO atividade){
        return atividadeservice.criarAtividade(atividade);
    }

    @GetMapping("/listar")
    public List<AtividadesDTO> listarAtividades(){
        return atividadeservice.listarAtividades();
    }

    @GetMapping("/listar/{id}")
    public AtividadesDTO listarPorId(@PathVariable Long id){
        return atividadeservice.listarPorId(id);
    }

    @PutMapping("/atualizar/{id}")
    public AtividadesDTO atualizarPorID(@PathVariable Long id, @RequestBody AtividadesDTO atividadeAtualizada){
        return atividadeservice.atualizarAtividadePorId(id,atividadeAtualizada) ;
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarPorId(@PathVariable Long id){
        atividadeservice.deletarPorId(id);
    }
}
