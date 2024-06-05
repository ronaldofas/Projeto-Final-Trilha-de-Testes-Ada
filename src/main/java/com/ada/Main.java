package com.ada;

import com.ada.controller.BancoController;
import com.ada.controller.ClienteController;
import com.ada.infra.repositorios.memory.ClienteRepositorio;
import com.ada.infra.repositorios.memory.ContaRepositorio;
import com.ada.view.cli.Menu;
import com.ada.view.gui.TelaInicial;

public class Main {
    public static void main(String[] args) {
        // Crie uma aplicação que simule uma app bancária.
        // Além do produto conta-corrente, os clientes podem abrir uma conta-poupança.
        //      A conta poupança tem rendimento de 0.5% no momento do depósito.
        //      Para cada saque existe uma taxa de 0.2%.
        // Crie as funcionalidades: sacar, depositar, consultar saldo.

        // Cria o Menu
        boolean gui = false;
        final ClienteRepositorio clienteRepositorio = new ClienteRepositorio();
        final ContaRepositorio contaRepositorio = new ContaRepositorio();
        final BancoController banco = new BancoController(contaRepositorio);
        final ClienteController cliente = new ClienteController(clienteRepositorio);

        if (gui)
            new TelaInicial(banco, cliente).setVisible(true);
        else
            new Menu(banco, cliente).inicio();
    }
}