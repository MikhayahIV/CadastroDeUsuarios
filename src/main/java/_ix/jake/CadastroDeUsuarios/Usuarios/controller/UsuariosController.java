package _ix.jake.CadastroDeUsuarios.Usuarios.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @GetMapping("/boasVindas")
    public String boasVindas(){
        return "Essa é a minha primeira mensagem";
    }

    @PostMapping("/adicionar-usuario")
    public String criarUsuario(){
        return "Usuari Criado";
    }

    @GetMapping("/lista-de-usuarios")
    public String mostraratividade(){
        return "Lista de Usuarios";
    }

    @GetMapping("/usuario-id")
    public String mostrarAtividadeID(){
        return "Usuario por id";
    }

    @PutMapping("/atualizar-usuario-id")
    public String alterarAtividadePorID(){
        return "Alterar usuario";
    }

    @DeleteMapping("/deletar-usuario-id")
    public String deletarAtividadePorID(){
        return "usuario Deletado";
    }
}
