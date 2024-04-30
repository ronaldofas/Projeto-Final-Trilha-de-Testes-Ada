package com.ada.model.entity.conta;


import com.ada.model.entity.cliente.Cliente;
import com.ada.model.entity.interfaces.conta.ContaRentavel;
import com.ada.model.entity.interfaces.conta.Identificador;
import com.ada.model.entity.interfaces.conta.Conta;
import com.ada.model.helpers.enums.Classificacao;
import com.ada.model.helpers.services.ArredondamentoDouble;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContaPoupanca implements ContaRentavel {

    private static int numero = 0;
    private final List<Transacao> transacoes;
    private final LocalDateTime dataAtualizacao;
    private double saldo;
    private final Identificador<String> numeroConta;
    private final Cliente cliente;
    private boolean status;
    private final double TAXARENDIMENTO = 0.005;

    public ContaPoupanca(Cliente cliente) {
        validarCliente(cliente);
        numero++;
        this.numeroConta = new NumeroConta("CP" + String.format("%04d", numero));
        this.cliente = cliente;
        transacoes = new ArrayList<>();
        dataAtualizacao = LocalDateTime.now();
        saldo = 0;
        status = true;
    }

    private void validarCliente(Cliente cliente) {
        if (cliente.getClassificacao() == Classificacao.PJ)
            throw new IllegalArgumentException("Somente pessoas físicas podem abrir poupança.");
    }

    @Override
    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public String getNumero() {
        return numeroConta.getValor();
    }

    @Override
    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public boolean isStatus() {
        return status;
    }

    public void depositar(double valor){
        validarValorTransacao(valor, false);
        adicionarRendimento(valor);
        saldo += valor;
        System.out.printf("Depósito no valor de R$ %.2f efetuado.\n", valor);
    }

    @Override
    public void sacar(double valor) {
        validarValorTransacao(valor, true);
        saldo -= valor;
        System.out.printf("Saque no valor de R$ %.2f efetuado\n", valor);
    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
        validarValorTransacao(valor, false);
        contaDestino.depositar(valor);
        saldo -= valor;
        System.out.printf("Transferência no valor de R$ %.2f da conta %S para a conta %S efetuada\n", valor,
                getNumero(), contaDestino.getNumero());
    }

    @Override
    public double consultarSaldo() {
        return saldo;
    }

    @Override
    public void ativarDesativar() {
        status = !status;
    }

    @Override
    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    @Override
    public void criarTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }

    private void validarValorTransacao(double valor, boolean saque) {
        if (valor <= 0 ){
            throw new IllegalArgumentException("Valor deve ser maior que 0");
        }

        if (valor > saldo && saque){
            throw new IllegalArgumentException("Valor maior que o saldo disponível!");
        }
    }

    private void adicionarRendimento(double valorDoSaque) {
        double rendimento = ArredondamentoDouble.arredondar((valorDoSaque * TAXARENDIMENTO));
        this.saldo += rendimento;
        System.out.printf("Você recebeu o rendimento de R$ %.2f\n", rendimento);
    }

    @Override
    public double calcularRendimento(double valorDoDeposito) {
        return valorDoDeposito * TAXARENDIMENTO;
    }
}
