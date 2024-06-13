package com.ada.model.entity.cliente;

import com.ada.model.entity.interfaces.conta.Identificador;

import java.io.Serializable;

public class IdentificadorCNPJ implements Identificador<String>, Serializable {
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
