package controller;

import model.ContaCorrente;
import model.ContaPoupanca;
import view.Menu;

import java.util.Scanner;

public class BancoController {

    private final ContaCorrente[] contasCorrente = new ContaCorrente[10];
    private final ContaPoupanca[] contasPoupanca = new ContaPoupanca[10];
    private final Menu menu = new Menu();

    private final Scanner ENTRADA = new Scanner(System.in);

    public void Inicio(){
        int opcao;
        int quebrar = 0;
        this.menu.ExibirMenuInicial();
        while(true){
            opcao = ENTRADA.nextInt();
            while ((opcao > 3 || opcao < 1) && quebrar < 3){
                System.out.print("Opção inválida! Escolha uma opção: ");
                quebrar++;
                opcao = ENTRADA.nextInt();
            }

            // Todo Criar função de criação de criação de conta.
            switch (opcao){
                case 1: menu.ExibirTelaDeAberturaDeConta(); break; // Mostrar tela de abertura de conta;
                case 2: break; // Mostrar tela de transações;
                case 3:
                    System.out.println("Obrigado por utilizar a Caixa Econômica Federal.");
                    System.exit(0);
            }
        }
    }
}
