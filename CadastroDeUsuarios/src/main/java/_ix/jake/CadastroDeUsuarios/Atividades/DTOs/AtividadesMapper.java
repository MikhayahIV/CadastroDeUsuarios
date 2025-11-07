package _ix.jake.CadastroDeUsuarios.Atividades.DTOs;

import _ix.jake.CadastroDeUsuarios.Atividades.model.AtividadesModel;
import org.springframework.stereotype.Component;

@Component
public class AtividadesMapper {

    public AtividadesModel map(AtividadesDTO atividadesDTO){
        AtividadesModel atividadesModel = new AtividadesModel();
        atividadesModel.setId(atividadesDTO.getId());
        atividadesModel.setNome(atividadesDTO.getNome());
        atividadesModel.setDificuldade(atividadesDTO.getDificuldade());
        atividadesModel.setUsuarios(atividadesDTO.getUsuarios());

        return atividadesModel;
    }

    public AtividadesDTO map(AtividadesModel atividadesModel){
        AtividadesDTO atividadesDTO = new AtividadesDTO();
        atividadesDTO.setDificuldade(atividadesModel.getDificuldade());
        atividadesDTO.setId(atividadesModel.getId());
        atividadesDTO.setNome(atividadesModel.getNome());
        atividadesDTO.setUsuarios(atividadesModel.getUsuarios());

        return atividadesDTO;
    }
}
