package org.bruno.entitidades;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Month;
import java.util.List;

public class AnalistaExtrato {
    public void analisar(String caminhoArquivo, ConversorExtratoCSVParaTransacoes conversor) throws IOException {
        final List<TransacaoBancaria> transacoes = conversor.listarTransacoes(Files.readAllLines(Path.of(caminhoArquivo)));
        final ProcessadorTransacoes processador = new ProcessadorTransacoes(transacoes);
        sumario(processador);
    }

    private static void sumario(final ProcessadorTransacoes ProcessadorTransacoes) {
        System.out.println("O valor total do extrato é: R$" + ProcessadorTransacoes.calcularValorTotal());
        System.out.println("Extrato do mês de Janeiro: R$" + ProcessadorTransacoes.calcularTotalDoMes(Month.JANUARY));
        System.out.println("Extrato do mês de Fevereiro: R$" + ProcessadorTransacoes.calcularTotalDoMes(Month.FEBRUARY));
        System.out.println("Quantidade correspondente ao salário: R$" + ProcessadorTransacoes.calcularTotalDaCategoria("Salary"));
    }
}
