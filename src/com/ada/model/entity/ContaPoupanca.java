package com.ada.model.entity;


import com.ada.helpers.enums.TipoClienteEnum;

public class ContaPoupanca extends Conta{

    private final double TAXARENDIMENTO = 0.005;

    public ContaPoupanca(int id, Cliente cliente) {
        super(id, cliente);
        if (cliente == null) throw new RuntimeException("Cliente inválido ou não localizado!");
        if (cliente.getTipo() == TipoClienteEnum.PESSOA_JURIDICA)
            throw new RuntimeException("Somente pessoas físicas podem ter conta poupança!");
    }

    public void depositar(double valor){
        super.depositar(valor);
        this.adicionarRendimento(valor, TAXARENDIMENTO);
    }
}
