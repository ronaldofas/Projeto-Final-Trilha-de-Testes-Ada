package com.ada.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private final List<Cliente> clientes;
    private final List<ContaPoupanca> contasPoupanca;
    private final List<ContaCorrente> contasCorrentes;

    public Banco() {
        clientes = new ArrayList<>();
        contasPoupanca = new ArrayList<>();
        contasCorrentes = new ArrayList<>();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public Cliente pesquisarClientePorId(String idAhPesquisar) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().contains(idAhPesquisar)) {
                System.out.println("Localizado: " + cliente);
                return  cliente;
            }
        }
        return null;
    }

    public void imprimirClientes() {
        if (!clientes.isEmpty()) {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        } else {
            System.out.println("Não existem clientes cadastrados!");
        }
    }

    public Cliente pesquisarClientePorNome(String nomeAhPesquisar) {
        for (Cliente cliente: clientes) {
            if (cliente.getNome().contains(nomeAhPesquisar)){
                System.out.println("Localizado: " + cliente);
                return cliente;
            }
        }
        return null;
    }

    public void adicionarClienteNovo(Cliente cliente){
        clientes.add(cliente);
    }

    public ContaPoupanca encontrarContaPoupancaPorId(int numeroConta) {
        for (ContaPoupanca poupanca : contasPoupanca) {
            if (poupanca != null && poupanca.getId() == numeroConta) {
                return poupanca;
            }
        }
        return null;
    }

    public ContaCorrente encontrarContaCorrentePorId(int numeroConta) {
        for (ContaCorrente corrente : contasCorrentes) {
            if (corrente.getId() == numeroConta) {
                return corrente;
            }
        }
        return null;
    }

    public void adicionarContaPoupancaNova(ContaPoupanca conta){
        contasPoupanca.add(conta);
    }

    public void adicionarContaCorrenteNova(ContaCorrente conta){
        contasCorrentes.add(conta);
    }

    public int obterNumeroDeContaPoupancaParaAbertura() {
        int numeroNovo = 0;

        for (ContaPoupanca conta : contasPoupanca){
            if (conta.getId() > numeroNovo) numeroNovo = conta.getId();
        }
        numeroNovo ++;

        return numeroNovo;
    }

    public int obterNumeroDeContaCorrenteParaAbertura() {
        int numeroNovo = 0;

        for (ContaCorrente conta : contasCorrentes){
            if (conta.getId() > numeroNovo) numeroNovo = conta.getId();
        }
        numeroNovo ++;

        return numeroNovo;
    }
}
