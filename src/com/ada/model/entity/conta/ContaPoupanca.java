package com.ada.model.entity.conta;


import com.ada.model.entity.cliente.Cliente;
import com.ada.model.entity.interfaces.conta.Conta;
import com.ada.model.entity.interfaces.conta.Identificador;
import com.ada.model.helpers.enums.TipoTransacao;
import com.ada.model.helpers.services.ArredondamentoDouble;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContaPoupanca implements Conta {

    private List<Transacao> transacoes;
    private LocalDateTime dataAtualizacao;
    private double saldo;
    private Identificador<String> numeroConta;
    private Cliente cliente;
    private boolean status;
    private final double TAXARENDIMENTO = 0.005;

    public ContaPoupanca(Identificador<String> numeroConta, Cliente cliente) {
        this.numeroConta = numeroConta;
        this.cliente = cliente;
        transacoes = new ArrayList<>();
        dataAtualizacao = LocalDateTime.now();
        saldo = 0;
        status = true;
    }

    @Override
    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public String getNumero() {
        return numeroConta.getValor();
    }

    public void depositar(double valor){
        validarValorTransacao(valor);
        adicionarRendimento(valor);
        gravarTransacao(TipoTransacao.DEPOSITO, valor, "Deposito efetuado", cliente);
        System.out.printf("Depósito no valor de R$ %.2f efetuado.", valor);;
    }

    @Override
    public void sacar(double valor) {
        validarValorTransacao(valor);
        gravarTransacao(TipoTransacao.SAQUE, valor, "SAQUE EFETUADO", cliente);
        saldo -= valor;
        System.out.printf("Saque no valor de R$ %.2f efetuado", valor);
    }

    @Override
    public void transferir(double valor, Conta conta) {
        validarValorTransacao(valor);
        gravarTransacao(TipoTransacao.TRANSFERENCIA, valor, "Valor transferido", conta.getCliente());
        saldo -= valor;
        System.out.printf("Transferência no valor de R$ %.2f efetuada", valor);
    }

    @Override
    public double consultarSaldo() {
        return saldo;
    }

    private static void validarValorTransacao(double valor) {
        if (valor <= 0 ){
            throw new IllegalArgumentException("Valor deve ser maior que 0");
        }
    }

    private void adicionarRendimento(double valorDoSaque) {
        double rendimento = ArredondamentoDouble.arredondar((valorDoSaque * TAXARENDIMENTO));
        this.saldo += rendimento;
        gravarTransacao(TipoTransacao.DEPOSITO, rendimento, "Rendimento sobre depósito", cliente);
        System.out.printf("Você recebeu o rendimento de R$ %.2f\n", rendimento);
    }

    private void gravarTransacao(TipoTransacao tipo, double valor, String observacao, Cliente cliente) {
        Transacao transacao =
                new Transacao(tipo, valor, observacao, cliente);
        this.transacoes.add(transacao);
    }
}
