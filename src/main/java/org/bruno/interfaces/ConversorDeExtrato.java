package org.bruno.interfaces;


import org.bruno.entitidades.Transacao;

import java.util.List;

public interface InterpretadorDeExtrato {
    Transacao converterEmTransacao(final String transacao);
    List<Transacao> listarTransacoes(final List<String> extrato);
}
