package com.ada.model.entity.conta;

import com.ada.model.entity.interfaces.conta.Identificador;

public class NumeroConta implements Identificador<String> {
    private static int numeroIncremental = 0;
    private final String numeroConta;

    public NumeroConta(final String numeroConta) {
        this.numeroConta = numeroConta;
        validar();
    }

    public NumeroConta(){
        numeroIncremental++;
        this.numeroConta = String.format("%06d", numeroIncremental);
        validar();
    }

    @Override
    public void validar() {
        if (numeroConta == null || numeroConta.length() != 6){
            throw new IllegalArgumentException(
                    "Número da conta deve possuir 6 digitos, incluíndo zeros a esquerda");
        }
    }

    @Override
    public String getValor() {
        return numeroConta;
    }
}
