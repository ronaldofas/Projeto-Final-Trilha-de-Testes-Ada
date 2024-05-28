package com.ada.model.entity.interfaces.conta;

import com.ada.model.entity.cliente.Cliente;
import com.ada.model.entity.conta.Transacao;

import java.time.LocalDateTime;
import java.util.List;

public interface Conta {
    Cliente getCliente();
    String getNumero();
    LocalDateTime getDataAtualizacao();
    boolean isStatus();
    void depositar(double valor);
    void sacar(double valor);
    void transferir(double valor, Conta contaDestino);
    double consultarSaldo();
    void ativarDesativar();
    List<Transacao> getTransacoes();
    void criarTransacao(Transacao transacao);
}
