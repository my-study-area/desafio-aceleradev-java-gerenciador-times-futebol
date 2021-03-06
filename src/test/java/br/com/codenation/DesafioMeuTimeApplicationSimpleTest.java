package br.com.codenation;

import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;
import br.com.codenation.model.TimeDeFutebol;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DesafioMeuTimeApplicationSimpleTest {

    @Test
    public void deveIncluirTimeQueNaoExiste(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
        assertEquals(new Long(1L), desafioMeuTimeApplication.buscarTimes().get(0));
    }

    @Test(expected = IdentificadorUtilizadoException.class)
    public void deveIdentificarIdentificadorUtilizadoAoIncluirTime(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
        desafioMeuTimeApplication.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
    }

    @Test
    public void deveIncluirJogadorNaoExistenteEmTimeJaExistente(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
        desafioMeuTimeApplication.incluirJogador(1l, 1l, "Jogador", LocalDate.now(), 1, BigDecimal.TEN);
        assertEquals(new Long(1L), desafioMeuTimeApplication.buscarJogadoresDoTime(1L).get(0));
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void deveIncluirJogadorNaoExistenteEmTimeNaoExistente(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirJogador(1l, 1l, "Jogador", LocalDate.now(), 1, BigDecimal.TEN);
    }

    @Test(expected = IdentificadorUtilizadoException.class)
    public void deveIdentificarIdentificadorUtilizadoAoIncluirJogador(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
        desafioMeuTimeApplication.incluirJogador(1l, 1l, "Jogador", LocalDate.now(), 1, BigDecimal.TEN);
        desafioMeuTimeApplication.incluirJogador(1l, 1l, "Jogador", LocalDate.now(), 1, BigDecimal.TEN);
    }

    @Test
    public void deveBuscarTimeExistentePorId(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
        desafioMeuTimeApplication.incluirTime(2l, "Teste2", LocalDate.now(), "branco", "branco");
        assertEquals("Teste2", desafioMeuTimeApplication.buscarTimePorId(2L).getNome());
        assertEquals(new Long(1), desafioMeuTimeApplication.buscarTimePorId(1L).getId());
    }

    @Test
    public void deveBuscarJogadorExistentePorId(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
        desafioMeuTimeApplication.incluirJogador(1l, 1l, "Jogador1", LocalDate.now(), 1, BigDecimal.TEN);
        desafioMeuTimeApplication.incluirJogador(2l, 1l, "Jogador2", LocalDate.now(), 1, BigDecimal.TEN);
        assertEquals("Jogador1", desafioMeuTimeApplication.buscarJogadorPorId(1l).getNome());
    }

    @Test
    public void deveDefinirCapitao(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
        desafioMeuTimeApplication.incluirJogador(1l, 1l, "Jogador", LocalDate.now(), 1, BigDecimal.TEN);
        desafioMeuTimeApplication.definirCapitao(1l);
        assertEquals(new Long(1L), desafioMeuTimeApplication.buscarCapitaoDoTime(1L));
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void deveIdentificarTimeNaoEncontradoAoBuscarCapitaoDeTimeNaoExistente(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.buscarCapitaoDoTime(1l);
    }

    @Test(expected = CapitaoNaoInformadoException.class)
    public void deveIdentificarCapitaoNaoInformadoAoBuscarCapitaoDeTimeSemCapitao(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
        desafioMeuTimeApplication.buscarCapitaoDoTime(1l);
    }

    @Test(expected = JogadorNaoEncontradoException.class)
    public void deveIdentificarJogadorNaoEncontradoAoDefinirCapitaoComJogadorNaoIncluso(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.definirCapitao(1l);
    }

    @Test
    public void deveBuscarNomeJogador(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
        desafioMeuTimeApplication.incluirJogador(1l, 1l, "Jogador", LocalDate.now(), 1, BigDecimal.TEN);
        assertEquals("Jogador", desafioMeuTimeApplication.buscarNomeJogador(1L));
    }

    @Test(expected = JogadorNaoEncontradoException.class)
    public void deveIdentificarJogadorNaoEncontradoAoBuscarNomeJogadorDoJogadorNaoIncluso(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.buscarNomeJogador(100L);
    }

    @Test
    public void deveBuscarNomeTime(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
        assertEquals("Teste1", desafioMeuTimeApplication.buscarNomeTime(1L));
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void deveIdentificaTimeNaoEncontadoAoBuscarNomeTimeDoTimeNaoIncluso(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.buscarNomeTime(1L);
    }

    @Test
    public void deveBuscarJogadoresDoTime(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
        desafioMeuTimeApplication.incluirJogador(2l, 1l, "Jogador", LocalDate.now(), 1, BigDecimal.TEN);
        desafioMeuTimeApplication.incluirJogador(3l, 1l, "Jogador", LocalDate.now(), 1, BigDecimal.TEN);
        List<Long> jogadoresTime = desafioMeuTimeApplication.buscarJogadoresDoTime(1L);
        assertTrue(jogadoresTime.contains(2L));
        assertTrue(jogadoresTime.contains(3L));
    }

    @Test
    public void deveBuscarJogadoresDoTimeOrdenadosPorId(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
        desafioMeuTimeApplication.incluirJogador(3l, 1l, "Jogador1", LocalDate.now(), 1, BigDecimal.TEN);
        desafioMeuTimeApplication.incluirJogador(2l, 1l, "Jogador2", LocalDate.now(), 1, BigDecimal.TEN);
        desafioMeuTimeApplication.incluirJogador(1l, 1l, "Jogador3", LocalDate.now(), 1, BigDecimal.TEN);
        List<Long> jogadoresTime = desafioMeuTimeApplication.buscarJogadoresDoTime(1L);
        assertEquals(1l, jogadoresTime.get(0).longValue());
        assertEquals(2l, jogadoresTime.get(1).longValue());
        assertEquals(3l, jogadoresTime.get(2).longValue());
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void deveIdentificarTimeNaoEncontradoAoBuscarJogadoresDoTimeDeUmTimeNaoIncluso(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.buscarJogadoresDoTime(1L);
    }

    @Test
    public void deveBuscarMelhorJogadorDoTime(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
        desafioMeuTimeApplication.incluirJogador(2l, 1l, "Jogador", LocalDate.now(), 1, BigDecimal.TEN);
        desafioMeuTimeApplication.incluirJogador(3l, 1l, "Jogador2", LocalDate.now(), 2, BigDecimal.TEN);
        assertEquals(3L, desafioMeuTimeApplication.buscarMelhorJogadorDoTime(1L).longValue());
    }
    
    @Test(expected = TimeNaoEncontradoException.class)
    public void deveIdentificarTimeNaoEncontradoAoBuscarMelhorJogadorDoTimeDeTimeNaoIncluso(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.buscarMelhorJogadorDoTime(100L);
    }
    
    @Test 
    public void deveBuscarMelhorJogadorDoTimeComEmpateNoNivelDeHabilidade(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
        desafioMeuTimeApplication.incluirJogador(3l, 1l, "Jogador1", LocalDate.now(), 3, BigDecimal.TEN);
        desafioMeuTimeApplication.incluirJogador(2l, 1l, "Jogador2", LocalDate.now(), 3, BigDecimal.TEN);
        desafioMeuTimeApplication.incluirJogador(1l, 1l, "Jogador0", LocalDate.now(), 3, BigDecimal.TEN);
        
        assertEquals(1l,(long)desafioMeuTimeApplication.buscarMelhorJogadorDoTime(1L));
    }

    @Test
    public void deveBuscarJogadorMaisVelho(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
        desafioMeuTimeApplication.incluirJogador(2l, 1l, "Jogador", LocalDate.now().minus(25, ChronoUnit.YEARS), 1, BigDecimal.TEN);
        desafioMeuTimeApplication.incluirJogador(3l, 1l, "Jogador2", LocalDate.now().minus(20, ChronoUnit.YEARS), 2, BigDecimal.TEN);
        assertEquals(2L, desafioMeuTimeApplication.buscarJogadorMaisVelho(1L).longValue());
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void deveIdentificarTimeNaoEncontradoAoBuscarJogadorMaisVelhoDeTimeNaoIncluso(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.buscarJogadorMaisVelho(100L);
    }

    @Test
    public void deveBuscarTodosOsTimesExistentes(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
        desafioMeuTimeApplication.incluirTime(2l, "Teste1", LocalDate.now(), "branco", "branco");
        desafioMeuTimeApplication.incluirTime(3l, "Teste1", LocalDate.now(), "branco", "branco");
        assertEquals(3L, desafioMeuTimeApplication.buscarTimes().size());
    }

    @Test
    public void deveBuscarTodosOsTimesExistentesOrdenadaPorId(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirTime(3l, "Time3", LocalDate.now(), "branco", "branco");
        desafioMeuTimeApplication.incluirTime(2l, "Time2", LocalDate.now(), "branco", "branco");
        desafioMeuTimeApplication.incluirTime(1l, "Time1", LocalDate.now(), "branco", "branco");
        assertEquals(1L, desafioMeuTimeApplication.buscarTimes().get(0).longValue());
        assertEquals(2L, desafioMeuTimeApplication.buscarTimes().get(1).longValue());
        assertEquals(3L, desafioMeuTimeApplication.buscarTimes().get(2).longValue());
    }

    @Test
    public void deveListarABuscaDeTodosOsTimesVazia(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        assertEquals(new ArrayList<Long>(), desafioMeuTimeApplication.buscarTimes());
    }

    @Test
    public void deveBuscarJogadorComMaiorSalarioDoTime(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
        desafioMeuTimeApplication.incluirJogador(2l, 1l, "Jogador", LocalDate.now().minus(25, ChronoUnit.YEARS), 1, new BigDecimal(10000));
        desafioMeuTimeApplication.incluirJogador(3l, 1l, "Jogador2", LocalDate.now().minus(20, ChronoUnit.YEARS), 2, new BigDecimal(20000));
        desafioMeuTimeApplication.incluirJogador(4l, 1l, "Jogador3", LocalDate.now().minus(20, ChronoUnit.YEARS), 2, new BigDecimal(30000));
        assertEquals(4L, desafioMeuTimeApplication.buscarJogadorMaiorSalario(1L).longValue());
    }

    @Test(expected = TimeNaoEncontradoException.class)
    public void deveIdentificarTimeNaoEncontradoAoBuscarJogadorComMaiorSalarioDoTimeDeTimeNaoIncluso(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.buscarJogadorMaiorSalario(100L);
    }

    @Test
    public void deveBuscarJogadorComMaiorSalarioDoTimeEmpatado(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
        desafioMeuTimeApplication.incluirJogador(2l, 1l, "Jogador", LocalDate.now().minus(25, ChronoUnit.YEARS), 1, new BigDecimal(10000));
        desafioMeuTimeApplication.incluirJogador(3l, 1l, "Jogador2", LocalDate.now().minus(20, ChronoUnit.YEARS), 2, new BigDecimal(30000));
        desafioMeuTimeApplication.incluirJogador(4l, 1l, "Jogador3", LocalDate.now().minus(20, ChronoUnit.YEARS), 2, new BigDecimal(30000));
        assertEquals(3L, desafioMeuTimeApplication.buscarJogadorMaiorSalario(1L).longValue());
    }

    @Test
    public void deveRetornarSalarioDoJogador(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
        desafioMeuTimeApplication.incluirJogador(2l, 1l, "Jogador", LocalDate.now().minus(25, ChronoUnit.YEARS), 1, new BigDecimal(10000));
        desafioMeuTimeApplication.incluirJogador(3l, 1l, "Jogador2", LocalDate.now().minus(20, ChronoUnit.YEARS), 2, new BigDecimal(20000));
        desafioMeuTimeApplication.incluirJogador(4l, 1l, "Jogador3", LocalDate.now().minus(20, ChronoUnit.YEARS), 2, new BigDecimal(30000));
        assertEquals(new BigDecimal(10000), desafioMeuTimeApplication.buscarSalarioDoJogador(2L));
    }

    @Test(expected = JogadorNaoEncontradoException.class)
    public void deveIdentificarJogadorNaoEncontradoAoBuscarSalarioDoJogadorNaoIncluso(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.buscarSalarioDoJogador(200L);
    }

    @Test
    public void deveRetonarTopJogadores(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
        desafioMeuTimeApplication.incluirJogador(2l, 1l, "Jogador", LocalDate.now().minus(25, ChronoUnit.YEARS), 1, new BigDecimal(10000));
        desafioMeuTimeApplication.incluirJogador(3l, 1l, "Jogador2", LocalDate.now().minus(20, ChronoUnit.YEARS), 2, new BigDecimal(20000));
        desafioMeuTimeApplication.incluirJogador(4l, 1l, "Jogador3", LocalDate.now().minus(20, ChronoUnit.YEARS), 3, new BigDecimal(30000));
        List<Long> jogadores = desafioMeuTimeApplication.buscarTopJogadores(2);
        assertEquals(2, jogadores.size());
        assertEquals(4L, jogadores.get(0).longValue());
        assertEquals(3L, jogadores.get(1).longValue());
    }

    @Test
    public void deveRetonarTopJogadoresComNivelDeHabilidadeEmapatados(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        desafioMeuTimeApplication.incluirTime(1l, "Teste1", LocalDate.now(), "branco", "branco");
        desafioMeuTimeApplication.incluirJogador(2l, 1l, "Jogador", LocalDate.now().minus(25, ChronoUnit.YEARS), 1, new BigDecimal(10000));
        desafioMeuTimeApplication.incluirJogador(4l, 1l, "Jogador3", LocalDate.now().minus(20, ChronoUnit.YEARS), 3, new BigDecimal(30000));
        desafioMeuTimeApplication.incluirJogador(3l, 1l, "Jogador2", LocalDate.now().minus(20, ChronoUnit.YEARS), 3, new BigDecimal(20000));
        List<Long> jogadores = desafioMeuTimeApplication.buscarTopJogadores(2);
        assertEquals(2, jogadores.size());
        assertEquals(3L, jogadores.get(0).longValue());
        assertEquals(4L, jogadores.get(1).longValue());
    }

    @Test
    public void deveRetonarListaVaziaQuandoBuscarTopJogadoresSemJogadoresIncluidos(){
        final DesafioMeuTimeApplication desafioMeuTimeApplication = new DesafioMeuTimeApplication();
        List<Long> jogadores = desafioMeuTimeApplication.buscarTopJogadores(2);
        assertEquals(0, jogadores.size());
        assertEquals(new ArrayList<Long>(), jogadores);
    }
}
