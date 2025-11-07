package _ix.jake.CadastroDeUsuarios.Usuarios;

import _ix.jake.CadastroDeUsuarios.Usuarios.DTOs.UsuariosDTO;
import _ix.jake.CadastroDeUsuarios.Usuarios.DTOs.UsuariosMapper;
import _ix.jake.CadastroDeUsuarios.Usuarios.model.UsuariosModel;
import _ix.jake.CadastroDeUsuarios.Usuarios.repository.UsuariosRepository;
import _ix.jake.CadastroDeUsuarios.Usuarios.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServiceTest {
    @Mock
    private UsuariosRepository usuariosRepository;

    @Mock
    private UsuariosMapper usuariosMapper;

    @InjectMocks
    private UsuarioService usuariosService;

    private UsuariosModel model;
    private UsuariosDTO dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        model = new UsuariosModel();
        model.setId(1L);
        model.setNome("Vitor Dias");
        model.setEmail("vitor@email.com");

        dto = new UsuariosDTO();
        dto.setId(1L);
        dto.setNome("Vitor Dias");
        dto.setEmail("vitor@email.com");
    }

    @Test
    void deveListarUsuarios() {
        when(usuariosRepository.findAll()).thenReturn(List.of(model));
        when(usuariosMapper.map(model)).thenReturn(dto);

        List<UsuariosDTO> resultado = usuariosService.listarUsuarios();

        assertEquals(1, resultado.size());
        assertEquals("Vitor Dias", resultado.get(0).getNome());
        verify(usuariosRepository, times(1)).findAll();
    }

    @Test
    void deveRetornarUsuarioPorId() {
        when(usuariosRepository.findById(1L)).thenReturn(Optional.of(model));
        when(usuariosMapper.map(model)).thenReturn(dto);

        UsuariosDTO resultado = usuariosService.usuarioPorId(1L);

        assertNotNull(resultado);
        assertEquals("Vitor Dias", resultado.getNome());
        verify(usuariosRepository).findById(1L);
    }

    @Test
    void deveRetornarNullSeNaoEncontrarUsuarioPorId() {
        when(usuariosRepository.findById(1L)).thenReturn(Optional.empty());

        UsuariosDTO resultado = usuariosService.usuarioPorId(1L);

        assertNull(resultado);
        verify(usuariosRepository).findById(1L);
    }

    @Test
    void deveCriarUsuario() {
        when(usuariosMapper.map(dto)).thenReturn(model);
        when(usuariosRepository.save(model)).thenReturn(model);
        when(usuariosMapper.map(model)).thenReturn(dto);

        UsuariosDTO resultado = usuariosService.criarUsuario(dto);

        assertNotNull(resultado);
        assertEquals("Vitor Dias", resultado.getNome());
        verify(usuariosRepository, times(1)).save(model);
    }

    @Test
    void deveDeletarUsuarioPorId() {
        doNothing().when(usuariosRepository).deleteById(1L);

        usuariosService.deletarPorId(1L);

        verify(usuariosRepository, times(1)).deleteById(1L);
    }

    @Test
    void deveAtualizarUsuarioPorId() {
        when(usuariosRepository.findById(1L)).thenReturn(Optional.of(model));
        when(usuariosMapper.map(dto)).thenReturn(model);
        when(usuariosRepository.save(model)).thenReturn(model);
        when(usuariosMapper.map(model)).thenReturn(dto);

        UsuariosDTO resultado = usuariosService.atualizarPorId(1L, dto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(usuariosRepository).save(model);
    }

    @Test
    void deveRetornarNullAoAtualizarUsuarioInexistente() {
        when(usuariosRepository.findById(1L)).thenReturn(Optional.empty());

        UsuariosDTO resultado = usuariosService.atualizarPorId(1L, dto);

        assertNull(resultado);
        verify(usuariosRepository, never()).save(any());
    }
}
