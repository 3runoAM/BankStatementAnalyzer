package org.bruno.entitidades;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Month;
import java.util.List;

public class AnalistaDeExtratoBancario {
    private static final String EXTRATO_CSV = "recursos/extrato.txt";

    public void analizar(final InterpretadorExtratoEmCSV interpretador) throws IOException {
        final List<TransacaoBancaria> transacoes = interpretador.interpretarExtrato(Files.readAllLines(Path.of(EXTRATO_CSV)));
        final ProcessaTransacoes processador = new ProcessaTransacoes(transacoes);
        sumario(processador);
    }

    private static void sumario(final ProcessaTransacoes ProcessaTransacoes) {
        System.out.println("The total for all transactions is " + ProcessaTransacoes.calcularValorTotal());
        System.out.println("The total for transactions in January is " + ProcessaTransacoes.calcularTotalDoMes(Month.JANUARY));
        System.out.println("The total for transactions in February is " + ProcessaTransacoes.calcularTotalDoMes(Month.FEBRUARY));
        System.out.println("The total salary received is " + ProcessaTransacoes.calcularTotalDaCategoria("Salary"));
    }
}
