package _ix.jake.CadastroDeUsuarios.Atividades.service;

import _ix.jake.CadastroDeUsuarios.Atividades.model.AtividadesModel;
import _ix.jake.CadastroDeUsuarios.Atividades.repository.AtividadesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtividadeService {

    private AtividadesRepository atividadesrepository;

    public AtividadeService(AtividadesRepository atividadesrepository) {
        this.atividadesrepository = atividadesrepository;
    }

    public List<AtividadesModel> listarAtividades(){
        return atividadesrepository.findAll();
    }

}
