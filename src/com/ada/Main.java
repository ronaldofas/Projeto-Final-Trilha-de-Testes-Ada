package com.ada;

import com.ada.controller.BancoController;
import com.ada.infra.repositorios.inMemory.ClienteRepositorio;
import com.ada.infra.repositorios.inMemory.ContaRepositorio;
import com.ada.view.CLI.Menu;
import com.ada.view.GUI.TelaInicial;

public class Main {
    public static void main(String[] args) {
        // Crie uma aplicação que simule uma app bancária.
        // Além do produto conta-corrente, os clientes podem abrir uma conta-poupança.
        //      A conta poupança tem rendimento de 0.5% no momento do depósito.
        //      Para cada saque existe uma taxa de 0.2%.
        // Crie as funcionalidades: sacar, depositar, consultar saldo.

        // Cria o Menu
        boolean gui = false;

        if (gui)
            new TelaInicial().setVisible(true);
        else
            new Menu().inicio();
    }
}