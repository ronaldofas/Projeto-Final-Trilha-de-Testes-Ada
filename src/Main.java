import controller.BancoController;
import view.Menu;

public class Main {
    public static void main(String[] args) {
        // Crie uma aplicação que simule uma app bancária.
        // Além do produto conta-corrente, os clientes podem abrir uma conta-poupança.
        //      A conta poupança tem rendimento de 0.5% no momento do depósito.
        //      Para cada saque existe uma taxa de 0.2%.
        // Crie as funcionalidades: sacar, depositar, consultar saldo.

        // Cria o Menu
        BancoController banco = new BancoController();

        banco.Inicio();
    }
}