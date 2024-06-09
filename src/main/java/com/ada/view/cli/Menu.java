package com.ada.view.cli;

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
    private static final Scanner entrada = new Scanner(System.in);
    private static final String TXT_SELECIONAR_OPCAO = "Selecione uma opção: ";
    private static final String TXT_DIGITAR_OPCAO = "Digite uma opção: ";
    private static final String TXT_NAO_LOCALIZADA = "Conta não localizada!";

    public Menu(final BancoController bancoController, final ClienteController clienteController) {
        this.clienteController = clienteController;
        this.bancoController = bancoController;
    }

    public void exibirMenuInicial() {
        this.limparTela();
        this.imprimirCabecalho();
        this.imprimirCorpoDoMenu();
    }

    public void exibirTelaDeAberturaDeConta(){
        this.limparTela();
        this.imprimirCabecalho();
        this.imprimirCorpoAberturaDeConta();
    }

    public void exibirTelaDeTransacoesDeConta(){
        this.limparTela();
        this.imprimirCabecalho();
        this.imprimirCorpoTransacoesDeConta();
    }

    private void limparTela() {
        if (System.getProperty("os.name").contains("Windows")) {
            try {
                String[] command = new String[]{"cls"};
                Runtime.getRuntime().exec(command);
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
        else {
            try {
                String[] command = new String[]{"clear"};
                Runtime.getRuntime().exec(command);
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    private void imprimirCabecalho() {
        System.out.println(this.cadeiaDeCaracteres(80, '='));
        System.out.println(
                this.cadeiaDeCaracteres(29, ' ') + "Caixa Econômica Federal"
                + this.cadeiaDeCaracteres(28, ' '));
        System.out.println(this.cadeiaDeCaracteres(80, '='));
    }

    private String cadeiaDeCaracteres(int quantidade, char caracter){
        return String.valueOf(caracter).repeat(Math.max(0, quantidade));
    }

    private void imprimirCorpoDoMenu(){
        System.out.println();
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + "Operações de Conta: ");
        System.out.println();
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + TXT_SELECIONAR_OPCAO);
        System.out.println();
        System.out.println();
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + "1 - Cadastro de Clientes");
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + "2 - Abertura de conta");
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + "3 - Transações de conta");
        System.out.println();
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + "4 - Sair da aplicação");
        System.out.println();
        System.out.println(this.cadeiaDeCaracteres(80, '='));
        System.out.print(this.cadeiaDeCaracteres(30, ' ') + TXT_DIGITAR_OPCAO);
    }

    private void imprimirCorpoAberturaDeConta(){
        // TODO: Criar opção de listar todas as contas.
        System.out.println();
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + "Operações de Conta");
        System.out.println();
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + TXT_SELECIONAR_OPCAO);
        System.out.println();
        System.out.println();
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + "1 - Abrir conta poupança");
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + "2 - Abrir conta corrente");
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + "3 - Listar contas abertas");
        System.out.println();
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + "4 - Voltar para o menu principal");
        System.out.println();
        System.out.println(this.cadeiaDeCaracteres(80, '='));
        System.out.print(this.cadeiaDeCaracteres(30, ' ') + TXT_DIGITAR_OPCAO);
    }

    private void imprimirCorpoTransacoesDeConta(){
        System.out.println();
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + "Transações de Conta");
        System.out.println();
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + TXT_SELECIONAR_OPCAO);
        System.out.println();
        System.out.println();
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + "1 - Depositar");
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + "2 - Sacar");
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + "3 - Consultar saldo");
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + "4 - Transferir");
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + "5 - Investir");
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + "6 - Exibir Extrato");
        System.out.println();
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + "7 - Voltar para o menu principal");
        System.out.println();
        System.out.println(this.cadeiaDeCaracteres(80, '='));
        System.out.print(this.cadeiaDeCaracteres(30, ' ') + TXT_DIGITAR_OPCAO);
    }

    public void imprimirCorpoDeCadastroDeCliente(){
        System.out.println();
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + "Cadastro de Clientes");
        System.out.println();
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + TXT_SELECIONAR_OPCAO);
        System.out.println();
        System.out.println();
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + "1 - Cadastrar Cliente");
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + "2 - Pesquisar Cliente por nome");
        System.out.println(this.cadeiaDeCaracteres(30, ' ') +
                "3 - Pesquisar Cliente por identificador (CPF/CNPJ)"
        );
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + "4 - Listar todos os clientes");
        System.out.println();
        System.out.println(this.cadeiaDeCaracteres(30, ' ') + "5 - Voltar para o menu principal");
        System.out.println();
        System.out.println(this.cadeiaDeCaracteres(80, '='));
        System.out.print(this.cadeiaDeCaracteres(30, ' ') + TXT_DIGITAR_OPCAO);
    }


    public void inicio(){
        int opcao;
        int tentativas = 0;
        boolean fecharMenu = false;
        exibirMenuInicial();
        while(!fecharMenu){
            opcao = entrada.nextInt();
            clearBuffer();
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
        imprimirCorpoDeCadastroDeCliente();
        while (!fecharMenu){
            opcao = entrada.nextInt();
            clearBuffer();
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
            String textoEscolha = entrada.nextLine();
            if (textoEscolha.length() == 1){
                opcao = Character.getNumericValue(textoEscolha.charAt(0));
                if (opcao > 0 && opcao <= 2) escolhaEfetuada = true;
            }
        }

        return switch (opcao){
            case 1 -> Classificacao.PF;
            case 2 -> Classificacao.PJ;
            default -> throw new IllegalStateException("Valor inválido: " + opcao);
        };
    }

    public void aberturaDeContas(){
        int opcao;
        int tentativas = 0;
        boolean fecharMenu = false;
        exibirTelaDeAberturaDeConta();
        while (!fecharMenu){
            opcao = entrada.nextInt();
            clearBuffer();
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
            case 3 -> {
                listarContasAbertas();
                aberturaDeContas();
                yield false;
            }
            default -> true;
        };
    }

    private void listarContasAbertas() {

        for (Conta conta : bancoController.listarTodasAsContas()){
            System.out.println(conta.toString());
        }
        aguardarRetorno();
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
        return entrada.nextLine();
    }

    public void transacionarContas(){
        int opcao;
        int quebrar = 0;
        boolean fecharMenu = false;
        exibirTelaDeTransacoesDeConta();
        while (!fecharMenu){
            opcao = entrada.nextInt();
            clearBuffer();
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
                    depositarNaConta();
                } catch (RuntimeException ex){
                    System.out.println(ex.getMessage());
                    aguardarRetorno();
                    transacionarContas();
                }
                yield false;
            }
            case 2 -> {
                try {
                    sacarDaConta();
                } catch (RuntimeException e){
                    System.out.println(e.getMessage());
                    aguardarRetorno();
                    transacionarContas();
                }
                yield false;
            }
            case 3 -> {
                try {
                    consultarSaldoConta();
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
            throw new IllegalArgumentException("Conta não localizada.");
        for (Transacao transacao : conta.getTransacoes())
            System.out.println(transacao.toString());
        aguardarRetorno();
        transacionarContas();
    }

    private void investirValores() {
        final Conta conta = bancoController.buscarConta(obterNumeroConta());
        if (!(conta instanceof ContaCorrente))
            throw new IllegalArgumentException("Investimentos só podem ser efetuados a partir de uma conta corrente");
        bancoController.investir((ContaCorrente) conta, obterValorTransacao("investido"));
        aguardarRetorno();
        transacionarContas();
    }

    private void consultarSaldoConta() {
        final Conta conta = bancoController.buscarConta(obterNumeroConta());
        if (conta != null) System.out.printf("Saldo atual da conta R$ %.2f.", conta.consultarSaldo());
        else throw new IllegalArgumentException(TXT_NAO_LOCALIZADA);
        aguardarRetorno();
        transacionarContas();
    }

    private void sacarDaConta() {
        final Conta conta = bancoController.buscarConta(obterNumeroConta());
        if (conta != null){
            try {
                bancoController.sacar(conta, obterValorTransacao("sacado"));
            } catch (Exception e){
                System.out.print("Não foi possível efetuar o saque. " + e.getLocalizedMessage());
            }
        } else throw new IllegalArgumentException(TXT_NAO_LOCALIZADA);
        clearBuffer();
        aguardarRetorno();
        transacionarContas();
    }

    private void depositarNaConta() {
        final Conta conta = bancoController.buscarConta(obterNumeroConta());
        if (conta != null)
            bancoController.depositar(conta, obterValorTransacao("depositado"));
        else throw new IllegalArgumentException(TXT_NAO_LOCALIZADA);
        clearBuffer();
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
        else throw new IllegalArgumentException("Uma das contas não localizadas");
        clearBuffer();
        aguardarRetorno();
        transacionarContas();
    }

    private void aguardarRetorno() {
        System.out.println("Pressione enter para voltar ao menu: ");
        entrada.nextLine();
    }

    private double obterValorTransacao(String transacao) {
        System.out.printf("Informe o valor a ser %s: ", transacao);
        return entrada.nextDouble();
    }

    private String obterNumeroConta() {
        System.out.println("Informe o número da conta: ");
        return String.format("%06d", Integer.parseInt(entrada.nextLine())) ;
    }

    private String obterNomeDoCliente() {
        System.out.println("Digite o seu nome: ");
        return entrada.nextLine();
    }

    private static void clearBuffer() {
        if (Menu.entrada.hasNextLine()) {
            Menu.entrada.nextLine();
        }
    }
}
