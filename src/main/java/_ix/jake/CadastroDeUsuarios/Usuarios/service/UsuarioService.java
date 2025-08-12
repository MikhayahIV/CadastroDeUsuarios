package _ix.jake.CadastroDeUsuarios.Usuarios.service;

import _ix.jake.CadastroDeUsuarios.Usuarios.model.UsuariosModel;
import _ix.jake.CadastroDeUsuarios.Usuarios.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {


    private UsuariosRepository usuariosRepository;

    public UsuarioService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public List<UsuariosModel> listarUsuarios(){
        return usuariosRepository.findAll();
    }
}
