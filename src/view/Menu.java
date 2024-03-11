package view;

import java.io.IOException;

public class Menu {
    public void LimparTela() {
        if (System.getProperty("os.name").contains("Windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                String[] command = new String[5];
                command[0] = "clear";
                Runtime.getRuntime().exec(command);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void ExibirMenuInicial() {
        this.LimparTela();
        ImprimirCabecalho();
        System.out.println();
        System.out.println(' ' * 30 + "Operações de Conta: ");
        System.out.println();
        System.out.println(' ' * 30 + "Selecione uma opção: ");
        System.out.println();
        System.out.println();
        System.out.println(' ' * 30 + "1 - Abertura de conta");
        System.out.println(' ' * 30 + "2 - Transações de conta");
        System.out.println();
        System.out.print(' ' * 30 + "Digite a opção: ");
    }

    private static void ImprimirCabecalho() {
        System.out.println('=' * 80);
        System.out.println(' ' * 29 + "Caixa Econômica Federal" + ' ' * 28);
        System.out.println('=' * 80);
    }

    public void
}
