package com.ada.model.entity;


import com.ada.helpers.enums.TipoClienteEnum;
import com.ada.helpers.enums.TipoDeContaEnum;

public class ContaPoupanca extends Conta{

    private final double TAXARENDIMENTO = 0.005;

    public ContaPoupanca(int id, Cliente cliente, TipoDeContaEnum tipoConta) {
        super(id, cliente, tipoConta);
        if (cliente == null) throw new RuntimeException("Cliente inválido ou não localizado!");
        if (cliente.getTipo() == TipoClienteEnum.PESSOA_JURIDICA)
            throw new RuntimeException("Somente pessoas físicas podem ter conta poupança!");
    }

    public double depositar(double valor){
        super.depositar(valor);
        return valor + this.adicionarRendimento(valor, TAXARENDIMENTO);
    }
}
