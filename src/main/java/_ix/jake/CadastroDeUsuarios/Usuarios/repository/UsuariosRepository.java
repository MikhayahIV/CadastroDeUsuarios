package _ix.jake.CadastroDeUsuarios.Usuarios.repository;

import _ix.jake.CadastroDeUsuarios.Usuarios.model.UsuariosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<UsuariosModel,Long> {
}
