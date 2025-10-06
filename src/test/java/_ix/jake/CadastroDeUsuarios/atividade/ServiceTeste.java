package _ix.jake.CadastroDeUsuarios.atividade;

import _ix.jake.CadastroDeUsuarios.Atividades.DTOs.AtividadesDTO;
import _ix.jake.CadastroDeUsuarios.Atividades.DTOs.AtividadesMapper;
import _ix.jake.CadastroDeUsuarios.Atividades.model.AtividadesModel;
import _ix.jake.CadastroDeUsuarios.Atividades.repository.AtividadesRepository;
import _ix.jake.CadastroDeUsuarios.Atividades.service.AtividadeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServiceTeste {


    @Mock
    private AtividadesRepository atividadesRepository;

    @Mock
    private AtividadesMapper atividadesMapper;

    @InjectMocks
    private AtividadeService atividadesService;

    private AtividadesModel model;
    private AtividadesDTO dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        model = new AtividadesModel();
        model.setId(1L);
        model.setNome("Atividade Teste");

        dto = new AtividadesDTO();
        dto.setId(1L);
        dto.setNome("Atividade Teste");
    }

    @Test
    void deveListarAtividades() {
        when(atividadesRepository.findAll()).thenReturn(List.of(model));
        when(atividadesMapper.map(model)).thenReturn(dto);

        List<AtividadesDTO> resultado = atividadesService.listarAtividades();

        assertEquals(1, resultado.size());
        assertEquals("Atividade Teste", resultado.get(0).getNome());
        verify(atividadesRepository, times(1)).findAll();
    }

    @Test
    void deveRetornarAtividadePorId() {
        when(atividadesRepository.findById(1L)).thenReturn(Optional.of(model));
        when(atividadesMapper.map(model)).thenReturn(dto);

        AtividadesDTO resultado = atividadesService.atividadePorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(atividadesRepository).findById(1L);
    }

    @Test
    void deveRetornarNullSeNaoEncontrarAtividadePorId() {
        when(atividadesRepository.findById(1L)).thenReturn(Optional.empty());

        AtividadesDTO resultado = atividadesService.atividadePorId(1L);

        assertNull(resultado);
        verify(atividadesRepository).findById(1L);
    }

    @Test
    void deveCriarAtividade() {
        when(atividadesMapper.map(dto)).thenReturn(model);
        when(atividadesRepository.save(model)).thenReturn(model);
        when(atividadesMapper.map(model)).thenReturn(dto);

        AtividadesDTO resultado = atividadesService.criarAtividade(dto);

        assertNotNull(resultado);
        assertEquals("Atividade Teste", resultado.getNome());
        verify(atividadesRepository).save(model);
    }

    @Test
    void deveDeletarPorId() {
        doNothing().when(atividadesRepository).deleteById(1L);

        atividadesService.deletarPorId(1L);

        verify(atividadesRepository, times(1)).deleteById(1L);
    }

    @Test
    void deveAtualizarAtividadePorId() {
        when(atividadesRepository.findById(1L)).thenReturn(Optional.of(model));
        when(atividadesMapper.map(dto)).thenReturn(model);
        when(atividadesRepository.save(model)).thenReturn(model);
        when(atividadesMapper.map(model)).thenReturn(dto);

        AtividadesDTO resultado = atividadesService.atualizarAtividadePorId(1L, dto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(atividadesRepository).save(model);
    }

    @Test
    void deveRetornarNullAoTentarAtualizarAtividadeInexistente() {
        when(atividadesRepository.findById(1L)).thenReturn(Optional.empty());

        AtividadesDTO resultado = atividadesService.atualizarAtividadePorId(1L, dto);

        assertNull(resultado);
        verify(atividadesRepository, never()).save(any());
    }
}