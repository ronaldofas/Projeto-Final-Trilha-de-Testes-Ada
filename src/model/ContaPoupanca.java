package model;


public class ContaPoupanca extends Conta{

    private final double TAXARENDIMENTO = 0.005;

    public ContaPoupanca(int id, Cliente cliente) {

        super(id, cliente, true);
    }

    public void depositar(double valor){
        super.depositar(valor);
        this.adicionarRendimento(valor, TAXARENDIMENTO);
    }
}
