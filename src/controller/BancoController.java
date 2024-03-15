package controller;

import model.ContaCorrente;
import model.ContaPoupanca;
import view.Menu;
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
                case 2 -> {
                    this.transacionarContas();
                    yield true;
                }
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
                    fecharMenu = true;
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

            switch (opcao){
                case 1 -> DepositarNaConta();
                case 2 -> SacarNaConta();
                case 3 -> ConsultarSaldoConta();
            }

            if (opcao == 4 || quebrar == 3){
                if (quebrar == 3) {
                    System.out.println("Você parece indeciso, volte quando estiver preparado!");
                    fecharMenu = true;
                }
                this.inicio();
            }
        }
    }

    private void ConsultarSaldoConta() {
        int tipoConta;
        int numeroConta;
        tipoConta = obterTipoConta();
        numeroConta = obterNumeroConta();
        if (tipoConta == 1){
            ContaPoupanca conta = EncontraContaPoupanca(numeroConta);
            if (conta.getNumeroConta() == numeroConta){
                conta.consultarSaldo();
            } else {
                System.out.println("Conta não localizada, reveja as informações.");
            }
        }

        if (tipoConta == 2){
            ContaCorrente conta = EncontraContaCorrente(numeroConta);
            if (conta.getNumeroConta() == numeroConta){
                conta.consultarSaldo();
            } else System.out.println("Conta não localizada, reveja as informações.");
        }
        aguardarRetorno();
        transacionarContas();
    }

    private void SacarNaConta() {
        int tipoConta;
        int numeroConta;
        double valorDeposito;
        tipoConta = obterTipoConta();
        numeroConta = obterNumeroConta();
        valorDeposito = obterValorTransacao("sacado");
        if (tipoConta == 1){
            ContaPoupanca conta = EncontraContaPoupanca(numeroConta);
            if (conta.getNumeroConta() == numeroConta){
                try {
                    conta.sacar(valorDeposito);
                } catch (Exception e){
                    System.out.print("Não foi possível efetuar o saque. " + e.getLocalizedMessage());
                }
            } else {
                System.out.println("Conta não localizada, reveja as informações.");
            }
        }

        if (tipoConta == 2){
            ContaCorrente conta = EncontraContaCorrente(numeroConta);
            if (conta.getNumeroConta() == numeroConta){
                try {
                    conta.sacar(valorDeposito);
                } catch (Exception e){
                    System.out.print("Não foi possível efetuar o saque. " + e.getLocalizedMessage());
                }
            } else System.out.println("Conta não localizada, reveja as informações.");
        }
        aguardarRetorno();
        transacionarContas();
    }

    private void DepositarNaConta() {
        int tipoConta;
        int numeroConta;
        double valorDeposito;
        tipoConta = obterTipoConta();
        numeroConta = obterNumeroConta();
        valorDeposito = obterValorTransacao("depositado");
        if (tipoConta == 1){
            ContaPoupanca conta = EncontraContaPoupanca(numeroConta);
            if (conta.getNumeroConta() == numeroConta){
                conta.depositar(valorDeposito);
            } else {
                System.out.println("Conta não localizada, reveja as informações.");
            }
        }

        if (tipoConta == 2){
            ContaCorrente conta = EncontraContaCorrente(numeroConta);
            if (conta.getNumeroConta() == numeroConta){
                conta.depositar(valorDeposito);
            } else System.out.println("Conta não localizada, reveja as informações.");
        }
        aguardarRetorno();
        transacionarContas();
    }

    private void aguardarRetorno() {
        ENTRADA.nextLine();
        System.out.print("Pressione enter para voltar ao menu: ");
        ENTRADA.nextLine();
    }

    private void aguardarRetornoSemLimpezaAnterior() {
        System.out.print("Pressione enter para voltar ao menu: ");
        ENTRADA.nextLine();
    }

    private double obterValorTransacao(String transacao) {
        double valor;
        System.out.printf("Informe o valor a ser %s: ", transacao);
        valor = ENTRADA.nextDouble();
        return valor;
    }

    private int obterNumeroConta() {
        int numeroConta;
        System.out.print("Informe o número da conta: ");
        numeroConta = ENTRADA.nextInt();
        return numeroConta;
    }

    private int obterTipoConta() {
        int tipoConta;
        System.out.print("Informe o tipo de conta (1 poupança, 2 corrente): ");
        tipoConta = ENTRADA.nextInt();
        return tipoConta;
    }

    private ContaPoupanca EncontraContaPoupanca(int numeroConta) {
        ContaPoupanca contaPoupanca = new ContaPoupanca(99, "Não localizado");
        for (ContaPoupanca poupanca : this.contasPoupanca) {
            if (poupanca != null &&
                    poupanca.getNumeroConta() == numeroConta) {
                contaPoupanca = poupanca;
            }
        }
        return contaPoupanca;
    }

    private ContaCorrente EncontraContaCorrente(int numeroConta) {
        ContaCorrente contaCorrente = new ContaCorrente(99, "Não localizado");
        for (ContaCorrente corrente : this.contasCorrente) {
            if (corrente != null &&
                    corrente.getNumeroConta() == numeroConta) {
                contaCorrente = corrente;
            }
        }
        return contaCorrente;
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
        int quantidadeContas;
        quantidadeContas = obterQuantidadeDeContasPoupanca();
        if (quantidadeContas <10){
            numeroConta = quantidadeContas + 1;
        } else {
            System.out.println("Problema técnicos para abertura de poupança, tente mais tarde.");
        }
        if (numeroConta >= 1 && numeroConta <= this.contasPoupanca.length){
            ContaPoupanca conta = new ContaPoupanca(numeroConta, nomeCliente);
            this.contasPoupanca[numeroConta-1] = conta;
            System.out.printf(
                    "Conta número %d de %S aberta com sucesso!\n", conta.getNumeroConta(),
                    conta.getNomeCliente());
        }
        aguardarRetornoSemLimpezaAnterior();
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

        int quantidadeContas;
        quantidadeContas = obterQuantidadeDeContasCorrente();
        if (quantidadeContas <10){
            numeroConta = quantidadeContas + 1;
        } else {
            System.out.println("Problema técnicos para abertura de conta corrente, tente mais tarde.");
        }
        if (numeroConta >= 1 && numeroConta < this.contasCorrente.length){
            ContaCorrente conta = new ContaCorrente(numeroConta, nomeCliente);
            this.contasCorrente[numeroConta-1] = conta;
            System.out.printf(
                    "Conta número %d de %s aberta com sucesso!\n", conta.getNumeroConta(),
                    conta.getNomeCliente());
        }
        aguardarRetornoSemLimpezaAnterior();
    }
}
