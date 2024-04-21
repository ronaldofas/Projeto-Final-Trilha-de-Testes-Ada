package com.ada.model.entity.interfaces.conta;

import com.ada.model.entity.cliente.Cliente;

public interface Conta {
    Cliente getCliente();
    String getNumero();
    void depositar(double valor);
    void sacar(double valor);
    void transferir(double valor, Conta conta);
    double consultarSaldo();
}
