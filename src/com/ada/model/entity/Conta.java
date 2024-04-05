package com.ada.model.entity;


import com.ada.helpers.services.ArredondamentoDouble;
import java.time.LocalDate;

public abstract class Conta {

    protected int id;
    protected double saldo;
    protected LocalDate dataAtualizacao;
    protected boolean status;
    protected Cliente cliente;

    public Conta(int id, Cliente cliente) {
        this.id = id;
        this.saldo = 0;
        this.dataAtualizacao = LocalDate.now();
        this.status = true;
        this.cliente = cliente;
    }

    public int getId() {
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

    public void sacar(double valor)throws Exception {
        if (this.saldo > valor){
            this.saldo -= valor;
            System.out.println("Saque efetuado com sucesso!");
        } else {
            throw new Exception("Valor de saque maior que o saldo.");
        }
    }

    protected void cobrarTarifa(double valorDoSaque, double taxa){
        double tarifa = ArredondamentoDouble.arredondar(valorDoSaque * taxa);
        this.saldo -= tarifa;
        System.out.printf("Descontada tarifa de saque no valor de R$ %.2f\n", tarifa);
    }

    public void depositar(double valor){
        this.saldo += valor;
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
        this.status = !this.status;
    }
}
