package _ix.jake.CadastroDeUsuarios.Atividades.service;

import _ix.jake.CadastroDeUsuarios.Atividades.DTOs.AtividadesDTO;
import _ix.jake.CadastroDeUsuarios.Atividades.DTOs.AtividadesMapper;
import _ix.jake.CadastroDeUsuarios.Atividades.model.AtividadesModel;
import _ix.jake.CadastroDeUsuarios.Atividades.repository.AtividadesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AtividadeService {

    private AtividadesRepository atividadesrepository;
    private AtividadesMapper atividadesmapper;

    public AtividadeService(AtividadesRepository atividadesrepository, AtividadesMapper atividadesmapper) {
        this.atividadesrepository = atividadesrepository;
        this.atividadesmapper = atividadesmapper;
    }

    public List<AtividadesDTO> listarAtividades(){
        List<AtividadesModel> atividadesModels = atividadesrepository.findAll();
        return atividadesModels.stream()
                .map(atividadesmapper::map)
                .collect(Collectors.toList());
    }

    public AtividadesDTO listarPorId(Long id){
        Optional<AtividadesModel> atividadesPorId = atividadesrepository.findById(id);
        return atividadesPorId.map(atividadesmapper::map).orElse(null);
    }

    public AtividadesDTO criarAtividade(AtividadesDTO atividade){
        AtividadesModel atividadesModel = atividadesmapper.map(atividade);
        atividadesModel = atividadesrepository.save(atividadesModel);
        return atividadesmapper.map(atividadesModel);
    }

    public void deletarPorId(Long id){
        atividadesrepository.deleteById(id);
    }

    public AtividadesDTO atualizarAtividadePorId(Long id, AtividadesDTO atividadesDTO){
        Optional<AtividadesModel> atividadesModel = atividadesrepository.findById(id);
        if(atividadesModel.isPresent()){
            AtividadesModel atividadeAtualizada = atividadesmapper.map(atividadesDTO);
            atividadeAtualizada.setId(id);
            AtividadesModel atividadeSalva = atividadesrepository.save(atividadeAtualizada);
            return atividadesmapper.map(atividadeSalva);
        }
        return null;
    }
}
