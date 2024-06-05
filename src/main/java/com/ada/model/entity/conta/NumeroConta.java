package com.ada.model.entity.conta;

import com.ada.model.entity.interfaces.conta.Identificador;

public class NumeroConta implements Identificador<String> {
    private final String numeroDaConta;

    public NumeroConta(final String numeroConta) {
        this.numeroDaConta = numeroConta;
        validar();
    }

    @Override
    public void validar() {
        if (numeroDaConta == null || numeroDaConta.length() != 6){
            throw new IllegalArgumentException(
                    "Número da conta deve possuir 6 digitos, incluíndo zeros a esquerda");
        }
    }

    @Override
    public String getValor() {
        return numeroDaConta;
    }
}
