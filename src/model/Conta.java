package model;

public abstract class Conta {

    protected final double TAXADESAQUE = 1.002;
    protected double Saldo;
    protected int NumeroConta;
    protected String NomeCliente;

    protected Conta(){}

    public Conta(int numeroConta, String nomeCliente){
        this.NumeroConta = numeroConta;
        this.NomeCliente = nomeCliente;
    }

    public abstract void depositar(double valor);

    public abstract void sacar(double valor)throws Exception;

    public abstract void consultarSaldo();

    public int getNumeroConta(){
        return this.NumeroConta;
    }

    public String getNomeCliente(){
        return this.NomeCliente;
    }
}
