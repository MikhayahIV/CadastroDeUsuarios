package _ix.jake.CadastroDeUsuarios.Atividades.controller;

import _ix.jake.CadastroDeUsuarios.Atividades.DTOs.AtividadesDTO;
import _ix.jake.CadastroDeUsuarios.Atividades.service.AtividadeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> criarAtividade(@RequestBody AtividadesDTO atividade){
       AtividadesDTO ativid = atividadeservice.criarAtividade(atividade);
       return ResponseEntity.status(HttpStatus.CREATED)
               .body("Usuario criado: "+ativid.getNome() +" id: "+ativid.getId());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AtividadesDTO>> listarAtividades(){
        List<AtividadesDTO> listaDeAtividades = atividadeservice.listarAtividades();
        return ResponseEntity.ok(listaDeAtividades);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id){
           AtividadesDTO atividade =atividadeservice.atividadePorId(id);
        if(atividadeservice.atividadePorId(id) != null){
            return  ResponseEntity.ok(atividade);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Atividade com id: "+id+" não foi encontrada");
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarPorID(@PathVariable Long id, @RequestBody AtividadesDTO atividadeAtualizada){
        AtividadesDTO atividade = atividadeservice.atualizarAtividadePorId(id,atividadeAtualizada);
        if(atividadeservice.atividadePorId(id) != null){
            return ResponseEntity.ok(atividade);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario com id: "+id+" não foi encontrado" );
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarPorId(@PathVariable Long id){
        if(atividadeservice.atividadePorId(id) != null){
            atividadeservice.deletarPorId(id);
            return ResponseEntity.ok("Usuario deletado com Sucesso");
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario com id: "+id+" não foi encontrado");
        }
    }
}
