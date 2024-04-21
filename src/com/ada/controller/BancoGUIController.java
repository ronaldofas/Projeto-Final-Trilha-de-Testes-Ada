package com.ada.controller;

import com.ada.model.entity.cliente.CNPJ;
import com.ada.model.entity.cliente.CPF;
import com.ada.model.entity.cliente.Cliente;
import com.ada.model.entity.interfaces.cliente.Identificador;
import com.ada.model.helpers.enums.Classificacao;
import com.ada.model.helpers.enums.TipoDeContaEnum;
import com.ada.model.entity.*;

import java.util.List;

public class BancoGUIController {
    private final Banco banco = new Banco();

    public void adicionarCliente(String textoId, int tipo, String nome){
        Classificacao tipoCliente = getTipoCliente(tipo);
        Identificador<String> id = criarIdentificador(textoId, tipoCliente);
        Cliente cliente = new Cliente(id, tipoCliente, nome);
        banco.adicionarClienteNovo(cliente);
    }

    private static Identificador<String> criarIdentificador(String textoId, Classificacao tipoCliente) {
        if (tipoCliente == Classificacao.PF){
            return new CPF(textoId);
        }
        if (tipoCliente == Classificacao.PJ){
            return new CNPJ(textoId);
        }
        throw new RuntimeException("Classificação inválida, reveja!");
    }

    public void adicionarConta(String id, TipoDeContaEnum tipo) throws RuntimeException{
        switch (tipo){
            case TipoDeContaEnum.CONTA_POUPANCA -> {
                String idConta = Integer.toString(banco.obterNumeroDeContaCorrenteParaAbertura());
                Cliente cliente = banco.pesquisarClientePorId(id);
                ContaPoupanca conta = new ContaPoupanca(idConta, cliente, tipo);
                banco.adicionarContaPoupancaNova(conta);
            }
            case TipoDeContaEnum.CONTA_CORRENTE -> {
                String idConta = Integer.toString(banco.obterNumeroDeContaCorrenteParaAbertura());
                Cliente cliente = banco.pesquisarClientePorId(id);
                ContaCorrente conta = new ContaCorrente(idConta, cliente, tipo);
                banco.adicionarContaCorrenteNova(conta);
            }
            default -> throw new RuntimeException("Tipo de conta inválido!");
        }
    }

    private static Classificacao getTipoCliente(int tipo) {
        return switch (tipo) {
            case 0 -> Classificacao.PF;
            case 1 -> Classificacao.PJ;
            default -> throw new RuntimeException("Tipo de Cliente inválido!!!");
        };
    }

    public List<Cliente> obterClientes(){
        return banco.ObterListaDeClientes();
    }
    public List<ContaPoupanca> obterContasPoupanca(){
        return banco.obterListaDeContasPoupanca();
    }
    public List<ContaCorrente> obterContasCorrente(){
        return  banco.obterListaDeContasCorrentes();
    }

    public Conta obterContaPoupancaPorId (String id){
        for (Conta conta : obterContasPoupanca()){
            if (conta.getId() == id){
                return conta;
            }
        }
        throw new RuntimeException("Conta não localizada!");
    }

    public Conta obterContaCorrentePorId (String id){
        for (Conta conta : obterContasCorrente()){
            if (conta.getId() == id){
                return conta;
            }
        }
        throw new RuntimeException("Conta não localizada!");
    }

    public Conta obterContaPorIdETipo(String id, TipoDeContaEnum tipo){
        if (tipo == TipoDeContaEnum.CONTA_POUPANCA)
            return obterContaPoupancaPorId(id);
        if (tipo == TipoDeContaEnum.CONTA_CORRENTE)
            return obterContaCorrentePorId(id);
        throw new RuntimeException("Conta não localizada");
    }
}
