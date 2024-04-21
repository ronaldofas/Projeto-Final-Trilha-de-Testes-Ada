package com.ada.model.entity.conta;

import com.ada.model.entity.Conta;
import com.ada.model.entity.cliente.Cliente;
import com.ada.model.helpers.enums.Classificacao;
import com.ada.model.helpers.enums.TipoDeContaEnum;

public class ContaCorrente extends Conta {

    protected final double TAXADESAQUEPF = 0.002;
    protected final double TAXADESAQUEPJ = 0.005;

    public ContaCorrente(String id, Cliente cliente, TipoDeContaEnum tipoConta) {
        super(id, cliente, tipoConta);
    }

    public void sacar(double valor) throws Exception {
        double taxa = 0.0;
        if (super.getCliente().getClassificacao().equals(Classificacao.PF)) {
            taxa = TAXADESAQUEPF;
        } else {
            taxa = TAXADESAQUEPJ;
        }

        super.cobrarTarifa(valor, taxa);
        super.sacar(valor);
    }

}
