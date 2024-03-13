package model;

public class ContaPoupanca extends Conta{

    public void depositar(double valor){
        double taxaderendimento = 1.0005;
        double rendimento = this.Saldo * taxaderendimento;
        this.Saldo += valor;
        this.Saldo += rendimento;
    }

    public void Sacar(double valor) throws Exception {
        if (this.Saldo > valor){
            double saqueComTaxa = ArredondamentoDouble.arredondar((valor * TAXADESAQUE));
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
