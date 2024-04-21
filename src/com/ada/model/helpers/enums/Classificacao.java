package com.ada.model.helpers.enums;

public enum Classificacao {
    PF(0, "CPF", 11),
    PJ(1, "CNPJ", 14);

    private final int id;
    private final String tipoDocumento;
    private final int tamanhoDocumento;

    Classificacao(int id, String tipoDocumento, int tamanhoDocumento) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.tamanhoDocumento = tamanhoDocumento;
    }

    public int getId() {
        return id;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public int getTamanhoDocumento() {
        return tamanhoDocumento;
    }

    public boolean validaDocumento(Classificacao tipo, String documento){
        return tipo.tamanhoDocumento == documento.length();
    }
}
