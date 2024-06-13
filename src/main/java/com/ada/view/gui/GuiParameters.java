package com.ada.view.gui;

public class GuiParameters {

    private GuiParameters() {}

    private static final int TELA_LARGURA = 600;
    private static final int TELA_ALTURA = 800;
    private static final int TABELA_LARGURA = 400;
    private static final int TABELA_ALTURA = 600;
    private static final int LINHA_UM_TELA = 0;
    private static final int LINHA_DOIS_TELA = 1;
    private static final int LINHA_TRES_TELA = 2;
    private static final int LINHA_QUATRO_TELA = 3;
    private static final int COLUNA_UM_TELA = 0;
    private static final int COLUNA_DOIS_TELA = 1;
    private static final int COLUNA_TRES_TELA = 2;
    private static final int COLUNA_QUATRO_TELA = 3;
    private static final int TAMANHO_TXT_NOME = 20;
    private static final int TAMANHO_TXT_CPF_CNPJ = 14;

    public static int getTelaLargura() {
        return TELA_LARGURA;
    }

    public static int getTelaAltura() {
        return TELA_ALTURA;
    }

    public static int getTabelaLargura() {
        return TABELA_LARGURA;
    }

    public static int getTabelaAltura() {
        return TABELA_ALTURA;
    }

    public static int getLinhaUmTela() {
        return LINHA_UM_TELA;
    }

    public static int getLinhaDoisTela() {
        return LINHA_DOIS_TELA;
    }

    public static int getLinhaTresTela() {
        return LINHA_TRES_TELA;
    }

    public static int getLinhaQuatroTela() {
        return LINHA_QUATRO_TELA;
    }

    public static int getColunaUmTela() {
        return COLUNA_UM_TELA;
    }

    public static int getColunaDoisTela() {
        return COLUNA_DOIS_TELA;
    }

    public static int getColunaTresTela() {
        return COLUNA_TRES_TELA;
    }

    public static int getColunaQuatroTela() {
        return COLUNA_QUATRO_TELA;
    }

    public static int getTamanhoTxtNome() {
        return TAMANHO_TXT_NOME;
    }

    public static int getTamanhoTxtCpfCnpj() {
        return TAMANHO_TXT_CPF_CNPJ;
    }
}
