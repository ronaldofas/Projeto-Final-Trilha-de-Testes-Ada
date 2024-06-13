package com.ada.view.gui;

import com.ada.controller.BancoController;
import com.ada.controller.ClienteController;
import javax.swing.*;
import java.awt.*;

public class TelaInicial extends JFrame {

    private JMenu menuClientes;
    private JMenu menuContas;
    private JMenu menuTransacoes;
    final BancoController banco;
    final ClienteController cliente;

    public TelaInicial(BancoController banco, ClienteController cliente) {
        // Configurações da Janela
        setTitle("Caixa Econômica Federal");
        setSize(GuiParameters.getTelaLargura(), GuiParameters.getTelaAltura());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.banco = banco;
        this.cliente = cliente;

        // Criar o Painel Principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new GridBagLayout());

        // Criar o Menu Bar
        JMenuBar menuBar = new JMenuBar();

        criarMenuClientes();
        criarMenuContas();
        criarMenuTransacoes();

        // Adicionar o Menu "Opções" ao Menu Bar
        menuBar.add(menuClientes);
        menuBar.add(menuContas);
        menuBar.add(menuTransacoes);

        // Adicionar o Menu Bar à Janela
        setJMenuBar(menuBar);

        // Adicionar o Painel Principal à Janela
        add(painelPrincipal);

        // Ajustar a Janela à Resolução da Tela
        setLocationRelativeTo(null);
    }

    private void criarMenuClientes(){
        menuClientes = new JMenu("Clientes");
        JMenuItem itemClientes2 = new JMenuItem("Tabela de Clientes");

        itemClientes2.addActionListener(e -> new ClienteTable(this.cliente).setVisible(true));
        menuClientes.add(itemClientes2);
    }

    private void criarMenuContas(){
        menuContas = new JMenu("Contas");
        JMenuItem itemTabelaContas = new JMenuItem("Tabela de Contas");

        itemTabelaContas.addActionListener(e -> new ContaTable(this.banco, this.cliente).setVisible(true));
        menuContas.add(itemTabelaContas);
    }

    private void criarMenuTransacoes(){
        menuTransacoes = new JMenu("Transações");
        JMenuItem itemTransacoes = new JMenuItem("Transações de Contas");

        itemTransacoes.addActionListener(e -> new TelaTransacoes(banco).setVisible(true));
        menuTransacoes.add(itemTransacoes);
    }
}

