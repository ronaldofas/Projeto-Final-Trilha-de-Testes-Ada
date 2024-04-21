package com.ada.model.entity;


import com.ada.model.entity.cliente.Cliente;
import com.ada.model.helpers.enums.Classificacao;
import com.ada.model.helpers.enums.TipoDeContaEnum;

public class ContaPoupanca extends Conta{

    private final double TAXARENDIMENTO = 0.005;

    public ContaPoupanca(String id, Cliente cliente, TipoDeContaEnum tipoConta) {
        super(id, cliente, tipoConta);
        if (cliente.getClassificacao() == Classificacao.PESSOA_JURIDICA)
            throw new IllegalArgumentException("Somente pessoas físicas podem ter conta poupança!");
    }

    public void depositar(double valor){
        super.depositar(valor);
        super.adicionarRendimento(valor, TAXARENDIMENTO);
    }
}
