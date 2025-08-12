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

    @PostMapping("/adicionar-usuario")
    public String criarUsuario(){
        return "Usuari Criado";
    }

    @GetMapping("/lista-de-usuarios")
    public List<UsuariosModel> mostraraUsuarios(){
        return usuarioService.listarUsuarios() ;
    }

    @GetMapping("/usuario-id")
    public String mostrarUsuarisID(){
        return "Usuario por id";
    }

    @PutMapping("/atualizar-usuario-id")
    public String alterarUsuarisPorID(){
        return "Alterar usuario";
    }

    @DeleteMapping("/deletar-usuario-id")
    public String deletarUsuariosPorID(){
        return "usuario Deletado";
    }
}
