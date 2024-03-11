package model;

public abstract class Conta {

    protected double Saldo;

    public void depositar(double valor){
        this.Saldo += valor;
    }

    public void sacar(double valor) throws Exception {
        if (this.Saldo > valor){
            this.Saldo -= valor * 1.002;
            System.out.println(
                    "Saque efetuado com sucesso! Taxa de saque cobrada no valor de R$ "
                            + (valor * 0.002));
        } else {
            throw new Exception("Valor de saque maior que o saldo.");
        }
    }

    public void consultarSaldo(){
        System.out.println("O saldo da conta é R$ " + this.Saldo);
    }
}
