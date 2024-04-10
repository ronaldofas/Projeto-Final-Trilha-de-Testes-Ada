package com.ada.controller;

import com.ada.helpers.enums.TipoClienteEnum;
import com.ada.helpers.enums.TipoDeContaEnum;
import com.ada.model.entity.Banco;
import com.ada.model.entity.Cliente;
import com.ada.model.entity.ContaCorrente;
import com.ada.model.entity.ContaPoupanca;
import com.ada.view.CLI.Menu;

import java.util.Scanner;

public class BancoController {

    private final Banco banco = new Banco();
    private final Menu menu = new Menu();

    private final Scanner ENTRADA = new Scanner(System.in);

    public void inicio(){
        int opcao;
        int tentativas = 0;
        boolean fecharMenu = false;
        this.menu.ExibirMenuInicial();
        while(!fecharMenu){
            opcao = ENTRADA.nextInt();
            clearBuffer(ENTRADA);
            tentativas = getTentativas(opcao, 4, tentativas);
            fecharMenu = validarOpcaoMenuInicio(opcao);

            terminarLoopOuSair(opcao, tentativas, 4, true);
        }
    }

    private static int getTentativas(int opcao, int x, int tentativas) {
        if ((opcao > x || opcao < 1)) {
            System.out.print("Opção inválida! Escolha uma opção: ");
            tentativas++;
        }
        return tentativas;
    }

    private boolean validarOpcaoMenuInicio(int opcao) {
        return switch (opcao) {
            case 1 -> {
                this.cadastroDeClientes();
                yield false;
            }
            case 2 -> {
                this.aberturaDeContas();
                yield false;
            }
            case 3 -> {
                this.transacionarContas();
                yield false;
            }
            default -> true;
        };
    }

    private static void terminarLoopOuSair(int opcao, int tentativas, int maiorOpcao, boolean sair) {
        if (opcao == maiorOpcao || tentativas == 3){
            if (tentativas == 3) {
                System.out.println("Você parece indeciso, volte quando estiver preparado!");
            }
            if (sair) {
                System.out.println("Obrigado por utilizar a Caixa Econômica Federal.");
                System.exit(0);
            }
        }
    }

    public void cadastroDeClientes(){
        int opcao;
        int tentativas = 0;
        boolean fecharMenu = false;
        this.menu.ImprimirCorpoDeCadastroDeCliente();
        while (!fecharMenu){
            opcao = ENTRADA.nextInt();
            clearBuffer(ENTRADA);
            tentativas = getTentativas(opcao, 5, tentativas);
            fecharMenu = avaliarOpcaoMenuCadastroDeClientes(opcao);
        }
        inicio();
    }

    private boolean avaliarOpcaoMenuCadastroDeClientes(int opcao) {
        return switch (opcao) {
            case 1 -> {
                criarCliente();
                cadastroDeClientes();
                yield false;
            }
            case 2 -> {
                try {
                    pesquisarClientePorNome();
                } catch (RuntimeException ex){
                    System.out.println(ex.getMessage());
                }
                aguardarRetorno();
                cadastroDeClientes();
                yield false;
            }
            case 3 -> {
                try {
                    pesquisarClientePorId();
                } catch (RuntimeException ex){
                    System.out.println(ex.getMessage());
                }
                aguardarRetorno();
                cadastroDeClientes();
                yield false;
            }
            case 4 -> {
                banco.imprimirClientes();
                aguardarRetorno();
                cadastroDeClientes();
                yield false;
            }
            default -> true;
        };
    }

    private Cliente pesquisarClientePorId() {
        String idAhPesquisar = obterIdCliente();
        Cliente clienteLocalizado = banco.pesquisarClientePorId(idAhPesquisar);
        if (clienteLocalizado == null)
            throw new RuntimeException("Nenhum cliente localizado com o nome informado!");
        return clienteLocalizado;
    }

    private Cliente pesquisarClientePorNome() {
        String nomeAhPesquisar = obterNomeDoCliente();
        Cliente clienteLocalizado = banco.pesquisarClientePorNome(nomeAhPesquisar);
        if (clienteLocalizado == null)
            throw new RuntimeException("Nenhum cliente localizado com o nome informado!");
        return clienteLocalizado;
    }

    private void criarCliente() {
        TipoClienteEnum classificacao = obterTipoDeCliente();
        String id = obterIdCliente();
        String nomeCliente = obterNomeDoCliente();
        Cliente cliente = new Cliente(id, classificacao, nomeCliente);
        banco.adicionarClienteNovo(cliente);
        System.out.println(cliente);
        aguardarRetorno();
    }

    private TipoClienteEnum obterTipoDeCliente() {
        boolean escolhaEfetuada = false;
        int opcao = 0;

        while (!escolhaEfetuada){
            System.out.println("Digite 1 - Pessoa Física (PF) ou 2 - Pessoa Jurídica (PJ): ");
            opcao = ENTRADA.nextInt();
            clearBuffer(ENTRADA);
            if (opcao > 0 && opcao <= 2) escolhaEfetuada = true;
        }
        
        return switch (opcao){
            case 1 -> TipoClienteEnum.PESSOA_FISICA;
            case 2 -> TipoClienteEnum.PESSOA_JURIDICA;
            default -> throw new IllegalStateException("Unexpected value: " + opcao);
        };
    }

    private TipoDeContaEnum obterTipoDeConta() {
        boolean escolhaEfetuada = false;
        int opcao = 0;

        while (!escolhaEfetuada){
            System.out.println("Digite 1 - Conta Poupança ou 2 - Conta Corrente: ");
            opcao = ENTRADA.nextInt();
            clearBuffer(ENTRADA);
            if (opcao > 0 && opcao <= 2) escolhaEfetuada = true;
        }

        return switch (opcao){
            case 1 -> TipoDeContaEnum.CONTA_POUPANCA;
            case 2 -> TipoDeContaEnum.CONTA_CORRENTE;
            default -> throw new IllegalStateException("Unexpected value: " + opcao);
        };
    }

    public void aberturaDeContas(){
        int opcao;
        int tentativas = 0;
        boolean fecharMenu = false;
        this.menu.ExibirTelaDeAberturaDeConta();
        while (!fecharMenu){
            opcao = ENTRADA.nextInt();
            clearBuffer(ENTRADA);
            tentativas = getTentativas(opcao, 3, tentativas);

            fecharMenu = validarOpcaoMenuAberturaDeContas(opcao);

            terminarLoopOuSair(opcao, tentativas, 3, false);
        }
        this.inicio();
    }

    private boolean validarOpcaoMenuAberturaDeContas(int opcao) {
        return switch (opcao) {
            case 1 -> {
                abrirContaPoupanca();
                aberturaDeContas();
                yield false;
            }
            case 2 -> {
                abrirContaCorrente();
                aberturaDeContas();
                yield false;
            }
            default -> true;
        };
    }

    private void abrirContaCorrente() {
        Cliente cliente;
        try {
            cliente = pesquisarClientePorId();
        } catch (RuntimeException ex){
            System.out.println(ex.getMessage());
            return;
        }
        criarContaCorrente(cliente);
    }

    private void abrirContaPoupanca() {
        Cliente cliente;
        try {
            cliente = pesquisarClientePorId();
        } catch (RuntimeException ex){
            System.out.println(ex.getMessage());
            aguardarRetorno();
            return;
        }
        criarContaPoupanca(cliente);
    }

    private String obterIdCliente() {
        String id;
        System.out.println("Digite o seu CPF/CNPJ: ");
        id = ENTRADA.nextLine();
        return id;
    }

    public void transacionarContas(){
        int opcao;
        int quebrar = 0;
        boolean fecharMenu = false;
        this.menu.ExibirTelaDeTransacoesDeConta();
        while (!fecharMenu){
            opcao = ENTRADA.nextInt();
            clearBuffer(ENTRADA);
            quebrar = getTentativas(opcao, 4, quebrar);

            fecharMenu = validarOpcaoMenuTransacionarContas(opcao);

            terminarLoopOuSair(opcao, quebrar, 4, false);
        }
        this.inicio();
    }

    private boolean validarOpcaoMenuTransacionarContas(int opcao) {
        return switch (opcao) {
            case 1 -> {
                try{
                    DepositarNaConta();
                } catch (RuntimeException ex){
                    System.out.println(ex.getMessage());
                }
                yield false;
            }
            case 2 -> {
                SacarDaConta();
                yield false;
            }
            case 3 -> {
                ConsultarSaldoConta();
                yield false;
            }
            default -> true;
        };
    }

    private void ConsultarSaldoConta() {
        TipoDeContaEnum tipoConta = obterTipoDeConta();
        String numeroConta = obterNumeroConta();
        if (tipoConta == TipoDeContaEnum.CONTA_POUPANCA){
            ContaPoupanca conta = banco.encontrarContaPoupancaPorId(numeroConta);
            if (conta != null) conta.consultarSaldo();
            else throw new RuntimeException("Conta não localizada!");
        }

        if (tipoConta == TipoDeContaEnum.CONTA_CORRENTE){
            ContaCorrente conta = banco.encontrarContaCorrentePorId(numeroConta);
            if (conta != null) conta.consultarSaldo();
            else throw new RuntimeException("Conta não localizada!");
        }
        aguardarRetorno();
        transacionarContas();
    }

    private void SacarDaConta() {
        TipoDeContaEnum tipoConta = obterTipoDeConta();
        String numeroConta = obterNumeroConta();
        double valorDeposito = obterValorTransacao("sacado");
        if (tipoConta == TipoDeContaEnum.CONTA_POUPANCA){
            ContaPoupanca conta = banco.encontrarContaPoupancaPorId(numeroConta);
            if (conta != null){
                try {
                    conta.sacar(valorDeposito);
                } catch (Exception e){
                    System.out.print("Não foi possível efetuar o saque. " + e.getLocalizedMessage());
                }
            } else throw new RuntimeException("Conta não localizada!");
        }

        if (tipoConta == TipoDeContaEnum.CONTA_CORRENTE){
            ContaCorrente conta = banco.encontrarContaCorrentePorId(numeroConta);
            if (conta != null){
                try {
                    conta.sacar(valorDeposito);
                } catch (Exception e){
                    System.out.print("Não foi possível efetuar o saque. " + e.getLocalizedMessage());
                }
            } else throw new RuntimeException("Conta não localizada!");
        }
        aguardarRetorno();
        transacionarContas();
    }

    private void DepositarNaConta() {
        TipoDeContaEnum tipo = obterTipoDeConta();
        String numeroConta = obterNumeroConta();
        double valorDeposito = obterValorTransacao("depositado");
        if (tipo == TipoDeContaEnum.CONTA_POUPANCA){
            ContaPoupanca conta = banco.encontrarContaPoupancaPorId(numeroConta);
            if (conta != null) conta.depositar(valorDeposito);
            else throw new RuntimeException("Conta não localizada!");
        }

        if (tipo == TipoDeContaEnum.CONTA_CORRENTE){
            ContaCorrente conta = banco.encontrarContaCorrentePorId(numeroConta);
            if (conta != null) conta.depositar(valorDeposito);
            else throw new RuntimeException("Conta não localizada!");
        }
        aguardarRetorno();
        transacionarContas();
    }

    private void aguardarRetorno() {
        System.out.print("Pressione enter para voltar ao menu: ");
        ENTRADA.nextLine();
    }

    private double obterValorTransacao(String transacao) {
        double valor;
        System.out.printf("Informe o valor a ser %s: ", transacao);
        valor = ENTRADA.nextDouble();
        clearBuffer(ENTRADA);
        return valor;
    }

    private String obterNumeroConta() {
        int numeroConta;
        System.out.print("Informe o número da conta: ");
        numeroConta = ENTRADA.nextInt();
        clearBuffer(ENTRADA);
        return Integer.toString(numeroConta);
    }

    private String obterNomeDoCliente() {
        String nomeCliente;
        System.out.println("Digite o seu nome: ");
        nomeCliente = ENTRADA.nextLine();
        return nomeCliente;
    }

    private void criarContaPoupanca(Cliente cliente) {
        String id = Integer.toString(banco.obterNumeroDeContaPoupancaParaAbertura());
        ContaPoupanca conta;
        try {
            conta = new ContaPoupanca(id, cliente, TipoDeContaEnum.CONTA_POUPANCA);
        } catch (RuntimeException ex){
            System.out.println("Conta não Aberta!!! " + ex.getMessage());
            aguardarRetorno();
            return;
        }
        banco.adicionarContaPoupancaNova(conta);
        System.out.printf("Conta número %d de %S aberta com sucesso!\n",
                conta.getId(), conta.getCliente().getNome());
        aguardarRetorno();
    }

    private void criarContaCorrente(Cliente cliente) {
        String numeroConta = Integer.toString(banco.obterNumeroDeContaCorrenteParaAbertura());
        ContaCorrente conta = new ContaCorrente(numeroConta, cliente, TipoDeContaEnum.CONTA_CORRENTE);
        banco.adicionarContaCorrenteNova(conta);
        System.out.printf("Conta número %d de %s aberta com sucesso!\n",
                conta.getId(), conta.getCliente().getNome());
        aguardarRetorno();
    }

    private static void clearBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}
