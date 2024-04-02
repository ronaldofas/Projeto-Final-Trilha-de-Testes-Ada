package controller;

import helpers.enums.TipoClienteEnum;
import helpers.enums.TipoDeContaEnum;
import model.Cliente;
import model.ContaCorrente;
import model.ContaPoupanca;
import view.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BancoController {

    private final List<ContaCorrente> contasCorrente = new ArrayList<>();
    private final List<ContaPoupanca> contasPoupanca = new ArrayList<>();
    private final List<Cliente> clientes = new ArrayList<>();
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
            if ((opcao > 4 || opcao < 1)){
                System.out.print("Opção inválida! Escolha uma opção: ");
                tentativas++;
            }
            fecharMenu = validarOpcaoMenuInicio(opcao);

            terminarLoopOuSair(opcao, tentativas, 4, true);
        }
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
            if ((opcao > 5 || opcao < 1) && tentativas < 3){
                System.out.println("Opção inválida! Escolha uma opção: ");
                tentativas++;
            }
            fecharMenu = avaliarOpcaoMenuCadastroDeClientes(opcao);
        }
        inicio();
    }

    private boolean avaliarOpcaoMenuCadastroDeClientes(int opcao) {
        return switch (opcao) {
            case 1 -> {
                criarCliente();
                yield true;
            }
            case 2 -> {
                try {
                    pesquisarClientePorNome();
                } catch (RuntimeException ex){
                    System.out.println(ex.getMessage());
                    aguardarRetorno();
                }
                yield false;
            }
            case 3 -> {
                try {
                    pesquisarClientePorId();
                } catch (RuntimeException ex){
                    System.out.println(ex.getMessage());
                }
                yield false;
            }
            case 4 -> {
                imprimirClientes();
                yield false;
            }
            case 5 -> true;
            default -> true;
        };
    }

    private void imprimirClientes() {
        if (!clientes.isEmpty()){
            for (Cliente cliente : clientes){
                System.out.println(cliente);
            }
        } else {
            System.out.println("Não existem clientes cadastrados!");
        }
        aguardarRetorno();
    }

    private Cliente pesquisarClientePorId() {
        String idAhPesquisar = obterIdCliente();
        Cliente clienteLocalizado = null;
        for (Cliente cliente: clientes) {
            if (cliente.getId().contains(idAhPesquisar)){
                clienteLocalizado = cliente;
                System.out.println("Localizado: " + clienteLocalizado);
                aguardarRetorno();
                break;
            }
        }
        if (clienteLocalizado == null)
            throw new RuntimeException("Nenhum cliente localizado com o nome informado!");
        return clienteLocalizado;
    }

    private Cliente pesquisarClientePorNome() {
        String nomeAhPesquisar = obterNomeDoCliente();
        Cliente clienteLocalizado = null;
        for (Cliente cliente: clientes) {
            if (cliente.getNome().contains(nomeAhPesquisar)){
                clienteLocalizado = cliente;
                System.out.println("Localizado: " + clienteLocalizado);
                aguardarRetorno();
                break;
            }
        }
        if (clienteLocalizado == null)
            throw new RuntimeException("Nenhum cliente localizado com o nome informado!");
        return clienteLocalizado;
    }

    private void criarCliente() {
        TipoClienteEnum classificacao = obterTipoDeCliente();
        String id = obterIdCliente();
        String nomeCliente = obterNomeDoCliente();
        Cliente cliente = new Cliente(id, classificacao, nomeCliente);
        this.clientes.add(cliente);
        System.out.println(cliente);
        aguardarRetorno();
    }

    private TipoClienteEnum obterTipoDeCliente() {
        TipoClienteEnum classificacao;
        boolean escolhaEfetuada = false;
        int opcao = 0;

        while (!escolhaEfetuada){
            System.out.println("Digite 1 - Pessoa Física (PF) ou 2 - Pessoa Jurídica (PJ): ");
            opcao = ENTRADA.nextInt();
            clearBuffer(ENTRADA);
            if (opcao > 0 && opcao <= 2) escolhaEfetuada = true;
        }
        
        classificacao = switch (opcao){
            case 1 -> TipoClienteEnum.PF;
            case 2 -> TipoClienteEnum.PJ;
            default -> throw new IllegalStateException("Unexpected value: " + opcao);
        };

        return  classificacao;
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
            case 1 -> TipoDeContaEnum.ContaPoupanca;
            case 2 -> TipoDeContaEnum.ContaCorrente;
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
            if ((opcao > 3 || opcao < 1)){
                System.out.print("Opção inválida! Escolha uma opção: ");
                tentativas++;
            }

            fecharMenu = validarOpcaoMenuAberturaDeContas(opcao);

            terminarLoopOuSair(opcao, tentativas, 3, false);
        }
        this.inicio();
    }

    private boolean validarOpcaoMenuAberturaDeContas(int opcao) {
        return switch (opcao) {
            case 1 -> {
                abrirContaPoupanca();
                yield false;
            }
            case 2 -> {
                abrirContaCorrente();
                yield false;
            }
            case 3 -> true;
            default -> true;
        };
    }

    private void abrirContaCorrente() {
        Cliente cliente = null;
        try {
            cliente = pesquisarClientePorId();
        } catch (RuntimeException ex){
            System.out.println(ex.getMessage());
            return;
        }
        criarContaCorrente(cliente);
    }

    private void abrirContaPoupanca() {
        Cliente cliente = null;
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
            if ((opcao > 4 || opcao < 1)){
                System.out.print("Opção inválida! Escolha uma opção: ");
                quebrar++;
            }

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
                SacarNaConta();
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
        int numeroConta = obterNumeroConta();
        if (tipoConta == TipoDeContaEnum.ContaPoupanca){
            ContaPoupanca conta = encontrarContaPoupanca(numeroConta);
            if (conta != null) conta.consultarSaldo();
            else throw new RuntimeException("Conta não localizada!");
        }

        if (tipoConta == TipoDeContaEnum.ContaCorrente){
            ContaCorrente conta = encontrarContaCorrente(numeroConta);
            if (conta != null) conta.consultarSaldo();
            else throw new RuntimeException("Conta não localizada!");
        }
        aguardarRetorno();
        transacionarContas();
    }

    private void SacarNaConta() {
        TipoDeContaEnum tipoConta = obterTipoDeConta();
        int numeroConta = obterNumeroConta();
        double valorDeposito = obterValorTransacao("sacado");
        if (tipoConta == TipoDeContaEnum.ContaPoupanca){
            ContaPoupanca conta = encontrarContaPoupanca(numeroConta);
            if (conta != null){
                try {
                    conta.sacar(valorDeposito);
                } catch (Exception e){
                    System.out.print("Não foi possível efetuar o saque. " + e.getLocalizedMessage());
                }
            } else throw new RuntimeException("Conta não localizada!");
        }

        if (tipoConta == TipoDeContaEnum.ContaCorrente){
            ContaCorrente conta = encontrarContaCorrente(numeroConta);
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
        int numeroConta = obterNumeroConta();
        double valorDeposito = obterValorTransacao("depositado");
        if (tipo == TipoDeContaEnum.ContaPoupanca){
            ContaPoupanca conta = encontrarContaPoupanca(numeroConta);
            if (conta != null) conta.depositar(valorDeposito);
            else throw new RuntimeException("Conta não localizada!");
        }

        if (tipo == TipoDeContaEnum.ContaCorrente){
            ContaCorrente conta = encontrarContaCorrente(numeroConta);
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

    private void aguardarRetornoSemLimpezaAnterior() {
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

    private int obterNumeroConta() {
        int numeroConta;
        System.out.print("Informe o número da conta: ");
        numeroConta = ENTRADA.nextInt();
        clearBuffer(ENTRADA);
        return numeroConta;
    }

    private ContaPoupanca encontrarContaPoupanca(int numeroConta) {
        ContaPoupanca contaPoupanca = null;
        for (ContaPoupanca poupanca : this.contasPoupanca) {
            if (poupanca != null && poupanca.getId() == numeroConta) {
                contaPoupanca = poupanca;
            }
        }
        return contaPoupanca;
    }

    private ContaCorrente encontrarContaCorrente(int numeroConta) {
        ContaCorrente contaCorrente = null;
        for (ContaCorrente corrente : this.contasCorrente) {
            if (corrente.getId() == numeroConta) {
                contaCorrente = corrente;
            }
        }
        return contaCorrente;
    }

    private String obterNomeDoCliente() {
        String nomeCliente;
        System.out.println("Digite o seu nome: ");
        nomeCliente = ENTRADA.nextLine();
        return nomeCliente;
    }

    private void criarContaPoupanca(Cliente cliente) {
        int id = this.obterNumeroDeContaPoupancaParaAbertura();
        ContaPoupanca conta = null;
        try {
            conta = new ContaPoupanca(id, cliente);
        } catch (RuntimeException ex){
            System.out.println("Conta não Aberta!!! " + ex.getMessage());
            return;
        }
        this.contasPoupanca.add(conta);
        System.out.printf("Conta número %d de %S aberta com sucesso!\n",
                conta.getId(), conta.getCliente().getNome());
        aguardarRetornoSemLimpezaAnterior();
    }

    private void criarContaCorrente(Cliente cliente) {
        int numeroConta = obterNumeroDeContaCorrenteParaAbertura();
        ContaCorrente conta = new ContaCorrente(numeroConta, cliente);
        this.contasCorrente.add(conta);
        System.out.printf("Conta número %d de %s aberta com sucesso!\n",
                conta.getId(), conta.getCliente().getNome());
        aguardarRetornoSemLimpezaAnterior();
    }

    private int obterNumeroDeContaPoupancaParaAbertura() {
        int numeroNovo = 0;

        for (ContaPoupanca conta : contasPoupanca){
            if (conta.getId() > numeroNovo) numeroNovo = conta.getId();
        }
        numeroNovo ++;

        return numeroNovo;
    }

    private int obterNumeroDeContaCorrenteParaAbertura() {
        int numeroNovo = 0;

        for (ContaCorrente conta : contasCorrente){
            if (conta.getId() > numeroNovo) numeroNovo = conta.getId();
        }
        numeroNovo ++;

        return numeroNovo;
    }

    private static void clearBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}
