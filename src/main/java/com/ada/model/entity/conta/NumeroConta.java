package com.ada.model.entity.conta;

import com.ada.model.entity.interfaces.conta.Identificador;

import java.io.Serializable;

public class NumeroConta implements Identificador<String>, Serializable {
    private static int numeroIncremental = 0;
    private final String numeroDaConta;

    public NumeroConta() {
        numeroIncremental++;
        this.numeroDaConta = String.format("%06d", numeroIncremental);
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

    @Override
    public String toString() {
        return "NumeroConta{" +
                "numeroDaConta='" + numeroDaConta + '\'' +
                '}';
    }
}
