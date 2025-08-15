package _ix.jake.CadastroDeUsuarios.Atividades.service;

import _ix.jake.CadastroDeUsuarios.Atividades.DTOs.AtividadesDTO;
import _ix.jake.CadastroDeUsuarios.Atividades.DTOs.AtividadesMapper;
import _ix.jake.CadastroDeUsuarios.Atividades.model.AtividadesModel;
import _ix.jake.CadastroDeUsuarios.Atividades.repository.AtividadesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeService {

    private AtividadesRepository atividadesrepository;
    private AtividadesMapper atividadesmapper;

    public AtividadeService(AtividadesRepository atividadesrepository, AtividadesMapper atividadesmapper) {
        this.atividadesrepository = atividadesrepository;
        this.atividadesmapper = atividadesmapper;
    }

    public List<AtividadesModel> listarAtividades(){
        return atividadesrepository.findAll();
    }

    public AtividadesModel listarPorId(Long id){
        Optional<AtividadesModel> atividadesPorId = atividadesrepository.findById(id);
        return atividadesPorId.orElse(null);
    }

    public AtividadesDTO criarAtividade(AtividadesDTO atividade){
        AtividadesModel atividadesModel = atividadesmapper.map(atividade);
        atividadesModel = atividadesrepository.save(atividadesModel);
        return atividadesmapper.map(atividadesModel);
    }

    public void deletarPorId(Long id){
        atividadesrepository.deleteById(id);
    }

    public AtividadesModel atualizarAtividadePorId(Long id, AtividadesModel atividadeAtualizada){
        if(atividadesrepository.existsById(id)){
            atividadeAtualizada.setId(id);
            return atividadesrepository.save(atividadeAtualizada);
        }
        return null;
    }
}
