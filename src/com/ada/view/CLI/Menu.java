package com.ada.view.CLI;

import com.ada.controller.BancoController;
import com.ada.controller.ClienteController;
import com.ada.model.entity.cliente.IdentificadorCNPJ;
import com.ada.model.entity.cliente.IdentificadorCPF;
import com.ada.model.entity.cliente.Cliente;
import com.ada.model.entity.conta.ContaCorrente;
import com.ada.model.entity.conta.ContaPoupanca;
import com.ada.model.entity.conta.Transacao;
import com.ada.model.entity.interfaces.conta.Conta;
import com.ada.model.entity.interfaces.conta.Identificador;
import com.ada.model.helpers.enums.Classificacao;
import com.ada.model.helpers.enums.TipoDeContaEnum;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final BancoController bancoController;
    private final ClienteController clienteController;
    private final Scanner ENTRADA = new Scanner(System.in);

    public Menu(final BancoController bancoController, final ClienteController clienteController) {
        this.clienteController = clienteController;
        this.bancoController = bancoController;
    }

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
        return String.valueOf(caracter).repeat(Math.max(0, qtde));
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
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "4 - Transferir");
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "5 - Investir");
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "6 - Exibir Extrato");
        System.out.println();
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "7 - Voltar para o menu principal");
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
                "3 - Pesquisar Cliente por identificador (CPF/CNPJ)"
        );
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "4 - Listar todos os clientes");
        System.out.println();
        System.out.println(this.CadeiaDeCaracteres(30, ' ') + "5 - Voltar para o menu principal");
        System.out.println();
        System.out.println(this.CadeiaDeCaracteres(80, '='));
        System.out.print(this.CadeiaDeCaracteres(30, ' ') + "Digite a opção: ");
    }


    public void inicio(){
        int opcao;
        int tentativas = 0;
        boolean fecharMenu = false;
        ExibirMenuInicial();
        while(!fecharMenu){
            opcao = ENTRADA.nextInt();
            clearBuffer(ENTRADA);
            tentativas = getTentativas(opcao, 4, tentativas);
            fecharMenu = validarOpcaoMenuInicio(opcao);

            terminarLoopOuSair(opcao, tentativas, 4, true);
        }
    }

    private static int getTentativas(final int opcao, final int x, int tentativas) {
        if ((opcao > x || opcao < 1)) {
            System.out.print("Opção inválida! Escolha uma opção: ");
            tentativas++;
        }
        return tentativas;
    }

    private boolean validarOpcaoMenuInicio(final int opcao) {
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

    private static void terminarLoopOuSair(
            final int opcao, final int tentativas, final int maiorOpcao, final boolean sair
    ) {
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
        ImprimirCorpoDeCadastroDeCliente();
        while (!fecharMenu){
            opcao = ENTRADA.nextInt();
            clearBuffer(ENTRADA);
            tentativas = getTentativas(opcao, 5, tentativas);
            fecharMenu = avaliarOpcaoMenuCadastroDeClientes(opcao);
        }
        inicio();
    }

    private boolean avaliarOpcaoMenuCadastroDeClientes(final int opcao) {
        return switch (opcao) {
            case 1 -> {
                criarCliente();
                cadastroDeClientes();
                yield false;
            }
            case 2 -> {
                try {
                    System.out.println(pesquisarClientePorNome());
                } catch (RuntimeException ex){
                    System.out.println(ex.getMessage());
                    aguardarRetorno();
                    cadastroDeClientes();
                }
                aguardarRetorno();
                cadastroDeClientes();
                yield false;
            }
            case 3 -> {
                try {
                    System.out.println(pesquisarClientePorId());
                } catch (RuntimeException ex){
                    System.out.println(ex.getMessage());
                    aguardarRetorno();
                    cadastroDeClientes();
                }
                aguardarRetorno();
                cadastroDeClientes();
                yield false;
            }
            case 4 -> {
                imprimirClientes();
                yield false;
            }
            default -> true;
        };
    }

    private void imprimirClientes() {
        for (Cliente cliente : clienteController.listarClientes()){
            System.out.println(cliente);
        }
        aguardarRetorno();
        cadastroDeClientes();
    }

    private Cliente pesquisarClientePorId() {
        final String idAhPesquisar = obterIdentificador();
        return clienteController.buscarClientePorIdentificador(idAhPesquisar);
    }

    private Cliente pesquisarClientePorNome() {
        final String nomeAhPesquisar = obterNomeDoCliente();
        return clienteController.buscarClientePorNome(nomeAhPesquisar);
    }

    private void criarCliente() {
        final Classificacao classificacao = obterTipoDeCliente();
        final Identificador<String> id = obterIdCliente(classificacao);
        final String nomeCliente = obterNomeDoCliente();
        final Cliente cliente = new Cliente(id, classificacao, nomeCliente);
        clienteController.cadastrarCliente(cliente);
        System.out.println(cliente);
        aguardarRetorno();
    }

    private Classificacao obterTipoDeCliente() {
        boolean escolhaEfetuada = false;
        int opcao = 0;

        while (!escolhaEfetuada){
            System.out.println("Digite 1 - Pessoa Física (PF) ou 2 - Pessoa Jurídica (PJ): ");
            opcao = ENTRADA.nextInt();
            clearBuffer(ENTRADA);
            if (opcao > 0 && opcao <= 2) escolhaEfetuada = true;
        }

        return switch (opcao){
            case 1 -> Classificacao.PF;
            case 2 -> Classificacao.PJ;
            default -> throw new IllegalStateException("Unexpected value: " + opcao);
        };
    }

    public void aberturaDeContas(){
        int opcao;
        int tentativas = 0;
        boolean fecharMenu = false;
        ExibirTelaDeAberturaDeConta();
        while (!fecharMenu){
            opcao = ENTRADA.nextInt();
            clearBuffer(ENTRADA);
            tentativas = getTentativas(opcao, 3, tentativas);
            fecharMenu = validarOpcaoMenuAberturaDeContas(opcao);
            terminarLoopOuSair(opcao, tentativas, 3, false);
        }
        this.inicio();
    }

    private boolean validarOpcaoMenuAberturaDeContas(final int opcao) {
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
        final Cliente cliente;
        try {
            cliente = pesquisarClientePorId();
        } catch (RuntimeException ex){
            System.out.println(ex.getMessage());
            return;
        }
        bancoController.abrirConta(cliente, TipoDeContaEnum.CONTA_CORRENTE);
        final List<Conta> contas = bancoController.buscarContas(cliente.getIdentificador());
        if (!contas.isEmpty()) {
            for (Conta conta : contas) {
                if (conta instanceof ContaCorrente) {
                    System.out.printf("Conta número %S de %S aberta com sucesso!\n",
                            conta.getNumero(), conta.getCliente().getNome());
                    aguardarRetorno();
                }
            }
        }
    }

    private void abrirContaPoupanca() {
        final Cliente cliente;
        try {
            cliente = pesquisarClientePorId();
        } catch (RuntimeException ex){
            System.out.println(ex.getMessage());
            aguardarRetorno();
            return;
        }
        try {
            bancoController.abrirConta(cliente, TipoDeContaEnum.CONTA_POUPANCA);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            aguardarRetorno();
            return;

        }
        final List<Conta> contas = bancoController.buscarContas(cliente.getIdentificador());
        if (!contas.isEmpty()) {
            for (Conta conta : contas) {
                if (conta instanceof ContaPoupanca) {
                    System.out.printf("Conta número %S de %S aberta com sucesso!\n",
                            conta.getNumero(), conta.getCliente().getNome());
                    aguardarRetorno();
                }
            }
        }
    }

    private Identificador<String> obterIdCliente(final Classificacao classificacao) {
        if (classificacao == Classificacao.PF) return new IdentificadorCPF(obterIdentificador());
        if (classificacao == Classificacao.PJ) return new IdentificadorCNPJ(obterIdentificador());
        throw new IllegalArgumentException("Classificação inválida!");
    }

    private String obterIdentificador() {
        System.out.println("Digite o identificador: ");
        return ENTRADA.nextLine();
    }

    public void transacionarContas(){
        int opcao;
        int quebrar = 0;
        boolean fecharMenu = false;
        ExibirTelaDeTransacoesDeConta();
        while (!fecharMenu){
            opcao = ENTRADA.nextInt();
            clearBuffer(ENTRADA);
            quebrar = getTentativas(opcao, 7, quebrar);
            fecharMenu = validarOpcaoMenuTransacionarContas(opcao);
            terminarLoopOuSair(opcao, quebrar, 4, false);
        }
        this.inicio();
    }

    private boolean validarOpcaoMenuTransacionarContas(final int opcao) {
        return switch (opcao) {
            case 1 -> {
                try{
                    DepositarNaConta();
                } catch (RuntimeException ex){
                    System.out.println(ex.getMessage());
                    aguardarRetorno();
                    transacionarContas();
                }
                yield false;
            }
            case 2 -> {
                try {
                    SacarDaConta();
                } catch (RuntimeException e){
                    System.out.println(e.getMessage());
                    aguardarRetorno();
                    transacionarContas();
                }
                yield false;
            }
            case 3 -> {
                try {
                    ConsultarSaldoConta();
                } catch (RuntimeException e){
                    System.out.println(e.getMessage());
                    aguardarRetorno();
                    transacionarContas();
                }
                yield false;
            }
            case 4 -> {
                try {
                    transferirEntreContas();
                } catch (RuntimeException e){
                    System.out.println(e.getMessage());
                    aguardarRetorno();
                    transacionarContas();
                }
                yield false;
            }
            case 5 -> {
                try{
                    investirValores();
                } catch (RuntimeException e){
                    System.out.println(e.getMessage());
                    aguardarRetorno();
                    transacionarContas();
                }
                yield false;
            }
            case 6 -> {
                try {
                    exibirTransacoes();
                } catch (RuntimeException e){
                    System.out.println(e.getMessage());
                    aguardarRetorno();
                    transacionarContas();
                }
                yield false;
            }
            default -> true;
        };
    }

    private void exibirTransacoes() {
        final Conta conta = bancoController.buscarConta(obterNumeroConta());
        if (conta == null)
            throw new RuntimeException("Conta não localizada.");
        for (Transacao transacao : conta.getTransacoes())
            System.out.println(transacao.toString());
        aguardarRetorno();
        transacionarContas();
    }

    private void investirValores() {
        final Conta conta = bancoController.buscarConta(obterNumeroConta());
        if (!(conta instanceof ContaCorrente))
            throw new RuntimeException("Investimentos só podem ser efetuados a partir de uma conta corrente");
        bancoController.investir((ContaCorrente) conta, obterValorTransacao("investido"));
        aguardarRetorno();
        transacionarContas();
    }

    private void ConsultarSaldoConta() {
        final Conta conta = bancoController.buscarConta(obterNumeroConta());
        if (conta != null) System.out.printf("Saldo atual da conta R$ %.2f.", conta.consultarSaldo());
        else throw new RuntimeException("Conta não localizada!");
        aguardarRetorno();
        transacionarContas();
    }

    private void SacarDaConta() {
        final Conta conta = bancoController.buscarConta(obterNumeroConta());
        if (conta != null){
            try {
                bancoController.sacar(conta, obterValorTransacao("sacado"));
            } catch (Exception e){
                System.out.print("Não foi possível efetuar o saque. " + e.getLocalizedMessage());
            }
        } else throw new RuntimeException("Conta não localizada!");
        clearBuffer(ENTRADA);
        aguardarRetorno();
        transacionarContas();
    }

    private void DepositarNaConta() {
        final Conta conta = bancoController.buscarConta(obterNumeroConta());
        if (conta != null)
            bancoController.depositar(conta, obterValorTransacao("depositado"));
        else throw new RuntimeException("Conta não localizada!");
        clearBuffer(ENTRADA);
        aguardarRetorno();
        transacionarContas();
    }

    private void transferirEntreContas() {
        System.out.println("Escolha a conta de ORIGEM: ");
        final Conta contaOrigem = bancoController.buscarConta(obterNumeroConta());
        System.out.println("Escolha a conta de DESTINO: ");
        final Conta contaDestino = bancoController.buscarConta(obterNumeroConta());
        if (contaOrigem != null && contaDestino != null)
            bancoController.transferir(contaOrigem, contaDestino, obterValorTransacao("transferido"));
        else throw new RuntimeException("Uma das contas não localizadas");
        clearBuffer(ENTRADA);
        aguardarRetorno();
        transacionarContas();
    }

    private void aguardarRetorno() {
        System.out.println("Pressione enter para voltar ao menu: ");
        ENTRADA.nextLine();
    }

    private double obterValorTransacao(String transacao) {
        System.out.printf("Informe o valor a ser %s: ", transacao);
        return ENTRADA.nextDouble();
    }

    private String obterNumeroConta() {
        System.out.println("Informe o número da conta: ");
        return String.format("%6d", Integer.parseInt(ENTRADA.nextLine())) ;
    }

    private String obterNomeDoCliente() {
        System.out.println("Digite o seu nome: ");
        return ENTRADA.nextLine();
    }

    private static void clearBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}
