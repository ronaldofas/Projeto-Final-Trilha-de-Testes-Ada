package model;

public class ContaCorrente extends Conta{

    protected ContaCorrente(){}

    public ContaCorrente(int numeroConta, String nomeCliente){
        super(numeroConta, nomeCliente);
    }

    public void depositar(double valor){
        this.Saldo += valor;
        System.out.printf("Você depositou o valor de R$ %.2f.\n", valor);
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
        System.out.printf("O saldo da conta é R$ %.2f\n", this.Saldo);
    }
}
