package com.ada.model.entity;

import com.ada.helpers.enums.TipoClienteEnum;
import com.ada.helpers.enums.TipoDeContaEnum;

public class ContaCorrente extends Conta{

    protected final double TAXADESAQUEPF = 0.02;
    protected final double TAXADESAQUEPJ = 0.05;

    public ContaCorrente(int id, Cliente cliente, TipoDeContaEnum tipoConta) {
        super(id, cliente, tipoConta);
    }

    public double sacar(double valor) throws Exception {
        double taxa = 0.0;
        if (cliente.getTipo() == TipoClienteEnum.PESSOA_FISICA) taxa = TAXADESAQUEPF;
        if (cliente.getTipo() == TipoClienteEnum.PESSOA_JURIDICA) taxa = TAXADESAQUEPJ;

        double saque = super.sacar(valor);
        return saque + this.cobrarTarifa(valor, taxa);
    }

}
