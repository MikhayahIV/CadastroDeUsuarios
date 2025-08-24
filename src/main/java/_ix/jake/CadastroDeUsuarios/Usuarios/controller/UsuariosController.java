package _ix.jake.CadastroDeUsuarios.Usuarios.controller;


import _ix.jake.CadastroDeUsuarios.Usuarios.DTOs.UsuariosDTO;
import _ix.jake.CadastroDeUsuarios.Usuarios.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    private UsuarioService usuarioService;

    public UsuariosController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/boasVindas")
    public String boasVindas(){
        return "Essa é a minha primeira mensagem";
    }

    @PostMapping("/adicionar")
    public ResponseEntity<String> criarUsuario(@RequestBody UsuariosDTO usuario){
        UsuariosDTO novoUsuario = usuarioService.criarUsuario(usuario);
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario criado com sucesso: "+novoUsuario.getNome() + " ID:" +novoUsuario.getId());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuariosDTO>> mostraraUsuarios(){
        List<UsuariosDTO> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/listar/{id}")
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
    public ResponseEntity<?> alteraraPorId(@PathVariable Long id, @RequestBody UsuariosDTO usuarioAtualizado){
        UsuariosDTO usuario = usuarioService.atualizarPorId(id,usuarioAtualizado);
        if(usuario !=null){
            return ResponseEntity.ok(usuario);
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario com id: "+id +" não foi encontado");
        }
    }

    @DeleteMapping("/deletar/{id}")
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
