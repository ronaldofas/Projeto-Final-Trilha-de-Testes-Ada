package model;

public class ContaCorrente extends Conta{

    public void depositar(double valor){
        this.Saldo += valor;
    }

    public void Sacar(double valor) throws Exception {
        if (this.Saldo > valor){
            double saqueComTaxa = valor * TAXADESAQUE;
            this.Saldo -= saqueComTaxa;
            System.out.println(
                    "Saque efetuado com sucesso! Taxa de saque cobrada no valor de R$ "
                            + (saqueComTaxa - valor));
        } else {
            throw new Exception("Valor de saque maior que o saldo.");
        }
    }

    public void ConsultarSaldo(){
        System.out.println("O saldo da conta é R$ " + this.Saldo);
    }
}
