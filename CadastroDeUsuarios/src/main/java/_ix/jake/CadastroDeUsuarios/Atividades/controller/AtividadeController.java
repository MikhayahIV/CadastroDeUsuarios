package _ix.jake.CadastroDeUsuarios.Atividades.controller;

import _ix.jake.CadastroDeUsuarios.Atividades.DTOs.AtividadesDTO;
import _ix.jake.CadastroDeUsuarios.Atividades.service.AtividadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    private final AtividadeService atividadeservice;

    public AtividadeController(AtividadeService atividadeservice) {
        this.atividadeservice = atividadeservice;
    }

    @PostMapping("/adicionar")
    @Operation(summary = "Cria uma nova Atividade",description = "Rota cria uma nova atividade e insere no banco de dados.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201", description = "Atividade criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na criacao de Atividade")
    })
    public ResponseEntity<String> criarAtividade(@RequestBody AtividadesDTO atividade){
       AtividadesDTO ativid = atividadeservice.criarAtividade(atividade);
       return ResponseEntity.status(HttpStatus.CREATED)
               .body("Atividade criado: "+ativid.getNome() +" id: "+ativid.getId());
    }

    @GetMapping("/listar")
    @Operation(summary = "Lista todas as Atividades", description = "Esta rota retorna uma lista com todas as Atividades cadastrados no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aparece a lista de Atividades"),
            @ApiResponse(responseCode = "404", description = "Nenhuma Atividade encontrado")
    })
    public ResponseEntity<List<AtividadesDTO>> listarAtividades(){
        List<AtividadesDTO> listaDeAtividades = atividadeservice.listarAtividades();
        return ResponseEntity.ok(listaDeAtividades);
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Listar Atividade por ID",description = "Rota lista uma atividade por id.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "atividade encontrado sucesso."),
            @ApiResponse(responseCode = "404", description = "atividade nao encontrado")
    })
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
    @Operation(summary = "Altera a Atividade por ID",description = "Rota Altera uma ativdade por id.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Atividade alterado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Atividade nao encontrado, nao foi possivel alterar")
    })
    public ResponseEntity<?> atualizarPorID(@PathVariable Long id, @RequestBody AtividadesDTO atividadeAtualizada){
        AtividadesDTO atividade = atividadeservice.atualizarAtividadePorId(id,atividadeAtualizada);
        if(atividadeservice.atividadePorId(id) != null){
            return ResponseEntity.ok(atividade);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Atividade com id: "+id+" não foi encontrado" );
        }
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta uma Ativadade existente", description = "Esta rota deleta uma atividade com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atividade deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Atividade nao encontrado, nao foi possivel deletar")
    })
    public ResponseEntity<String> deletarPorId(@PathVariable Long id){
        if(atividadeservice.atividadePorId(id) != null){
            atividadeservice.deletarPorId(id);
            return ResponseEntity.ok("Atividade deletado com Sucesso");
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Atividade com id: "+id+" não foi encontrado");
        }
    }
}
