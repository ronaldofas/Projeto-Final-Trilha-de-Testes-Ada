package com.ada.model.entity.conta;

import com.ada.model.entity.interfaces.conta.Identificador;

public class NumeroConta implements Identificador<String> {
    private final String numeroConta;

    public NumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
        validar();
    }

    @Override
    public void validar() {
        if (numeroConta.length() < 4){
            throw new IllegalArgumentException(
                    "Número da conta deve possuir 4 digitos, incluíndo zeros a esquerda");
        }
    }

    @Override
    public String getValor() {
        return numeroConta;
    }
}
