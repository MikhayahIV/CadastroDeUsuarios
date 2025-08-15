package _ix.jake.CadastroDeUsuarios.Usuarios.service;

import _ix.jake.CadastroDeUsuarios.Usuarios.model.UsuariosModel;
import _ix.jake.CadastroDeUsuarios.Usuarios.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {


    private UsuariosRepository usuariosrepository;

    public UsuarioService(UsuariosRepository usuariosrepository) {
        this.usuariosrepository = usuariosrepository;
    }

    public List<UsuariosModel> listarUsuarios(){
        return usuariosrepository.findAll();
    }

    public UsuariosModel usuarioPorId(Long id){
        Optional<UsuariosModel> usuarioPorIc = usuariosrepository.findById(id);
        return usuarioPorIc.orElse(null);
    }

    public UsuariosModel criarUsuario(UsuariosModel usuario){
        return usuariosrepository.save(usuario);
    }

    public void deletarPorId(Long id){
        usuariosrepository.deleteById(id);
    }

    public UsuariosModel atualizarPorId(Long id, UsuariosModel usuarioAtualizado){
        if(usuariosrepository.existsById(id)){
            usuarioAtualizado.setId(id);
            return usuariosrepository.save(usuarioAtualizado);
        }
        return null;
    }

}
