package view;

import java.io.IOException;

public class Menu {
    public void ExibirMenuInicial() {
        this.LimparTela();
        this.ImprimirCabecalho();
        this.ImprimirCorpoDoMenu();
    }

    public void ExibirTelaDeAberturaDeConta(){
        this.LimparTela();
        this.ImprimirCabecalho();
        this.ImprimirCorpoAberturaDeConta();
    }

    private void LimparTela() {
        if (System.getProperty("os.name").contains("Windows")) {
            try {
                //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                String[] command = new String[]{"cls"};
                Runtime.getRuntime().exec(command);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                String[] command = new String[]{"clear"};
                Runtime.getRuntime().exec(command);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void ImprimirCabecalho() {
        System.out.println(this.CadeiaDeCaracteres(80, '='));
        System.out.println(
                this.CadeiaDeCaracteres(29, ' ') + "Caixa Econômica Federal"
                + this.CadeiaDeCaracteres(28, ' '));
        System.out.println(this.CadeiaDeCaracteres(80, '='));
    }

    private String CadeiaDeCaracteres(int qtde, char caracter){
        String resultado = "";
        for (int i = 1; i<=qtde; i++){
            resultado += caracter;
        }
        return resultado;
    }

    private void ImprimirCorpoDoMenu(){
        System.out.println();
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "Operações de Conta: ");
        System.out.println();
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "Selecione uma opção: ");
        System.out.println();
        System.out.println();
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "1 - Abertura de conta");
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "2 - Transações de conta");
        System.out.println();
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "3 - Sair da aplicação");
        System.out.println();
        System.out.println(this.CadeiaDeCaracteres(80, '='));
        System.out.print(this.CadeiaDeCaracteres(30, ' ') + "Digite a opção: ");
    }

    private void ImprimirCorpoAberturaDeConta(){
        System.out.println();
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "Operações de Conta");
        System.out.println();
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "Selecione uma opção: ");
        System.out.println();
        System.out.println();
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "1 - Abrir conta poupança");
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "2 - Abrir conta corrente");
        System.out.println();
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "3 - Voltar para o menu principal");
        System.out.println();
        System.out.println(this.CadeiaDeCaracteres(80, '='));
        System.out.print(this.CadeiaDeCaracteres(30, ' ') + "Digite a opção: ");
    }
}
