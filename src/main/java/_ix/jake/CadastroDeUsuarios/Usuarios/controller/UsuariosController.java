package _ix.jake.CadastroDeUsuarios.Usuarios.controller;


import _ix.jake.CadastroDeUsuarios.Usuarios.DTOs.UsuariosDTO;
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
    public UsuariosDTO criarUsuario(@RequestBody UsuariosDTO usuario){
        return usuarioService.criarUsuario(usuario);
    }

    @GetMapping("/listar")
    public List<UsuariosDTO> mostraraUsuarios(){
        return usuarioService.listarUsuarios() ;
    }

    @GetMapping("/listar/{id}")
    public UsuariosDTO listarPorId(@PathVariable Long id){
        return usuarioService.usuarioPorId(id);
    }

    @PutMapping("/atualizar/{id}")
    public UsuariosDTO alteraraPorId(@PathVariable Long id, @RequestBody UsuariosDTO usuarioAtualizado){
        return usuarioService.atualizarPorId(id,usuarioAtualizado) ;
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarPorId(@PathVariable Long id){
        usuarioService.deletarPorId(id);
    }
}
