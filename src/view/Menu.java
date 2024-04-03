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

    public void ExibirTelaDeTransacoesDeConta(){
        this.LimparTela();
        this.ImprimirCabecalho();
        this.ImprimirCorpoTransacoesDeConta();
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
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "1 - Cadastro de Clientes");
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "2 - Abertura de conta");
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "3 - Transações de conta");
        System.out.println();
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "4 - Sair da aplicação");
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

    private void ImprimirCorpoTransacoesDeConta(){
        System.out.println();
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "Transações de Conta");
        System.out.println();
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "Selecione uma opção: ");
        System.out.println();
        System.out.println();
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "1 - Depositar");
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "2 - Sacar");
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "3 - Consultar saldo");
        System.out.println();
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "4 - Voltar para o menu principal");
        System.out.println();
        System.out.println(this.CadeiaDeCaracteres(80, '='));
        System.out.print(this.CadeiaDeCaracteres(30, ' ') + "Digite a opção: ");
    }

    public void ImprimirCorpoDeCadastroDeCliente(){
        System.out.println();
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "Cadastro de Clientes");
        System.out.println();
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "Selecione uma opção: ");
        System.out.println();
        System.out.println();
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "1 - Cadastrar Cliente");
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "2 - Pesquisar Cliente por nome");
        System.out.println(this.CadeiaDeCaracteres(30, ' ') +
                "3 - Pesquisar Cliente por identificador (CPF/CNPJ)");
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "4 - Listar todos os clientes");
        System.out.println();
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "5 - Voltar para o menu principal");
        System.out.println();
        System.out.println(this.CadeiaDeCaracteres(80, '='));
        System.out.print(this.CadeiaDeCaracteres(30, ' ') + "Digite a opção: ");
    }
}
