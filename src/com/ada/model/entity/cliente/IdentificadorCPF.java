package com.ada.model.entity.cliente;

import com.ada.model.entity.interfaces.conta.Identificador;

public class IdentificadorCPF implements Identificador<String> {
    private final String cpf;

    public IdentificadorCPF(final String cpf) {
        this.cpf = cpf;
        this.validar();
    }

    @Override
    public String getValor() {
        return cpf;
    }

    @Override
    public void validar() {
        if (cpf == null || cpf.isBlank()){
            throw new IllegalArgumentException("Preencha corretamente o CPF");
        }
        if (cpf.length() != 11){
            throw new IllegalArgumentException("CPF deve possuir 11 digitos, verifique!");
        }
    }
}
