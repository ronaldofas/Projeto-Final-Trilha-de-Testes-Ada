package model;

public class ContaPoupanca extends Conta{

    protected ContaPoupanca(){}

    public ContaPoupanca(int numeroConta, String nomeCliente){
        super(numeroConta, nomeCliente);
    }

    public void depositar(double valor){
        double taxaderendimento = 0.0005;
        this.Saldo += valor;
        double rendimento = ArredondamentoDouble.arredondar((this.Saldo * taxaderendimento));
        this.Saldo += rendimento;
        System.out.printf("Você depositou o valor de R$ %.2f e recebeu o rendimento de R$ %.2f\n",
                valor, rendimento
        );
    }

    public void sacar(double valor) throws Exception {
        if (this.Saldo > valor){
            double saqueComTaxa = ArredondamentoDouble.arredondar((valor * TAXADESAQUE));
            this.Saldo -= saqueComTaxa;
            System.out.printf(
                    "Saque efetuado com sucesso! Taxa de saque cobrada no valor de R$ %.2f\n",
                    (saqueComTaxa - valor));
        } else {
            throw new Exception("Valor de saque maior que o saldo.");
        }
    }

    public void consultarSaldo(){
        System.out.println("O saldo da conta é R$ " + this.Saldo);
    }

}
