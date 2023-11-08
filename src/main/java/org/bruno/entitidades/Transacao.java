package org.bruno.entitidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class TransacaoBancaria {
    private final LocalDate data;
    private final double valor;
    private final String categoria;

    public TransacaoBancaria(LocalDate data, double valor, String categoria) {
        this.data = data;
        this.valor = valor;
        this.categoria = categoria;
    }

    public LocalDate getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }

    public String getcategoria() {
        return categoria;
    }

    @Override
    public String toString(){
        return "| Transação de R$%.2f realizada em %s. Descrição: %S;\n".formatted(valor,
                data.format(DateTimeFormatter.ofPattern("dd/MM/yyy")), categoria);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if ((o == null) || this.getClass() != o.getClass()) return false;
        TransacaoBancaria transacaoConvertida = (TransacaoBancaria) o;
        return Double.compare(transacaoConvertida.valor, valor) == 0
                && data.equals(transacaoConvertida.data)
                && categoria.equals(transacaoConvertida.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, valor, categoria);
    }
}
