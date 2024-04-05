package com.ada.helpers.enums;

public enum TipoClienteEnum {
    PESSOA_FISICA(0, "PF", "CPF", 11),
    PESSOA_JURIDICA(1, "PJ", "CNPJ", 14);

    private final int id;
    private final String sigla;
    private final String tipoDocumento;
    private final int tamanhoDocumento;

    TipoClienteEnum(int id, String sigla, String tipoDocumento, int tamanhoDocumento) {
        this.id = id;
        this.sigla = sigla;
        this.tipoDocumento = tipoDocumento;
        this.tamanhoDocumento = tamanhoDocumento;
    }

    public int getId() {
        return id;
    }

    public String getSigla() {
        return sigla;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public int getTamanhoDocumento() {
        return tamanhoDocumento;
    }

    public boolean validaDocumento(TipoClienteEnum tipo, String documento){
        return tipo.tamanhoDocumento == documento.length();
    }
}
