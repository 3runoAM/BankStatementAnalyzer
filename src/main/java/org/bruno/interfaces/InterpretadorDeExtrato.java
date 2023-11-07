package org.bruno.interfaces;


import org.bruno.entitidades.TransacaoBancaria;

import java.util.List;

public interface InterpretadorDeExtrato {
    TransacaoBancaria interpretarTransacao(final String transacao);
    List<TransacaoBancaria> interpretarExtrato(final List<String> extrato);
}
