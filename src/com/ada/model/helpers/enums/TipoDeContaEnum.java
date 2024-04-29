package com.ada.model.helpers.enums;

public enum TipoDeContaEnum {
    CONTA_POUPANCA("CP"),
    CONTA_CORRENTE("CC"),
    CONTA_INVESTIMENTO("CI");

    private final String sigla;
    TipoDeContaEnum(String sigla){
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }
}
