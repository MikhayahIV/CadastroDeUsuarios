package _ix.jake.CadastroDeUsuarios.Usuarios.service;

import _ix.jake.CadastroDeUsuarios.Usuarios.DTOs.UsuariosDTO;
import _ix.jake.CadastroDeUsuarios.Usuarios.DTOs.UsuariosMapper;
import _ix.jake.CadastroDeUsuarios.Usuarios.model.UsuariosModel;
import _ix.jake.CadastroDeUsuarios.Usuarios.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {


    private UsuariosRepository usuariosrepository;
    private UsuariosMapper usuariosmapper;

    public UsuarioService(UsuariosRepository usuariosrepository, UsuariosMapper usuariosmapper) {
        this.usuariosrepository = usuariosrepository;
        this.usuariosmapper = usuariosmapper;
    }

    public List<UsuariosDTO> listarUsuarios(){
       List<UsuariosModel> usuariosModels = usuariosrepository.findAll();
       return usuariosModels.stream()
               .map(usuariosmapper::map)
               .collect(Collectors.toList());
    }

    public UsuariosDTO usuarioPorId(Long id){
        Optional<UsuariosModel> usuarioPorIc = usuariosrepository.findById(id);
        return usuarioPorIc.map(usuariosmapper::map).orElse(null);
    }

    public UsuariosDTO criarUsuario(UsuariosDTO usuariosDTO){
        UsuariosModel usuariosModel = usuariosmapper.map(usuariosDTO);
        usuariosModel = usuariosrepository.save(usuariosModel);
        return usuariosmapper.map(usuariosModel);
    }

    public void deletarPorId(Long id){
        usuariosrepository.deleteById(id);
    }

    public UsuariosDTO atualizarPorId(Long id, UsuariosDTO usuarioAtualizado){
        Optional<UsuariosModel> usuariosModel = usuariosrepository.findById(id);
        if(usuariosModel.isPresent()){
            UsuariosModel usuariosAtt = usuariosmapper.map(usuarioAtualizado);
            usuariosAtt.setId(id);
            UsuariosModel usuarioSalvo = usuariosrepository.save(usuariosAtt);
            return usuariosmapper.map(usuarioSalvo);
        }
        return null;
    }

}
