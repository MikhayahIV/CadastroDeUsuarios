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
    public String criarUsuario(){
        return "Usuari Criado";
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
    public String alterarUsuarisPorID(){
        return "Alterar usuario";
    }

    @DeleteMapping("/deletar/{id}")
    public String deletarUsuariosPorID(){
        return "usuario Deletado";
    }
}
