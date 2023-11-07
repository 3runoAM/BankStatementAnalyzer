package org.bruno.entitidades;

import java.time.Month;
import java.util.List;

public class ProcessaTransacoes {
    private final List<TransacaoBancaria> transacoes;

    public ProcessaTransacoes(List<TransacaoBancaria> transacoes){
        this.transacoes = transacoes;
    }

    public double calcularValorTotal(){
        double valorTotal = 0;
        for(TransacaoBancaria transacao : transacoes){
            valorTotal+=transacao.getValor();
        }
        return valorTotal;
    }

    public double calcularTotalDoMes(final Month mes){
        double valorTotal = 0;
        for(TransacaoBancaria transacao : transacoes){
            if(transacao.getData().getMonth() == mes){
                valorTotal+=transacao.getValor();
            }
        }
        return valorTotal;
    }

    public double calcularTotalDaCategoria(final String categoria){
        double valorTotal = 0;
        for(TransacaoBancaria transacao : transacoes){
            if(transacao.getcategoria().equals(categoria)){
                valorTotal+=transacao.getValor();
            }
        }
        return valorTotal;
    }
}
