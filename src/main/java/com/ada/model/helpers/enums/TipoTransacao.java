package com.ada.model.helpers.enums;

public enum TipoTransacao {
    TRANSFERENCIA("Transferência efetuada"),
    SAQUE("Saque efetuado"),
    INVESTIMENTO("Investimento efetuado"),
    DEPOSITO("Depósito efetuado"),
    TARIFA("Tarifa da conta"),
    RENDIMENTO("Rendimento da conta");


    private final String descricao;

    TipoTransacao(final String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
