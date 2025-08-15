package _ix.jake.CadastroDeUsuarios.Usuarios.DTOs;

import _ix.jake.CadastroDeUsuarios.Usuarios.model.UsuariosModel;
import org.springframework.stereotype.Component;

@Component
public class UsuariosMapper {
    public UsuariosModel map(UsuariosDTO usuariosDTO){

        UsuariosModel usuariosModel = new UsuariosModel();

        usuariosModel.setId(usuariosDTO.getId());
        usuariosModel.setNome(usuariosDTO.getNome());
        usuariosModel.setIdade(usuariosDTO.getIdade());
        usuariosModel.setEmail(usuariosDTO.getEmail());
        usuariosModel.setCargo(usuariosDTO.getCargo());
        usuariosModel.setImgUrl(usuariosDTO.getImgUrl());
        usuariosModel.setAtividades(usuariosDTO.getAtividades());

        return  usuariosModel;
    }

    public UsuariosDTO map(UsuariosModel usuariosModel){

        UsuariosDTO usuariosDTO = new UsuariosDTO();

        usuariosDTO.setId(usuariosModel.getId());
        usuariosDTO.setNome(usuariosModel.getNome());
        usuariosDTO.setIdade(usuariosModel.getIdade());
        usuariosDTO.setEmail(usuariosModel.getEmail());
        usuariosDTO.setCargo(usuariosModel.getCargo());
        usuariosDTO.setImgUrl(usuariosModel.getImgUrl());
        usuariosDTO.setAtividades(usuariosModel.getAtividades());

        return usuariosDTO;
    }
}
