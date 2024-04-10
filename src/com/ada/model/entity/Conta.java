package com.ada.model.entity;


import com.ada.helpers.enums.TipoDeContaEnum;
import com.ada.helpers.services.ArredondamentoDouble;
import java.time.LocalDate;

public abstract class Conta {

    private String id;
    private double saldo;
    private LocalDate dataAtualizacao;
    private boolean status;
    private Cliente cliente;
    private TipoDeContaEnum tipoConta;

    public Conta(String id, Cliente cliente, TipoDeContaEnum tipoConta) {
        validar(id, cliente);
        this.id = id;
        this.saldo = 0;
        this.dataAtualizacao = LocalDate.now();
        this.status = true;
        this.cliente = cliente;
        this.tipoConta = tipoConta;
    }

    private void validar(String id, Cliente cliente) {
        if (id == null || id.isBlank()){
            throw new IllegalArgumentException("O ID não pode ser nulo ou vazio!");
        }
        if (cliente == null){
            throw new IllegalArgumentException("O Cliente não pode ser nulo!");
        }
        if (!cliente.isStatus()){
            throw new IllegalArgumentException("Clientes inativos não podem abrir conta!");
        }
    }

    public String getId() {
        return id;
    }

    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }

    public boolean isStatus() {
        return status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public TipoDeContaEnum getTipoConta() {
        return tipoConta;
    }

    public void sacar(double valor)throws Exception {
        if (valor <= 0){
            throw new IllegalArgumentException("O valor a sacar deve ser maior que zero");
        }
        if (this.saldo > valor){
            this.saldo -= valor;
            System.out.println("Saque efetuado com sucesso!");
            this.dataAtualizacao = LocalDate.now();
        } else {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
    }

    protected double cobrarTarifa(double valorDoSaque, double taxa){
        double tarifa = ArredondamentoDouble.arredondar(valorDoSaque * taxa);
        this.saldo -= tarifa;
        System.out.printf("Descontada tarifa de saque no valor de R$ %.2f\n", tarifa);
        return tarifa;
    }

    public void depositar(double valor){
        if (valor <= 0){
            throw new IllegalArgumentException("O valor a depositar deve ser maior que zero");
        }
        this.saldo += valor;
        this.dataAtualizacao = LocalDate.now();
        System.out.printf("Você depositou o valor de R$ %.2f\n", valor);
    }

    protected void adicionarRendimento(double valorDoSaque, double taxaDeRendimento) {
        double rendimento = ArredondamentoDouble.arredondar((valorDoSaque * taxaDeRendimento));
        this.saldo += rendimento;
        System.out.printf("Você recebeu o rendimento de R$ %.2f\n", rendimento);
    }

    public double consultarSaldo(){
        System.out.printf("O saldo atual é de R$ %.2f\n", this.saldo);
        return this.saldo;
    }

    public void alterarStatus(){
        if (this.saldo > 0){
            throw new IllegalArgumentException(
                    "A conta não pode ser desativada com o saldo maior que zero. Realize um saque!");
        }
        this.status = !this.status;
    }
}
