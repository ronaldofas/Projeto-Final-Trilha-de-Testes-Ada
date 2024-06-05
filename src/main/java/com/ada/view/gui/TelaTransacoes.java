package com.ada.view.gui;

import com.ada.controller.BancoController;
import com.ada.model.entity.conta.ContaCorrente;
import com.ada.model.entity.conta.Transacao;
import com.ada.model.entity.interfaces.conta.Conta;
import com.ada.model.helpers.enums.TipoDeContaEnum;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaTransacoes extends JFrame {

    private JPanel painelPrincipal;
    private JPanel aba1;
    private JPanel aba2;
    private JPanel aba3;
    private JPanel aba4;
    private JPanel aba5;
    private JPanel aba6;
    private JTextField numeroContaTextField;
    private JTextField valorSaqueTextField;
    private JTextField valorDepositoTextField;
    private JButton sacarButton;
    private JButton depositarButton;
    private JButton consultarSaldoButton;
    private JTabbedPane painelComAbas;
    private final BancoController banco;
    private List<Transacao> transacoes;
    private JScrollPane scrollDoTexto;
    private JTextArea textoLongo;
    private static final String TXT_ERRO_PREENCHIMENTO = "Erro, preencha corretamente o número da conta!";

    public TelaTransacoes(BancoController banco) {
        super("Transações Bancárias");

        this.banco = banco;
        this.transacoes = new ArrayList<>();

        configurarAhTela();

        criarPainelPrincipal();

        configuraLinhaUmDaJanela();

        configuraLinhaDoisDaJanela();

        criarPainelDeAbas();

        criarAbas();

        criaObjetosAba1();

        criaObjetosAba2();

        criaObjetosAba3();

        criaObjetosAba4();

        criaObjetosAba5();

        criarTextoEscrolavel();

        criaObjetosAba6();

        adicionarFuncionalidadeAoBotaoSacar();

        adicionarFuncionalidadeAoBotaoDepositar();

        adicionarFuncionalidadeAoBotaoConsultarSaldo();

    }

    private void adicionarFuncionalidadeAoBotaoConsultarSaldo() {
        consultarSaldoButton.addActionListener(e -> {
            // Validar os dados
            String idConta = obterIdFormatado();
            Conta conta = banco.buscarConta(idConta);
            double saldo;

            if ((idConta.isBlank() || idConta.isEmpty()) ){
                JOptionPane.showMessageDialog(null, TXT_ERRO_PREENCHIMENTO);
            } else {
                // Efetuar saque
                saldo = conta.consultarSaldo();
                var textoSaldo = String.format("O saldo atual da conta é de R$ %.2f", (Object)saldo);
                JOptionPane.showMessageDialog(null,textoSaldo);
            }
        });
    }

    private void adicionarFuncionalidadeAoBotaoDepositar() {
        depositarButton.addActionListener(e -> {
            // Validar os dados
            String idConta = obterIdFormatado();
            String valorDeposito = valorDepositoTextField.getText();
            Conta conta = banco.buscarConta(idConta);

            if ((idConta.isBlank() || idConta.isEmpty()) ){
                JOptionPane.showMessageDialog(null, TXT_ERRO_PREENCHIMENTO);
            } else {
                if (valorDeposito.isBlank() || valorDeposito.isEmpty())
                    JOptionPane
                            .showMessageDialog(
                                    null, "É necessário preencher o valor a depositar!");
                else {
                    // Efetuar saque
                    try {
                        conta.depositar(Double.parseDouble(valorDeposito));
                        JOptionPane.showMessageDialog(null, "Depósito efetuado com sucesso");
                    } catch (Exception ex){
                        JOptionPane
                                .showMessageDialog(
                                        null, "Não foi possível depositar. " + ex.getMessage());
                    }
                }
            }
        });
    }

    private String obterIdFormatado() {
        return String.format(numeroContaTextField.getText());
    }

    private void adicionarFuncionalidadeAoBotaoSacar() {
        sacarButton.addActionListener(e -> {
            // Validar os dados
            String idConta = obterIdFormatado();
            String valorSaque = valorSaqueTextField.getText();
            Conta conta = banco.buscarConta(idConta);

            if ((idConta.isBlank() || idConta.isEmpty()) ){
                JOptionPane.showMessageDialog(null, TXT_ERRO_PREENCHIMENTO);
            } else {
                if (valorSaque.isBlank() || valorSaque.isEmpty())
                    JOptionPane
                            .showMessageDialog(null, "É necessário preencher o valor a sacar!");
                else {
                    // Efetuar saque
                    try {
                        conta.sacar(Double.parseDouble(valorSaque));
                        JOptionPane.showMessageDialog(null, "Saque efetuado com suceso.");
                    } catch (Exception ex){
                        JOptionPane
                                .showMessageDialog(
                                        null, "Não foi possível sacar. " + ex.getMessage());
                    }
                }
            }
        });
    }

    private void criaObjetosAba6() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        aba6.add(scrollDoTexto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JButton extratoButton = new JButton("Gerar extrato");
        aba6.add(extratoButton, gbc);

        extratoButton.addActionListener(e -> {
            Conta contaOrigem = banco.buscarConta(numeroContaTextField.getText().toUpperCase());
            transacoes = contaOrigem.getTransacoes();
            StringBuilder extratoPronto = new StringBuilder();
            extratoPronto.append(" Extrato da Conta n° ");
            extratoPronto.append(contaOrigem.getNumero());
            extratoPronto.append("\n Cliente: ");
            extratoPronto.append(contaOrigem.getCliente().getNome());
            extratoPronto.append("\n");
            extratoPronto.append("\n Data - Tipo transação  - Destinatario - Valor - Observação");
            for (Transacao transacao : transacoes){
                extratoPronto.append("\n");
                extratoPronto.append(transacao.toString());
            }
            extratoPronto.append("\n\nSaldo: ");
            extratoPronto.append(contaOrigem.consultarSaldo());
            textoLongo.setText("");
            textoLongo.setText(extratoPronto.toString());
        });
    }

    private void criarTextoEscrolavel(){
        textoLongo = new JTextArea();
        scrollDoTexto = new JScrollPane(textoLongo);
        scrollDoTexto.setPreferredSize(new Dimension(300,200));
    }

    private void criaObjetosAba5() {
        // Rótulo "Valor"
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        JLabel valorInvestirLabel = new JLabel("Valor a investir: ");
        aba5.add(valorInvestirLabel, gbc);

        // Campo de texto "Valor"
        gbc.gridx = 1;
        gbc.gridy = 0;
        JTextField valorInvestirTextField = new JTextField(10);
        aba5.add(valorInvestirTextField, gbc);

        // Botão "Investir"
        gbc.gridx = 0;
        gbc.gridy = 1;
        JButton investirButton = new JButton("Investir");
        aba5.add(investirButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JTextArea resultadoInvestirTextArea = new JTextArea();
        aba5.add(resultadoInvestirTextArea, gbc);

        try {
            investirButton.addActionListener(e -> {
                String valorAhTransferirCapturado = valorInvestirTextField.getText();
                double valorTransacao = Double.parseDouble(valorAhTransferirCapturado);
                Conta contaOrigem = banco.buscarConta(numeroContaTextField.getText().toUpperCase());
                if (!(contaOrigem instanceof ContaCorrente))
                    throw new IllegalArgumentException(
                            "Investimentos só podem ser efetuados a partir de uma conta corrente");
                this.banco.investir((ContaCorrente) contaOrigem, valorTransacao);

                JOptionPane.showMessageDialog(
                        null,
                        String.format(
                                "Investimento na conta do cliente %S efetuada com sucesso!",
                                contaOrigem.getCliente().getNome()
                                )
                );
            });
        } catch (RuntimeException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void criaObjetosAba4() {
        // Rótulo "Conta Destino"
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        JLabel contaDestinoLbl = new JLabel("Conta destino: ");
        aba4.add(contaDestinoLbl, gbc);

        // Campo de texto "conta destino"
        gbc.gridx = 1;
        gbc.gridy = 0;
        JTextField contaDestinoTextField = new JTextField(10);
        aba4.add(contaDestinoTextField, gbc);

        // Rótulo "Valor"
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        JLabel valorAhTransferir = new JLabel("Valor a transferir: ");
        aba4.add(valorAhTransferir, gbc);

        // Campo de texto "Valor"
        gbc.gridx = 1;
        gbc.gridy = 1;
        JTextField valorAhTransferirTextField = new JTextField(10);
        aba4.add(valorAhTransferirTextField, gbc);

        // Botão "Investir"
        gbc.gridx = 0;
        gbc.gridy = 2;
        JButton transferirButton = new JButton("Transferir");
        aba4.add(transferirButton, gbc);

        transferirButton.addActionListener(e -> {
            String valorAhTransferirCapturado = valorAhTransferirTextField.getText();
            double valorTransacao = Double.parseDouble(valorAhTransferirCapturado);
            Conta contaOrigem = banco.buscarConta(numeroContaTextField.getText().toUpperCase());
            Conta contaDestino = banco.buscarConta(contaDestinoTextField.getText().toUpperCase());
            this.banco.transferir(contaOrigem, contaDestino, valorTransacao);

            JOptionPane.showMessageDialog(null,"Transferência efetuada com sucesso!");
        });
    }

    private void criaObjetosAba3() {
        // Botão "Depositar"
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        consultarSaldoButton = new JButton("Consultar saldo");
        aba3.add(consultarSaldoButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JTextArea consultaSaldoTextArea = new JTextArea();
        aba3.add(consultaSaldoTextArea);
    }

    private void criaObjetosAba2() {
        // Rótulo "Valor"
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        JLabel valorDepositoLabel = new JLabel("Valor a depositar: ");
        aba2.add(valorDepositoLabel, gbc);

        // Campo de texto "Valor"
        gbc.gridx = 1;
        gbc.gridy = 0;
        valorDepositoTextField = new JTextField(10);
        aba2.add(valorDepositoTextField, gbc);

        // Botão "Depositar"
        gbc.gridx = 0;
        gbc.gridy = 1;
        depositarButton = new JButton("Depositar");
        aba2.add(depositarButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JTextArea resultadoDepositarTextArea = new JTextArea();
        aba2.add(resultadoDepositarTextArea);
    }

    private void criaObjetosAba1() {
        // Rótulo "Valor"
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        JLabel valorSaqueLabel = new JLabel("Valor a sacar: ");
        aba1.add(valorSaqueLabel, gbc);

        // Campo de texto "Valor"
        gbc.gridx = 1;
        gbc.gridy = 0;
        valorSaqueTextField = new JTextField(10);
        aba1.add(valorSaqueTextField, gbc);

        // Botão "Sacar"
        gbc.gridx = 0;
        gbc.gridy = 1;
        sacarButton = new JButton("Sacar");
        aba1.add(sacarButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JTextArea resultadoSacarTextArea = new JTextArea();
        aba1.add(resultadoSacarTextArea, gbc);
    }

    private void criarAbas() {
        aba1 = new JPanel();
        aba1.setLayout(new GridBagLayout());
        painelComAbas.insertTab("Sacar", null, aba1, "", 0);

        aba2 = new JPanel();
        aba2.setLayout(new GridBagLayout());
        painelComAbas.insertTab("Depositar", null, aba2, "", 1);

        aba3 = new JPanel();
        aba3.setLayout(new GridBagLayout());
        painelComAbas.insertTab("Consultar Saldo", null, aba3, "", 2);

        aba4 = new JPanel();
        aba4.setLayout(new GridBagLayout());
        painelComAbas.insertTab("Transferir", null, aba4, "", 3);

        aba5 = new JPanel();
        aba5.setLayout(new GridBagLayout());
        painelComAbas.insertTab("Investir", null, aba5, "", 4);

        aba6 = new JPanel();
        aba6.setLayout(new GridBagLayout());
        painelComAbas.insertTab("Extrato", null, aba6, "", 5);
    }

    private void criarPainelDeAbas() {
        painelComAbas = new JTabbedPane();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = 3;
        gbc.gridheight = 3;
        painelComAbas.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        painelComAbas.setPreferredSize(new Dimension(400, 300));
        painelPrincipal.add(painelComAbas, gbc);
    }

    private void configuraLinhaDoisDaJanela() {
        // Rótulo "Número da conta"
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        JLabel numeroContaLabel = new JLabel("Número da conta:");
        painelPrincipal.add(numeroContaLabel, gbc);

        // Campo de texto "Número da conta"
        gbc.gridx = 2;
        gbc.gridy = 1;
        numeroContaTextField = new JTextField(10);
        painelPrincipal.add(numeroContaTextField, gbc);
    }

    private void configuraLinhaUmDaJanela() {
        JComboBox<TipoDeContaEnum> tipoContaComboBox;
        // Rótulo "Tipo da conta"
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1; // coluna2
        gbc.gridy = 0; // linha 1
        gbc.insets = new Insets(10, 10, 10, 10);
        JLabel tipoContaLabel = new JLabel("Tipo da conta:");
        painelPrincipal.add(tipoContaLabel, gbc);

        // ComboBox "Tipo da conta"
        gbc.gridx = 2; // coluna2
        gbc.gridy = 0; // linha 1
        tipoContaComboBox = new JComboBox<>(TipoDeContaEnum.values());
        painelPrincipal.add(tipoContaComboBox, gbc);
    }

    private void criarPainelPrincipal() {
        // Painel principal
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new GridBagLayout());
        add(painelPrincipal, BorderLayout.CENTER);
    }

    private void configurarAhTela() {
        // Configurações da tela
        setSize(1024, 768);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
    }

}