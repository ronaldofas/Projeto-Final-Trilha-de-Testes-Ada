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

    public BancoController(final IContaRepositorio contaRepositorio) {
        this.contaRepositorio = contaRepositorio;
    }

    public void abrirConta(final Cliente cliente, final TipoDeContaEnum tipoConta){
        if (tipoConta == TipoDeContaEnum.CONTA_POUPANCA)
            contaRepositorio.salvar(new ContaPoupanca(cliente));
        if (tipoConta == TipoDeContaEnum.CONTA_CORRENTE)
            contaRepositorio.salvar(new ContaCorrente(cliente));
        if (tipoConta == TipoDeContaEnum.CONTA_INVESTIMENTO)
            contaRepositorio.salvar(new ContaInvestimento(cliente));
    }

    public Conta buscarConta(final String id){
        return contaRepositorio.buscarPorNumero(id);
    }

    public List<Conta> buscarContas(final String id){
        return contaRepositorio.buscarContas(id);
    }

    public List<Conta> listarTodasAsContas(){
        return contaRepositorio.buscarContas();
    }

    public void depositar(final Conta conta, final double valor){
        validarValor(conta, valor, false);
        conta.depositar(valor);
        adicionarTransacaoParaClientesIguais(TipoTransacao.DEPOSITO, valor, conta);
    }

    public void investir(final ContaCorrente conta, final double valor){
        validarValor(conta, valor, true);
        Conta contaInvestimento = pesquisarContaInvestimento(conta);
        conta.transferir(valor, contaInvestimento);
        adicionarTransacaoParaClientesIguais(TipoTransacao.INVESTIMENTO, valor, conta);
        adicionarTransacaoParaClientesIguais(TipoTransacao.INVESTIMENTO, valor, contaInvestimento);
    }

    public void sacar(final Conta conta, final double valor){
        validarValor(conta, valor, true);
        conta.sacar(valor);
        if (conta.getCliente().getClassificacao() == Classificacao.PJ){
            ContaCorrente contaCorrente = (ContaCorrente) conta;
            conta.sacar(contaCorrente.calcularTarifaDeSaque(valor));
            adicionarTransacaoParaClientesIguais(TipoTransacao.TARIFA, valor, conta);
        }
        adicionarTransacaoParaClientesIguais(TipoTransacao.SAQUE, valor, conta);
    }

    public void transferir(final Conta contaOrigem, final Conta contaDestino, final double valor){
        validarValor(contaOrigem, valor, true);
        contaOrigem.transferir(valor, contaDestino);
        adicionarTransacaoParaClienteDiferentes(contaOrigem, contaDestino, valor);
    }

    private static void adicionarTransacaoParaClienteDiferentes(
            final Conta contaOrigem, final Conta contaDestino, final double valor) {
        Transacao transacaoOrigem = new Transacao(TipoTransacao.TRANSFERENCIA, valor);
        transacaoOrigem.setRemetente(contaOrigem.getCliente());
        transacaoOrigem.setDestinatario(contaDestino.getCliente());
        contaOrigem.criarTransacao(transacaoOrigem);
        contaDestino.criarTransacao(transacaoOrigem);
    }

    private static void adicionarTransacaoParaClientesIguais(
            final TipoTransacao tipoTransacao, final double valor, final Conta conta) {
        Transacao transacao = new Transacao(tipoTransacao, valor);
        transacao.setDestinatarioERemetente(conta.getCliente());
        conta.criarTransacao(transacao);
    }

    private Conta pesquisarContaInvestimento(final ContaCorrente conta) {
        Conta contaInvestimento;
        contaInvestimento = contaRepositorio.buscarContaInvestimento(conta.getCliente().getIdentificador());
        if (contaInvestimento == null){
            ContaInvestimento novaConta = new ContaInvestimento(conta.getCliente());
            contaRepositorio.salvar(novaConta);
            contaInvestimento = contaRepositorio.buscarContaInvestimento(conta.getCliente().getIdentificador());
        }
        return contaInvestimento;
    }

    private static void validarValor(final Conta conta, final double valor, final boolean saque) {
        if (valor < 0){
            throw new IllegalArgumentException("Valor deve ser maior que zero.");
        }

        if (saque && valor > conta.consultarSaldo()){
            throw new IllegalArgumentException("Valor maior que o saldo disponível.");
        }
    }
}
