package _ix.jake.CadastroDeUsuarios.Usuarios.controller;


import _ix.jake.CadastroDeUsuarios.Usuarios.DTOs.UsuariosDTO;
import _ix.jake.CadastroDeUsuarios.Usuarios.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    private final UsuarioService usuarioService;

    public UsuariosController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/boasVindas")
    @Operation(summary = "Mensagem de boas vindas",description = "essa rota da uma mensagem de boas vindas para quem acessa ela.")
    public String boasVindas(){
        return "Essa é a minha primeira mensagem";
    }

    @PostMapping("/adicionar")
    @Operation(summary = "Cria um novo Usuarios",description = "Rota cria um novo usuarios e insere no banco de dados.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201", description = "Usuario criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na criacao do Usuario")
    })
    public ResponseEntity<String> criarUsuario(@RequestBody UsuariosDTO usuario){
        UsuariosDTO novoUsuario = usuarioService.criarUsuario(usuario);
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario criado com sucesso: "+novoUsuario.getNome() + " ID:" +novoUsuario.getId());
    }

    @GetMapping("/listar")
    @Operation(summary = "Lista todos os Usuarios", description = "Esta rota retorna uma lista com todos os Usuarios cadastrados no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aparece a lista de Usuarios"),
            @ApiResponse(responseCode = "404", description = "Nenhum Usuario encontrado")
    })
    public ResponseEntity<List<UsuariosDTO>> mostraraUsuarios(){
        List<UsuariosDTO> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Listar usuario por ID",description = "Rota lista um usuarios por id.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Usuario encontrado sucesso."),
            @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    })
    public ResponseEntity<?> listarPorId(@PathVariable Long id){
        UsuariosDTO usuario = usuarioService.usuarioPorId(id);
        if (usuario != null){
            return ResponseEntity.ok(usuario);
        } else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario com id: "+id+" não encontrado");
        }
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Altera usuario por ID",description = "Rota Altera um usuarios por id.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Usuario alterado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Usuario nao encontrado, nao foi possivel alterar")
    })
    public ResponseEntity<?> alteraraPorId(
            @Parameter(description = "Usuario manda o ID no caminho da requisicao")
            @PathVariable Long id,
            @Parameter(description = "Usuario manda os dados do Usuario a ser atualizado no corpo da requisicao")
            @RequestBody UsuariosDTO usuarioAtualizado){
        UsuariosDTO usuario = usuarioService.atualizarPorId(id,usuarioAtualizado);
        if(usuario !=null){
            return ResponseEntity.ok(usuario);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario com id: "+id +" não foi encontado");
        }
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta um Usuario existente", description = "Esta rota deleta um usuario com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuario nao encontrado, nao foi possivel deletar")
    })
    public ResponseEntity<String> deletarPorId(@PathVariable Long id){
        if(usuarioService.usuarioPorId(id) !=null){
            usuarioService.deletarPorId(id);
            return ResponseEntity.ok("Usuario com id: "+id +" deletado com sucesso");
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario com id: "+id +" não foi encontado");
        }
    }
}
