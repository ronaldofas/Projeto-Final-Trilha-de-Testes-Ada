package com.ada.view.gui;

import com.ada.controller.ClienteController;
import com.ada.model.entity.cliente.IdentificadorCNPJ;
import com.ada.model.entity.cliente.IdentificadorCPF;
import com.ada.model.entity.interfaces.conta.Identificador;
import com.ada.model.helpers.enums.Classificacao;
import com.ada.model.entity.cliente.Cliente;
import com.ada.view.gui.model.ClienteTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ClienteTable extends JFrame {

    private JPanel painelPrincipal;
    private JTable tabelaClientes;
    private JScrollPane scrollPane;
    private JButton btnAdicionar;
    private JTextField txtNome;
    private JTextField txtCpfCnpj;
    private JComboBox<Classificacao> cbTipoCliente;

    private List<Cliente> clientes;
    private final ClienteController cliente;
    private ClienteTableModel clienteTableModel;

    public ClienteTable(final ClienteController cliente) {
        // Configurações da Janela
        setTitle("Clientes");
        setSize(GuiParameters.getTabelaLargura(), GuiParameters.getTabelaAltura());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.cliente = cliente;
        clientes = this.cliente.listarClientes();

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
        painelPrincipal.add(cbTipoCliente, gbc);
    }

    private void organizaSegundaLinhaComCampos(final GridBagConstraints gbc) {
        gbc.gridy = GuiParameters.getLinhaDoisTela(); // Segunda linha

        gbc.gridwidth = 1;
        painelPrincipal.add(new JLabel("Cpf/Cnpj:"), gbc);

        gbc.gridx = GuiParameters.getColunaDoisTela();
        painelPrincipal.add(txtCpfCnpj, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = GuiParameters.getColunaTresTela();
        painelPrincipal.add(new JLabel("Nome:"), gbc);

        gbc.gridx = GuiParameters.getColunaQuatroTela();
        painelPrincipal.add(txtNome, gbc);
    }

    private void organizaPrimeiraLinhaComTabela(final GridBagConstraints gbc) {
        gbc.gridy = GuiParameters.getLinhaUmTela(); // Linha zero - primeira linha
        gbc.gridx = GuiParameters.getColunaUmTela(); // Coluna zero
        gbc.gridwidth = 3;
        painelPrincipal.add(scrollPane, gbc);
    }

    private void criarCampoDeEntrada() {
        // Criar os Campos de Entrada
        txtNome = new JTextField(GuiParameters.getTamanhoTxtNome());
        txtCpfCnpj = new JTextField(GuiParameters.getTamanhoTxtCpfCnpj());
        cbTipoCliente = new JComboBox<>(Classificacao.values());
    }

    private void criarBotaoAdicionar() {
        // Criar o Botão "Adicionar"
        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(e -> {
            // Validar os dados
            String id = txtCpfCnpj.getText();
            Identificador<String> identificador = null;
            Classificacao tipoCliente = (Classificacao) cbTipoCliente.getSelectedItem();
            try {
                identificador = getIdentificador(tipoCliente, id);
            } catch (IllegalArgumentException er){
                JOptionPane.showMessageDialog(null, er.getMessage());
            }
            String nome = txtNome.getText();

            if ((id.isBlank() || id.isEmpty()) || (nome.isEmpty() || nome.isBlank())){
                JOptionPane
                        .showMessageDialog(
                                null,
                                "Erro, preencha corretamento o documento e o nome. Conta não aberta!"
                        );
            } else {
                // Adicionar o novo Cliente à lista
                if (identificador != null){
                    Cliente clienteAhAdicionar = new Cliente(identificador, tipoCliente, nome);
                    cliente.cadastrarCliente(clienteAhAdicionar);
                }
                txtCpfCnpj.setText("");
                txtNome.setText("");
                txtCpfCnpj.requestFocus();

                // Atualizar a tabela
                clientes = cliente.listarClientes();
                atualizarClientesDaTabela();
                ((ClienteTableModel) tabelaClientes.getModel()).fireTableDataChanged();
            }
        });
    }

    private static Identificador<String> getIdentificador(final Classificacao tipoCliente, final String id) {
        if (tipoCliente == Classificacao.PF)
            return new IdentificadorCPF(id);
        if (tipoCliente == Classificacao.PJ)
            return new IdentificadorCNPJ(id);
        throw new IllegalArgumentException("Tipo de cliente inválido ou não reconhecido!");
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