package com.ada.model.entity.conta;

import com.ada.model.entity.cliente.Cliente;
import com.ada.model.entity.interfaces.conta.ContaTarifavel;
import com.ada.model.entity.interfaces.conta.Identificador;
import com.ada.model.entity.interfaces.conta.Conta;
import com.ada.model.helpers.enums.Classificacao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ContaCorrente implements ContaTarifavel {

    private static int numero = 0;
    private boolean status;
    private Cliente cliente;
    private Identificador<String> numeroConta;
    private double saldo;
    private LocalDateTime dataAtualizacao;
    private List<Transacao> transacoes;

    protected final double TAXADESAQUEPF = 0.002;
    protected final double TAXADESAQUEPJ = 0.005;

    public ContaCorrente(Cliente cliente) {
        numero++;
        this.cliente = cliente;
        this.numeroConta = new NumeroConta("CC" + String.format("%04d", numero));
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

    public void sacar(double valor) {
        validarValorTransacao(valor, true);
        saldo -= valor;
        System.out.printf("Saque no valor de R$ %.2f efetuado\n", valor);
    }

    @Override
    public void depositar(double valor) {
        validarValorTransacao(valor, false);
        saldo += valor;
        System.out.printf("Depósito no valor de R$ %.2f efetuado\n", valor);
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    @Override
    public void transferir(double valor, Conta conta) {
        validarValorTransacao(valor, true);
        saldo -= valor;
        conta.depositar(valor);
        System.out.printf(
                "Valor de R$ %.2f transferido da conta %S para a contas %S\n", valor,
                getNumero(), conta.getNumero());
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

    @Override
    public double calcularTarifaDeSaque(double valor) {
        double resultado = 0;
        if (this.getCliente().getClassificacao() == Classificacao.PF)
            resultado = valor * TAXADESAQUEPF;
        if (this.getCliente().getClassificacao() == Classificacao.PJ)
            resultado = valor * TAXADESAQUEPJ;

        return resultado;
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
