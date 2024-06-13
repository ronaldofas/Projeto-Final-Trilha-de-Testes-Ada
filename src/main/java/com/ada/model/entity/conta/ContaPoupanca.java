package com.ada.model.entity.conta;


import com.ada.model.entity.cliente.Cliente;
import com.ada.model.entity.interfaces.conta.ContaRentavel;
import com.ada.model.entity.interfaces.conta.Identificador;
import com.ada.model.entity.interfaces.conta.Conta;
import com.ada.model.helpers.enums.Classificacao;
import com.ada.model.helpers.services.ArredondamentoDouble;
import com.ada.model.helpers.services.DateTimeHelpers;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContaPoupanca implements ContaRentavel, Serializable {

    private final List<Transacao> transacoes;
    private final LocalDateTime dataAtualizacao;
    private double saldo;
    private final Identificador<String> numeroConta;
    private final Cliente cliente;
    private boolean status;
    private static final double TAXARENDIMENTO = 0.005;

    public ContaPoupanca(final Cliente cliente) {
        validarCliente(cliente);
        this.numeroConta = new NumeroConta();
        this.cliente = cliente;
        transacoes = new ArrayList<>();
        dataAtualizacao = LocalDateTime.now();
        saldo = 0;
        status = true;
    }

    private void validarCliente(final Cliente cliente) {
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

    public void depositar(final double valor){
        validarValorTransacao(valor, false);
        adicionarRendimento(valor);
        saldo += valor;
    }

    @Override
    public void sacar(final double valor) {
        validarValorTransacao(valor, true);
        saldo -= valor;
    }

    @Override
    public void transferir(final double valor, final Conta contaDestino) {
        validarValorTransacao(valor, false);
        contaDestino.depositar(valor);
        saldo -= valor;
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
    public void criarTransacao(final Transacao transacao) {
        transacoes.add(transacao);
    }

    private void validarValorTransacao(final double valor, final boolean saque) {
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
    }

    @Override
    public double calcularRendimento(final double valorDoDeposito) {
        return valorDoDeposito * TAXARENDIMENTO;
    }

    @Override
    public String toString() {
        return "ContaPoupanca{" +
                numeroConta.toString() +
                ", dataAtualizacao=" + DateTimeHelpers.obterDataNoPadraoBrasileiro(dataAtualizacao) +
                ", status=" + (status ? "Ativa" : "Inativa") +
                '}';
    }
}
