package com.ada.controller;


import com.ada.model.entity.cliente.Cliente;
import com.ada.model.entity.conta.ContaCorrente;
import com.ada.model.entity.conta.ContaInvestimento;
import com.ada.model.entity.conta.ContaPoupanca;
import com.ada.model.entity.conta.Transacao;
import com.ada.model.entity.interfaces.banco.IContaRepositorio;
import com.ada.model.entity.interfaces.conta.Conta;
import com.ada.model.helpers.enums.Classificacao;
import com.ada.model.helpers.enums.TipoDeContaEnum;
import com.ada.model.helpers.enums.TipoTransacao;

import java.util.List;

public class BancoController {

    private final IContaRepositorio contaRepositorio;

    public BancoController(IContaRepositorio contaRepositorio) {
        this.contaRepositorio = contaRepositorio;
    }

    public void abrirConta(Cliente cliente, TipoDeContaEnum tipoConta){
        if (tipoConta == TipoDeContaEnum.CONTA_POUPANCA)
            contaRepositorio.salvar(new ContaPoupanca(cliente));
        if (tipoConta == TipoDeContaEnum.CONTA_CORRENTE)
            contaRepositorio.salvar(new ContaCorrente(cliente));
        if (tipoConta == TipoDeContaEnum.CONTA_INVESTIMENTO)
            contaRepositorio.salvar(new ContaInvestimento(cliente));
    }

    public Conta buscarConta(String id){
        return contaRepositorio.buscarPorNumero(id);
    }

    public List<Conta> buscarContas(String id){
        return contaRepositorio.buscarContas(id);
    }

    public void depositar(Conta conta, double valor){
        validarValor(conta, valor, false);
        conta.depositar(valor);
        adicionarTransacaoParaClientesIguais(TipoTransacao.DEPOSITO, valor, conta);
    }

    public void investir(ContaCorrente conta, double valor){
        validarValor(conta, valor, true);
        Conta contaInvestimento = pesquisarContaInvestimento(conta);
        conta.transferir(valor, contaInvestimento);
        adicionarTransacaoParaClientesIguais(TipoTransacao.INVESTIMENTO, valor, conta);
        adicionarTransacaoParaClientesIguais(TipoTransacao.INVESTIMENTO, valor, contaInvestimento);
    }

    public void sacar(Conta conta, double valor){
        validarValor(conta, valor, true);
        conta.sacar(valor);
        if (conta.getCliente().getClassificacao() == Classificacao.PJ){
            ContaCorrente contaCorrente = (ContaCorrente) conta;
            conta.sacar(contaCorrente.calcularTarifaDeSaque(valor));
            adicionarTransacaoParaClientesIguais(TipoTransacao.TARIFA, valor, conta);
        }
        adicionarTransacaoParaClientesIguais(TipoTransacao.SAQUE, valor, conta);
    }

    public void transferir(Conta contaOrigem, Conta contaDestino, double valor){
        validarValor(contaOrigem, valor, true);
        contaOrigem.transferir(valor, contaDestino);
        adicionarTransacaoParaClienteDiferentes(TipoTransacao.TRANSFERENCIA, contaOrigem, contaDestino, valor);
    }

    private static void adicionarTransacaoParaClienteDiferentes(
            TipoTransacao tipo, Conta contaOrigem, Conta contaDestino, double valor) {
        Transacao transacaoOrigem = new Transacao(tipo, valor);
        transacaoOrigem.setRemetente(contaOrigem.getCliente());
        transacaoOrigem.setDestinatario(contaDestino.getCliente());
        contaOrigem.criarTransacao(transacaoOrigem);
        contaDestino.criarTransacao(transacaoOrigem);
    }

    private static void adicionarTransacaoParaClientesIguais(
            TipoTransacao tipoTransacao, double valor, Conta conta) {
        Transacao transacao = new Transacao(tipoTransacao, valor);
        transacao.setDestinatarioERemetente(conta.getCliente());
        conta.criarTransacao(transacao);
    }

    private Conta pesquisarContaInvestimento(ContaCorrente conta) {
        Conta contaInvestimento;
        contaInvestimento = contaRepositorio.buscarContaInvestimento(conta.getCliente().getIdentificador());
        if (contaInvestimento == null){
            ContaInvestimento novaConta = new ContaInvestimento(conta.getCliente());
            contaRepositorio.salvar(novaConta);
            contaInvestimento = contaRepositorio.buscarContaInvestimento(conta.getCliente().getIdentificador());
        }
        return contaInvestimento;
    }

    private static void validarValor(Conta conta, double valor, boolean saque) {
        if (valor < 0){
            throw new IllegalArgumentException("Valor deve ser maior que zero.");
        }

        if (saque && valor > conta.consultarSaldo()){
            throw new IllegalArgumentException("Valor maior que o saldo disponível.");
        }
    }
}
