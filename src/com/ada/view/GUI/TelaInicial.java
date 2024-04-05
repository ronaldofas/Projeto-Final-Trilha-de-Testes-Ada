package com.ada.view.GUI;

import com.ada.controller.BancoGUIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicial extends JFrame {

    private JPanel painelPrincipal;
    private JMenuBar menuBar;
    private JMenu menuOpcoes, menuClientes;
    private JMenuItem itemOpcao1, itemOpcao2;
    private JMenuItem itemClientes1;
    private JMenuItem itemClientes2;
    private BancoGUIController banco;

    public TelaInicial() {
        // Configurações da Janela
        setTitle("Caixa Econômica Federal");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        banco = new BancoGUIController();

        // Criar o Painel Principal
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new GridBagLayout());

        // Criar o Menu Bar
        menuBar = new JMenuBar();

        //criarMenuOpcoes();

        criarMenuClientes();

        // Adicionar o Menu "Opções" ao Menu Bar
        //menuBar.add(menuOpcoes);
        menuBar.add(menuClientes);

        // Adicionar o Menu Bar à Janela
        setJMenuBar(menuBar);

        // Adicionar o Painel Principal à Janela
        add(painelPrincipal);

        // Ajustar a Janela à Resolução da Tela
        //pack();
        setLocationRelativeTo(null);
    }

    private void criarMenuOpcoes() {
        // Criar o Menu "Opções"
        menuOpcoes = new JMenu("Opções");

        // Criar os Itens de Menu
        itemOpcao1 = new JMenuItem("Opção 1");
        itemOpcao2 = new JMenuItem("Opção 2");

        // Adicionar os Itens de Menu ao Menu "Opções"
        menuOpcoes.add(itemOpcao1);
        menuOpcoes.add(itemOpcao2);
    }

    private void criarMenuClientes(){
        menuClientes = new JMenu("Clientes");
        //itemClientes1 = new JMenuItem("Adicionar Cliente");
        itemClientes2 = new JMenuItem("Tabela de Clientes");

//        itemClientes1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new AdicionarClienteForm().setVisible(true);
//            }
//        });

        itemClientes2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClienteTable().setVisible(true);
            }
        });
        //menuClientes.add(itemClientes1);
        menuClientes.add(itemClientes2);
    }

    public static void main(String[] args) {
        new TelaInicial().setVisible(true);
    }
}

