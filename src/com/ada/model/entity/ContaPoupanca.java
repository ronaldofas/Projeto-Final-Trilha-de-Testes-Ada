package com.ada.model.entity;


import com.ada.helpers.enums.TipoClienteEnum;
import com.ada.helpers.enums.TipoDeContaEnum;

public class ContaPoupanca extends Conta{

    private final double TAXARENDIMENTO = 0.005;

    public ContaPoupanca(String id, Cliente cliente, TipoDeContaEnum tipoConta) {
        super(id, cliente, tipoConta);
        if (cliente.getTipo() == TipoClienteEnum.PESSOA_JURIDICA)
            throw new IllegalArgumentException("Somente pessoas físicas podem ter conta poupança!");
    }

    public void depositar(double valor){
        super.depositar(valor);
        super.adicionarRendimento(valor, TAXARENDIMENTO);
    }
}
