package _ix.jake.CadastroDeUsuarios.Atividades.service;

import _ix.jake.CadastroDeUsuarios.Atividades.model.AtividadesModel;
import _ix.jake.CadastroDeUsuarios.Atividades.repository.AtividadesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeService {

    private AtividadesRepository atividadesrepository;

    public AtividadeService(AtividadesRepository atividadesrepository) {
        this.atividadesrepository = atividadesrepository;
    }

    public List<AtividadesModel> listarAtividades(){
        return atividadesrepository.findAll();
    }

    public AtividadesModel listarPorId(Long id){
        Optional<AtividadesModel> atividadesPorId = atividadesrepository.findById(id);
        return atividadesPorId.orElse(null);
    }

    public AtividadesModel criarAtividade(AtividadesModel atividade){
        return atividadesrepository.save(atividade);
    }
}
