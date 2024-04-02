package model;

import helpers.enums.TipoClienteEnum;

public class ContaCorrente extends Conta{

    protected final double TAXADESAQUEPF = 0.02;
    protected final double TAXADESAQUEPJ = 0.05;

    public ContaCorrente(int id, Cliente cliente) {
        super(id, cliente, false);
    }

    public void sacar(double valor) throws Exception {
        double taxa = 0.0;
        if (cliente.getTipo() == TipoClienteEnum.PF) taxa = TAXADESAQUEPF;
        if (cliente.getTipo() == TipoClienteEnum.PJ) taxa = TAXADESAQUEPJ;

        super.sacar(valor);
        this.cobrarTarifa(valor, taxa);
    }
}
