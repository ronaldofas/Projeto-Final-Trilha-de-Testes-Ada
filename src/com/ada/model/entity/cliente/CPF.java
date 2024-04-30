package com.ada.model.entity.cliente;

import com.ada.model.entity.interfaces.conta.Identificador;

public class CPF implements Identificador<String> {
    private final String CPF;

    public CPF(String CPF) {
        this.CPF = CPF;
        this.validar();
    }

    @Override
    public String getValor() {
        return CPF;
    }

    @Override
    public void validar() {
        if (CPF == null || CPF.isBlank()){
            throw new IllegalArgumentException("Preencha corretamente o CPF");
        }
        if (CPF.length() != 11){
            throw new IllegalArgumentException("CPF deve possuir 11 digitos, verifique!");
        }
    }
}
