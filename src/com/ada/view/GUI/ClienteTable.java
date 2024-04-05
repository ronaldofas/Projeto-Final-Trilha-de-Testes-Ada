package com.ada.view.GUI;

import com.ada.controller.BancoGUIController;
import com.ada.helpers.enums.TipoClienteEnum;
import com.ada.model.entity.Cliente;
import com.ada.view.GUI.model.ClienteTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClienteTable extends JFrame {

    private JPanel painelPrincipal;
    private JTable tabelaClientes;
    private JScrollPane scrollPane;
    private JButton btnAdicionar;
    private JTextField txtNome;
    private JTextField txtCpfCnpj;
    private JComboBox<TipoClienteEnum> cbTipoCliente;

    private List<Cliente> clientes;
    BancoGUIController banco;
    private ClienteTableModel clienteTableModel;

    public ClienteTable() {
        // Configurações da Janela
        setTitle("Clientes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        banco = new BancoGUIController();
        clientes = banco.obterClientes();

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

    private void organizarComponentesNoGrid() {
        // Organizar os Componentes no Painel
        GridBagConstraints gbc = new GridBagConstraints();

        organizaPrimeiraLinhaComTabela(gbc);

        organizaSegundaLinhaComCampos(gbc);

        organizaTerceiraLinhaComCampos(gbc);

        organizaQuartaLinhaComBotoes(gbc);


    }

    private void organizaQuartaLinhaComBotoes(GridBagConstraints gbc) {
        gbc.gridy = 3; // Quarta linha
        gbc.gridx = 0;
        painelPrincipal.add(btnAdicionar, gbc);
    }

    private void organizaTerceiraLinhaComCampos(GridBagConstraints gbc) {
        gbc.gridy = 2; // Teceira Linha
        gbc.gridx = 0;
        painelPrincipal.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        painelPrincipal.add(cbTipoCliente, gbc);
    }

    private void organizaSegundaLinhaComCampos(GridBagConstraints gbc) {
        gbc.gridy = 1; // Segunda linha

        gbc.gridwidth = 1;
        painelPrincipal.add(new JLabel("Cpf/Cnpj:"), gbc);

        gbc.gridx = 1;
        painelPrincipal.add(txtCpfCnpj, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 2;
        painelPrincipal.add(new JLabel("Nome:"), gbc);

        gbc.gridx = 3;
        painelPrincipal.add(txtNome, gbc);
    }

    private void organizaPrimeiraLinhaComTabela(GridBagConstraints gbc) {
        gbc.gridy = 0; // Linha zero - primeira linha
        gbc.gridx = 0; // Coluna zero
        gbc.gridwidth = 3;
        painelPrincipal.add(scrollPane, gbc);
    }

    private void criarCampoDeEntrada() {
        // Criar os Campos de Entrada
        txtNome = new JTextField(20);
        txtCpfCnpj = new JTextField(14);
        cbTipoCliente = new JComboBox<>(TipoClienteEnum.values());
    }

    private void criarBotaoAdicionar() {
        // Criar o Botão "Adicionar"
        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(e -> {
            // Validar os dados
            String id = txtCpfCnpj.getText();
            String nome = txtNome.getText();
            TipoClienteEnum tipoCliente = (TipoClienteEnum) cbTipoCliente.getSelectedItem();

            // Adicionar o novo Cliente à lista
            banco.adicionarCliente(id, tipoCliente.getId(), nome);

            // Atualizar a tabela
            clientes = banco.obterClientes();
            atualizarClientesDaTabela();
            ((ClienteTableModel) tabelaClientes.getModel()).fireTableDataChanged();
        });
    }

    private void criarScrollPane() {
        // Criar o Scroll Pane
        scrollPane = new JScrollPane(tabelaClientes);
    }

    private void criarTabela() {
        // Criar a Tabela
        clienteTableModel = new ClienteTableModel(clientes);
        tabelaClientes = new JTable();
        tabelaClientes.setModel(clienteTableModel);
    }

    private void criarPainelPrincipal() {
        // Criar o Painel Principal
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new GridBagLayout());
    }

    private void atualizarClientesDaTabela(){
        clienteTableModel.atualizarClientes(this.clientes);
    }
}