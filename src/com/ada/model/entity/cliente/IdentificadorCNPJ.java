package com.ada.model.entity.cliente;

import com.ada.model.entity.interfaces.conta.Identificador;

public class IdentificadorCNPJ implements Identificador<String> {
    private final String idCNPJ;

    public IdentificadorCNPJ(final String idCNPJ) {
        this.idCNPJ = idCNPJ;
        validar();
    }

    @Override
    public String getValor() {
        return idCNPJ;
    }

    @Override
    public void validar() {
        if (idCNPJ == null || idCNPJ.length() != 14){
            throw new IllegalArgumentException("O CNPJ deve possuir 14 digitos, verifique!");
        }
    }
}
