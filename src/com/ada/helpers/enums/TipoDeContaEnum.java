package com.ada.helpers.enums;

public enum TipoDeContaEnum {
    CONTA_POUPANCA("CP"),
    CONTA_CORRENTE("CC");

    private final String sigla;
    TipoDeContaEnum(String sigla){
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }
}
