package com.ada.view.GUI;

import com.ada.controller.BancoGUIController;
import com.ada.helpers.enums.TipoDeContaEnum;
import com.ada.model.entity.Cliente;
import com.ada.model.entity.Conta;
import com.ada.view.GUI.model.ContaTableModel;

import javax.swing.*;
import java.awt.*;

public class TelaTransacoes extends JFrame {

    private JPanel painelPrincipal, aba1, aba2, aba3;
    private JComboBox<TipoDeContaEnum> tipoContaComboBox;
    private JTextField numeroContaTextField, valorSaqueTextField, valorDepositoTextField;
    private JTextArea resultadoSacarTextArea, resultadoDepositarTextArea, consultaSaldoTextArea;
    private JButton sacarButton, depositarButton, consultarSaldoButton;
    private JTabbedPane painelComAbas;
    private JLabel valorSaqueLabel, valorDepositoLabel;
    private BancoGUIController banco;

    public TelaTransacoes(BancoGUIController banco) {
        super("Transações Bancárias");

        this.banco = banco;

        configurarAhTela();

        criarPainelPrincipal();

        configuraLinhaUmDaJanela();

        configuraLinhaDoisDaJanela();

        criarPainelDeAbas();

        criarAbas();

        criaObjetosAba1();

        criaObjetosAba2();

        criaObjetosAba3();

        adicionarFuncionalidadeAoBotaoSacar();

        adicionarFuncionalidadeAoBotaoDepositar();

        adicionarFuncionalidadeAoBotaoConsultarSaldo();

    }

    private void adicionarFuncionalidadeAoBotaoConsultarSaldo() {
        consultarSaldoButton.addActionListener(e -> {
            // Validar os dados
            String idConta = numeroContaTextField.getText();
            TipoDeContaEnum tipoConta = (TipoDeContaEnum) tipoContaComboBox.getSelectedItem();
            Conta conta = banco.obterContaPorIdETipo(Integer.parseInt(idConta), tipoConta);
            double saldo = 0.00;

            if ((idConta.isBlank() || idConta.isEmpty()) ){
                JOptionPane
                        .showMessageDialog(
                                null,
                                "Erro, preencha corretamento o número da conta!"
                        );
            } else {
                // Efetuar saque
                saldo = conta.consultarSaldo();
                JOptionPane
                        .showMessageDialog(
                                null,
                                String.format("O saldo atual da conta é de R$ %.2f", saldo)
                        );
            }
        });
    }

    private void adicionarFuncionalidadeAoBotaoDepositar() {
        depositarButton.addActionListener(e -> {
            // Validar os dados
            String idConta = numeroContaTextField.getText();
            TipoDeContaEnum tipoConta = (TipoDeContaEnum) tipoContaComboBox.getSelectedItem();
            String valorDeposito = valorDepositoTextField.getText();
            Conta conta = banco.obterContaPorIdETipo(Integer.parseInt(idConta), tipoConta);

            if ((idConta.isBlank() || idConta.isEmpty()) ){
                JOptionPane
                        .showMessageDialog(
                                null,
                                "Erro, preencha corretamento o número da conta!"
                        );
            } else {
                if (valorDeposito.isBlank() || valorDeposito.isEmpty())
                    JOptionPane
                            .showMessageDialog(
                                    null, "É necessário preencher o valor a depositar!");
                else {
                    // Efetuar saque
                    double valorDepositado = 0;
                    try {
                        valorDepositado = conta.depositar(Double.parseDouble(valorDeposito));
                        JOptionPane.showMessageDialog(null, "Depósito efetuado com sucesso");
                        if (tipoConta == TipoDeContaEnum.CONTA_POUPANCA) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    String.format("Creditado rendimento de R$ %.2f",
                                            valorDepositado - Double.parseDouble(valorDeposito)));
                        }
                    } catch (Exception ex){
                        JOptionPane
                                .showMessageDialog(
                                        null, "Não foi possível depositar. " + ex.getMessage());
                    }
                }
            }
        });
    }

    private void adicionarFuncionalidadeAoBotaoSacar() {
        sacarButton.addActionListener(e -> {
            // Validar os dados
            String idConta = numeroContaTextField.getText();
            TipoDeContaEnum tipoConta = (TipoDeContaEnum) tipoContaComboBox.getSelectedItem();
            String valorSaque = valorSaqueTextField.getText();
            Conta conta = banco.obterContaPorIdETipo(Integer.parseInt(idConta), tipoConta);

            if ((idConta.isBlank() || idConta.isEmpty()) ){
                JOptionPane
                        .showMessageDialog(
                                null,
                                "Erro, preencha corretamento o número da conta!"
                        );
            } else {
                if (valorSaque.isBlank() || valorSaque.isEmpty())
                    JOptionPane
                            .showMessageDialog(null, "É necessário preencher o valor a sacar!");
                else {
                    // Efetuar saque
                    double valorSacado = 0;
                    try {
                        valorSacado = conta.sacar(Double.parseDouble(valorSaque));
                        JOptionPane.showMessageDialog(null, "Saque efetuado com suceso.");
                        if (tipoConta == TipoDeContaEnum.CONTA_CORRENTE)
                            JOptionPane.showMessageDialog(
                                    null,
                                    String.format("Foi cobrado o valor de R$ %.2f",
                                            valorSacado -  Double.parseDouble(valorSaque))
                            );
                    } catch (Exception ex){
                        JOptionPane
                                .showMessageDialog(
                                        null, "Não foi possível sacar. " + ex.getMessage());
                    }
                }
            }
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
        consultaSaldoTextArea = new JTextArea();
        aba3.add(consultaSaldoTextArea);
    }

    private void criaObjetosAba2() {
        // Rótulo "Valor"
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        valorDepositoLabel = new JLabel("Valor a depositar: ");
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
        resultadoDepositarTextArea = new JTextArea();
        aba2.add(resultadoDepositarTextArea);
    }

    private void criaObjetosAba1() {
        // Rótulo "Valor"
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        valorSaqueLabel = new JLabel("Valor a sacar: ");
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
        resultadoSacarTextArea = new JTextArea();
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
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
    }

}