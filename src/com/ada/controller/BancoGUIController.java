package com.ada.controller;

import com.ada.model.entity.cliente.CNPJ;
import com.ada.model.entity.cliente.CPF;
import com.ada.model.entity.cliente.Cliente;
import com.ada.model.entity.conta.ContaCorrente;
import com.ada.model.entity.conta.ContaPoupanca;
import com.ada.model.entity.interfaces.conta.Identificador;
import com.ada.model.entity.interfaces.conta.Conta;
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
                Cliente cliente = banco.pesquisarClientePorId(id);
                ContaPoupanca conta = new ContaPoupanca(cliente);
                banco.adicionarContaNova(conta);
            }
            case TipoDeContaEnum.CONTA_CORRENTE -> {
                Cliente cliente = banco.pesquisarClientePorId(id);
                ContaCorrente conta = new ContaCorrente(cliente);
                banco.adicionarContaNova(conta);
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

    public List<com.ada.model.entity.interfaces.conta.Conta> obterContas(){
        return banco.obterListaDeContas();
    }

    public Conta obterContaPorId(String id){
        for (Conta conta : obterContas()){
            if (conta.getNumero().equals(id)){
                return conta;
            }
        }
        throw new RuntimeException("Conta não localizada!");
    }

    public Conta obterContaPorIdETipo(String id, TipoDeContaEnum tipo){
        if (tipo == TipoDeContaEnum.CONTA_POUPANCA)
            return obterContaPorId(id);
        if (tipo == TipoDeContaEnum.CONTA_CORRENTE)
            return obterContaPorId(id);
        throw new RuntimeException("Conta não localizada");
    }
}
