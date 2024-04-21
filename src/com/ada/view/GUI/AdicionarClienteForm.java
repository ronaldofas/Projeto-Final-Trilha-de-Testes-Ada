package com.ada.view.GUI;

import com.ada.controller.BancoGUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdicionarClienteForm extends JFrame implements ActionListener {

    private final JTextField txtCpfCnpj;
    private final JComboBox<String> cbTipoPessoa;
    private final JTextField txtNome;
    private final JButton btnGravar;
    private final BancoGUIController banco;

    public AdicionarClienteForm() {
        banco = new BancoGUIController();
        // Configurações da Janela
        setTitle("Formulário");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Criar os Componentes
        txtCpfCnpj = new JTextField(20);
        cbTipoPessoa = new JComboBox<>(new String[]{"Física", "Jurídica"});
        txtNome = new JTextField(50);
        btnGravar = new JButton("Gravar");

        // Adicionar Listeners
        btnGravar.addActionListener(this);

        // Organizar os Componentes
        JPanel painel = new JPanel();
        painel.setLayout(new GridBagLayout());

        // Adicionar os Componentes ao Painel
        GridBagConstraints gbc = new GridBagConstraints();

        // === Linha 1 ===
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        painel.add(new JLabel("CPF/CNPJ:"), gbc);

        gbc.gridx = 2;
        painel.add(txtCpfCnpj, gbc);
        // === Fim Linha 1 ===

        // === Linha 2 ===
        gbc.gridy = 1;
        gbc.gridx = 0;
        painel.add(new JLabel("Tipo Pessoa:"), gbc);

        gbc.gridx = 2;
        painel.add(cbTipoPessoa, gbc);
        // === Fim Linha 2 ===

        // === Linha 3 ===
        gbc.gridx = 0;
        gbc.gridy = 2;
        painel.add(new JLabel("Nome:"), gbc);

        gbc.gridx = 2;
        painel.add(txtNome, gbc);

        gbc.gridy = 5;
        painel.add(btnGravar, gbc);

        // Adicionar o Painel à Janela
        add(painel);

        // Ajustar a Janela à Resolução da Tela
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGravar) {
            // Exemplo de Implementação da Função Gravar
            String cpfCnpj = txtCpfCnpj.getText();
            int tipoPessoa = cbTipoPessoa.getSelectedIndex();
            String nome = txtNome.getText();

            banco.adicionarCliente(cpfCnpj, tipoPessoa, nome);

            JOptionPane.showMessageDialog(null, "Dados gravados com sucesso!");
        }
    }

}
