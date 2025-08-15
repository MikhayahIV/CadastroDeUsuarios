package _ix.jake.CadastroDeUsuarios.Usuarios.controller;


import _ix.jake.CadastroDeUsuarios.Usuarios.model.UsuariosModel;
import _ix.jake.CadastroDeUsuarios.Usuarios.service.UsuarioService;
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
    public UsuariosModel criarUsuario(@RequestBody UsuariosModel usuario){
        return usuarioService.criarUsuario(usuario);
    }

    @GetMapping("/listar")
    public List<UsuariosModel> mostraraUsuarios(){
        return usuarioService.listarUsuarios() ;
    }

    @GetMapping("/listar/{id}")
    public UsuariosModel listarPorId(@PathVariable Long id){
        return usuarioService.usuarioPorId(id);
    }

    @PutMapping("/atualizar/{id}")
    public UsuariosModel alteraraPorId(@PathVariable Long id, @RequestBody UsuariosModel usuarioAtualizado){
        return usuarioService.atualizarPorId(id,usuarioAtualizado) ;
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarPorId(@PathVariable Long id){
        usuarioService.deletarPorId(id);
    }
}
