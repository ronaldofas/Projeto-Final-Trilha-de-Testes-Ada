package com.ada.model.entity.conta;

import com.ada.model.entity.cliente.Cliente;
import com.ada.model.entity.interfaces.conta.Conta;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContaInvestimento implements Conta {
    private static int numero;

    private boolean status;
    private final Cliente cliente;
    private final NumeroConta numeroConta;
    private double saldo;
    private final LocalDateTime dataAtualizacao;
    private final List<Transacao> transacoes;

    public ContaInvestimento(Cliente cliente) {
        numero++;
        numeroConta = new NumeroConta("CI" + String.format("%04d", numero));
        this.cliente = cliente;
        saldo = 0;
        status = true;
        dataAtualizacao = LocalDateTime.now();
        transacoes = new ArrayList<>();
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

    @Override
    public boolean isStatus() {
        return status;
    }

    @Override
    public void depositar(double valor) {
        validarValorTransacao(valor, false);
        saldo += valor;
    }

    @Override
    public void sacar(double valor) {
        validarValorTransacao(valor, true);
        saldo -= valor;
    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
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
}
