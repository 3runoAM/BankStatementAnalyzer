import org.bruno.entitidades.ConverteExtratoCSVParaTransacoes;
import org.bruno.entitidades.TransacaoBancaria;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ConverteExtratoCSVParaTransacoesTest {
    private static ConverteExtratoCSVParaTransacoes interpretadorTeste;

    @BeforeAll
    public static void setUp(){
        interpretadorTeste = new ConverteExtratoCSVParaTransacoes();
    }

    @AfterAll
    public static void tearDown(){
        interpretadorTeste = null;
    }

    @Test
    public void interpretarTransacoes() {
        // Transação 1
        String transacaoCrua1 = "06-02-2017,20,Restaurante";
        TransacaoBancaria transacaoInterpretada1 = interpretadorTeste.interpretarTransacao(transacaoCrua1);
        Assertions.assertEquals(20, transacaoInterpretada1.getValor());
        Assertions.assertEquals(LocalDate.of(2017,2, 6), transacaoInterpretada1.getData());
        Assertions.assertEquals("Restaurante", transacaoInterpretada1.getcategoria());

        // Transação 2
        String transacaoCrua2 = "07-08-2019,-150,Compras Online";
        TransacaoBancaria transacaoInterpretada2 = interpretadorTeste.interpretarTransacao(transacaoCrua2);

        Assertions.assertEquals(-150, transacaoInterpretada2.getValor());
        Assertions.assertEquals(LocalDate.of(2019, 8, 7), transacaoInterpretada2.getData());
        Assertions.assertEquals("Compras Online", transacaoInterpretada2.getcategoria());
    }

    @Test
    public void getTransacoesDoMes(){
        List<TransacaoBancaria> transacoes = new ArrayList<>(){{
            //  Janeiro
            new TransacaoBancaria(LocalDate.of(2017, 1, 1), 6000, "Salary");
            new TransacaoBancaria(LocalDate.of(2017, 1, 2), 2000, "Royalties");
            //Fevereiro
            new TransacaoBancaria(LocalDate.of(2017, 2, 2), -4000, "Rent");
            new TransacaoBancaria(LocalDate.of(2017, 2, 3), 3000, "Tesco");
            new TransacaoBancaria(LocalDate.of(2017, 2, 5), -30, "Cinema");
        }};

        Month mes1 = Month.JANUARY;
        List<TransacaoBancaria> transacoesMes1 = interpretadorTeste.getTransacoesDoMes(transacoes, mes1);
        List<TransacaoBancaria> transacoesEsperadasMes1 = new ArrayList<>(){{
            new TransacaoBancaria(LocalDate.of(2017, 1, 1), 6000, "Salary");
            new TransacaoBancaria(LocalDate.of(2017, 1, 2), 2000, "Royalties");
        }};
       for(int i = 0; i<(transacoesMes1.size()-1); i++){
           Assertions.assertEquals(transacoesEsperadasMes1.get(i).getValor(), transacoesMes1.get(i).getValor());
           Assertions.assertEquals(transacoesEsperadasMes1.get(i).getData(), transacoesMes1.get(i).getData());
           Assertions.assertEquals(transacoesEsperadasMes1.get(i).getcategoria(), transacoesMes1.get(i).getcategoria());
       }


        Month mes2 = Month.FEBRUARY;
        List<TransacaoBancaria> transacoesMes2 = interpretadorTeste.getTransacoesDoMes(transacoes, mes2);
        List<TransacaoBancaria> transacoesEsperadasMes2 = new ArrayList<>(){{
            new TransacaoBancaria(LocalDate.of(2017, 2, 2), -4000, "Rent");
            new TransacaoBancaria(LocalDate.of(2017, 2, 3), 3000, "Tesco");
            new TransacaoBancaria(LocalDate.of(2017, 2, 5), -30, "Cinema");
        }};
        for(int i = 0; i<(transacoesMes1.size()-1); i++){
            Assertions.assertEquals(transacoesEsperadasMes2.get(i).getValor(), transacoesMes2.get(i).getValor());
            Assertions.assertEquals(transacoesEsperadasMes1.get(i).getData(), transacoesMes2.get(i).getData());
            Assertions.assertEquals(transacoesEsperadasMes2.get(i).getcategoria(), transacoesMes2.get(i).getcategoria());
        }
    }
}