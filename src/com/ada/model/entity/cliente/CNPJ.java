package com.ada.model.entity.cliente;

import com.ada.model.entity.interfaces.cliente.Identificador;

public class CNPJ implements Identificador<String> {
    private final String CNPJ;

    public CNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
        validar();
    }

    @Override
    public String getValor() {
        return CNPJ;
    }

    @Override
    public void validar() {
        if (CNPJ.length() < 14){
            throw new IllegalArgumentException("O CNPJ deve possuir 14 digitos, verifique!");
        }
    }
}
