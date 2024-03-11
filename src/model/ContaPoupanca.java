package model;

public class ContaPoupanca extends Conta{

    public void aplicarRendimento(){
        if (this.Saldo > 0){
            this.Saldo = this.Saldo * 1.0002;
        }

        System.out.println("O valor do saldo atual é de R$ " + this.Saldo);
    }
}
