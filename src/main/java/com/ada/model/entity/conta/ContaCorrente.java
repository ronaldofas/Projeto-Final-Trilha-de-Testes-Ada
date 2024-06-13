package com.ada.model.entity.conta;

import com.ada.model.entity.cliente.Cliente;
import com.ada.model.entity.interfaces.conta.ContaTarifavel;
import com.ada.model.entity.interfaces.conta.Identificador;
import com.ada.model.entity.interfaces.conta.Conta;
import com.ada.model.helpers.enums.Classificacao;
import com.ada.model.helpers.services.DateTimeHelpers;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContaCorrente implements ContaTarifavel, Serializable {

    private boolean status;
    private final Cliente cliente;
    private final Identificador<String> numeroConta;
    private double saldo;
    private final LocalDateTime dataAtualizacao;
    private final List<Transacao> transacoes;

    protected static final double TAXADESAQUEPF = 0.002;
    protected static final double TAXADESAQUEPJ = 0.005;

    public ContaCorrente(final Cliente cliente) {
        this.cliente = cliente;
        this.numeroConta = new NumeroConta();
        status = true;
        saldo = 0;
        dataAtualizacao = LocalDateTime.now();
        transacoes = new ArrayList<>();
    }

    @Override
    public Cliente getCliente() {
        return cliente;
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public String getNumero() {
        return numeroConta.getValor();
    }

    public void sacar(final double valor) {
        validarValorTransacao(valor, true);
        saldo -= valor;
    }

    @Override
    public void depositar(final double valor) {
        validarValorTransacao(valor, false);
        saldo += valor;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    @Override
    public void transferir(final double valor, final Conta conta) {
        validarValorTransacao(valor, true);
        saldo -= valor;
        conta.depositar(valor);
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

    @Override
    public double calcularTarifaDeSaque(final double valor) {
        double resultado = 0;
        if (this.getCliente().getClassificacao() == Classificacao.PF)
            resultado = valor * TAXADESAQUEPF;
        if (this.getCliente().getClassificacao() == Classificacao.PJ)
            resultado = valor * TAXADESAQUEPJ;

        return resultado;
    }

    private void validarValorTransacao(final double valor, final boolean saque) {
        if (valor <= 0 ){
            throw new IllegalArgumentException("Valor deve ser maior que 0");
        }

        if (valor > saldo && saque){
            throw new IllegalArgumentException("Valor maior que o saldo disponível!");
        }
    }

    @Override
    public String toString() {
        return "ContaCorrente{" +
                numeroConta.toString() +
                ", dataAtualizacao=" + DateTimeHelpers.obterDataNoPadraoBrasileiro(dataAtualizacao) +
                ", status=" + (status ? "Ativa" : "Inativa") +
                '}';
    }
}
