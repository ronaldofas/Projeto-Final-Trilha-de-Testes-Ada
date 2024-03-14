package controller;

import model.ContaCorrente;
import model.ContaPoupanca;
import view.Menu;

import java.util.Arrays;
import java.util.Scanner;

public class BancoController {

    private final ContaCorrente[] contasCorrente = new ContaCorrente[10];
    private final ContaPoupanca[] contasPoupanca = new ContaPoupanca[10];
    private final Menu menu = new Menu();

    private final Scanner ENTRADA = new Scanner(System.in);

    public void inicio(){
        int opcao;
        int quebrar = 0;
        boolean fecharMenu = false;
        this.menu.ExibirMenuInicial();
        while(!fecharMenu){
            opcao = ENTRADA.nextInt();
            if ((opcao > 3 || opcao < 1) && quebrar < 3){
                System.out.print("Opção inválida! Escolha uma opção: ");
                quebrar++;
            }

            fecharMenu = switch (opcao) {
                case 1 -> {
                    this.aberturaDeContas();
                    yield true;
                }
                case 2 -> true;
                default -> false;
            };

            if (opcao == 3 || quebrar == 3){
                if (quebrar == 3) {
                    System.out.println("Você parece indeciso, volte quando estiver preparado!");
                }
                System.out.println("Obrigado por utilizar a Caixa Econômica Federal.");
                System.exit(0);
            }
        }
    }

    public void aberturaDeContas(){
        int opcao;
        int quebrar = 0;
        boolean fecharMenu = false;
        this.menu.ExibirTelaDeAberturaDeConta();
        while (!fecharMenu){
            opcao = ENTRADA.nextInt();
            if ((opcao > 3 || opcao < 1) && quebrar < 3){
                System.out.print("Opção inválida! Escolha uma opção: ");
                quebrar++;
            }

            switch (opcao){
                case 1 -> {
                    String nomeCliente = ObterNomeDoCliente();
                    criarContaPoupanca(nomeCliente);
                    this.aberturaDeContas();
                }
                case 2 -> {
                    String nomeCliente = ObterNomeDoCliente();
                    criarContaCorrente(nomeCliente);
                    this.aberturaDeContas();
                }
            }

            if (opcao == 3 || quebrar == 3){
                if (quebrar == 3) {
                    System.out.println("Você parece indeciso, volte quando estiver preparado!");
                }
                this.inicio();
            }
        }
    }

    public void transacionarContas(){
        int opcao;
        int quebrar = 0;
        boolean fecharMenu = false;
        this.menu.ExibirTelaDeTransacoesDeConta();
        while (!fecharMenu){
            opcao = ENTRADA.nextInt();
            if ((opcao > 4 || opcao < 1) && quebrar < 3){
                System.out.print("Opção inválida! Escolha uma opção: ");
                quebrar++;
            }

        }
    }

    private String ObterNomeDoCliente() {
        String nomeCliente;
        System.out.println("Digite o seu nome: ");
        nomeCliente = ENTRADA.next();
        ENTRADA.nextLine();
        return nomeCliente;
    }

    private void criarContaPoupanca(String nomeCliente) {
        int numeroConta = 0;
        int quantidadeContas = 0;
        quantidadeContas = obterQuantidadeDeContasPoupanca();
        if (quantidadeContas <10){
            numeroConta = quantidadeContas + 1;
        } else {
            System.out.println("Problema técnicos para abertura de poupança, tente mais tarde.");
        }
        if (numeroConta >= 1 && numeroConta <= 10){
            ContaPoupanca conta = new ContaPoupanca(numeroConta, nomeCliente);
            this.contasPoupanca[numeroConta-1] = conta;
            System.out.printf(
                    "Conta número %d de %S aberta com sucesso!", conta.getNumeroConta(),
                    conta.getNomeCliente());
        }
    }

    private int obterQuantidadeDeContasPoupanca() {
        int quantidadeContas = 0;
        for (int i=0; i < this.contasPoupanca.length; i++){
            if(this.contasPoupanca[i] == null){
                quantidadeContas = i;
                break;
            }
        }
        return quantidadeContas;
    }

    private int obterQuantidadeDeContasCorrente() {
        int quantidadeContas = 0;
        for (int i=0; i < this.contasCorrente.length; i++){
            if(this.contasCorrente[i] == null){
                quantidadeContas = i;
                break;
            }
        }
        return quantidadeContas;
    }

    private void criarContaCorrente(String nomeCliente) {
        int numeroConta = 0;

        int quantidadeContas = 0;
        quantidadeContas = obterQuantidadeDeContasCorrente();
        if (quantidadeContas <10){
            numeroConta = quantidadeContas + 1;
        } else {
            System.out.println("Problema técnicos para abertura de conta corrente, tente mais tarde.");
        }
        if (numeroConta >= 1 && numeroConta <= 10){
            ContaCorrente conta = new ContaCorrente(numeroConta, nomeCliente);
            this.contasCorrente[numeroConta-1] = conta;
            System.out.printf(
                    "Conta número %d de %s aberta com sucesso!", conta.getNumeroConta(),
                    conta.getNomeCliente());
        }
    }
}
