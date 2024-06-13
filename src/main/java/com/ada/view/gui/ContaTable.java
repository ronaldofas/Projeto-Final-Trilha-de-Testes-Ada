package com.ada.view.gui;

import com.ada.controller.BancoController;
import com.ada.controller.ClienteController;
import com.ada.model.entity.cliente.Cliente;
import com.ada.model.entity.interfaces.conta.Conta;
import com.ada.model.helpers.enums.TipoDeContaEnum;
import com.ada.view.gui.model.ContaTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ContaTable extends JFrame {

    private JPanel painelPrincipal;
    private JTable tabelaContas;
    private JScrollPane scrollPane;
    private JButton btnAdicionar;
    private JTextField txtCpfCnpj;
    private JComboBox<TipoDeContaEnum> cbTipoConta;

    private List<Conta> contas;
    private final BancoController banco;
    private final ClienteController cliente;
    private ContaTableModel contaTableModel;

    public ContaTable(final BancoController banco, final ClienteController cliente) {
        // Configurações da Janela
        setTitle("Contas");
        setSize(GuiParameters.getTabelaLargura(), GuiParameters.getTabelaAltura());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.banco = banco;
        this.cliente = cliente;
        contas = new ArrayList<>();

        obterContas();

        criarPainelPrincipal();

        criarTabela();

        criarScrollPane();

        criarBotaoAdicionar();

        criarCampoDeEntrada();

        organizarComponentesNoGrid();

        // Adicionar o Painel à Janela
        add(painelPrincipal);

        // Ajustar a Janela à Resolução da Tela
        pack();
        setLocationRelativeTo(null);
    }

    private void obterContas() {
        this.contas = this.banco.listarTodasAsContas();
    }

    private void organizarComponentesNoGrid() {
        // Organizar os Componentes no Painel
        final GridBagConstraints gbc = new GridBagConstraints();

        organizaPrimeiraLinhaComTabela(gbc);

        organizaSegundaLinhaComCampos(gbc);

        organizaTerceiraLinhaComCampos(gbc);

        organizaQuartaLinhaComBotoes(gbc);


    }

    private void organizaQuartaLinhaComBotoes(final GridBagConstraints gbc) {
        gbc.gridy = GuiParameters.getLinhaQuatroTela(); // Quarta linha
        gbc.gridx = GuiParameters.getColunaUmTela();
        painelPrincipal.add(btnAdicionar, gbc);
    }

    private void organizaTerceiraLinhaComCampos(final GridBagConstraints gbc) {
        gbc.gridy = GuiParameters.getLinhaTresTela(); // Teceira Linha
        gbc.gridx = GuiParameters.getColunaUmTela();
        painelPrincipal.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = GuiParameters.getColunaDoisTela();
        painelPrincipal.add(cbTipoConta, gbc);
    }

    private void organizaSegundaLinhaComCampos(final GridBagConstraints gbc) {
        gbc.gridy = GuiParameters.getLinhaDoisTela(); // Segunda linha

        gbc.gridwidth = 1;
        painelPrincipal.add(new JLabel("Cpf/Cnpj:"), gbc);

        gbc.gridx = GuiParameters.getColunaDoisTela();
        painelPrincipal.add(txtCpfCnpj, gbc);

    }

    private void organizaPrimeiraLinhaComTabela(final GridBagConstraints gbc) {
        gbc.gridy = GuiParameters.getLinhaUmTela(); // Linha zero - primeira linha
        gbc.gridx = GuiParameters.getColunaUmTela(); // Coluna zero
        gbc.gridwidth = 3;
        painelPrincipal.add(scrollPane, gbc);
    }

    private void criarCampoDeEntrada() {
        // Criar os Campos de Entrada
        txtCpfCnpj = new JTextField(14);
        cbTipoConta = new JComboBox<>(TipoDeContaEnum.values());
    }

    private void criarBotaoAdicionar() {
        // Criar o Botão "Adicionar"
        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(e -> {
            // Validar os dados
            String id = txtCpfCnpj.getText();
            TipoDeContaEnum tipoConta = (TipoDeContaEnum) cbTipoConta.getSelectedItem();
            Cliente clienteAhAdicionar = cliente.buscarClientePorIdentificador(id);
            if ((id.isBlank() || id.isEmpty()) ){
                JOptionPane
                        .showMessageDialog(
                                null,
                                "Erro, preencha corretamento o documento e o nome. Conta não aberta!"
                        );
            } else {
                // Adicionar o novo Cliente à lista
                if(tipoConta != null) {
                    try {
                        this.banco.abrirConta(clienteAhAdicionar, tipoConta);
                        txtCpfCnpj.setText("");
                        txtCpfCnpj.requestFocus();
                    } catch (RuntimeException ex){
                        JOptionPane.showMessageDialog(
                                null, "Erro ao abrir conta:\n" + ex.getMessage());
                    }
                } else throw new IllegalArgumentException("Tipo de conta inválido ou não preenchido!");

                // Atualizar a tabela
                obterContas();
                atualizarContasDaTabela();
                ((ContaTableModel) tabelaContas.getModel()).fireTableDataChanged();
            }
        });
    }

    private void criarScrollPane() {
        // Criar o Scroll Pane
        scrollPane = new JScrollPane(tabelaContas);
    }

    private void criarTabela() {
        // Criar a Tabela
        contaTableModel = new ContaTableModel(contas);
        tabelaContas = new JTable();
        tabelaContas.setModel(contaTableModel);
    }

    private void criarPainelPrincipal() {
        // Criar o Painel Principal
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new GridBagLayout());
    }

    private void atualizarContasDaTabela(){
        contaTableModel.atualizarContas(contas);
    }
}