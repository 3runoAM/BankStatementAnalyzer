package org.bruno.entitidades;



import org.bruno.interfaces.InterpretadorDeExtrato;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ConverteExtratoCSVParaTransacoes implements InterpretadorDeExtrato {
    private static final DateTimeFormatter PADRAO_DATA = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public TransacaoBancaria interpretarTransacao(final String transacao){
        final  String[] infosTransacao = (transacao.split(","));
        final LocalDate data = LocalDate.parse(infosTransacao[0], PADRAO_DATA);
        final double valor = Double.parseDouble(infosTransacao[1]);
        final String descricao = infosTransacao[2];

        return new TransacaoBancaria(data, valor, descricao);
    }

    @Override
    public List<TransacaoBancaria> interpretarExtrato(final List<String> extrato){
        final List<TransacaoBancaria> transacoesBancarias = new ArrayList<>();
        for(final String transacao : extrato){
            transacoesBancarias.add(interpretarTransacao(transacao));
        }
        return transacoesBancarias;
    }


    public static List<TransacaoBancaria> getTransacoesDoMes(final List<TransacaoBancaria> transacoes, final Month mes){
        final List<TransacaoBancaria> transacoesDoMes = new ArrayList<>();
        for(TransacaoBancaria transacao : transacoes){
            if(transacao.getData().getMonth() == mes){
                transacoesDoMes.add(transacao);
            }
        }
        return transacoesDoMes;
    }
}
