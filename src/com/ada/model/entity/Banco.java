package com.ada.model.entity;

import com.ada.model.entity.cliente.Cliente;
import com.ada.model.entity.conta.ContaCorrente;
import com.ada.model.entity.conta.ContaPoupanca;
import com.ada.model.entity.interfaces.conta.Conta;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private final List<Cliente> clientes;
    private final List<Conta> contasPoupanca;
    private final List<ContaCorrente> contasCorrentes;

    public Banco() {
        clientes = new ArrayList<>();
        contasPoupanca = new ArrayList<>();
        contasCorrentes = new ArrayList<>();
    }

    public List<Cliente> ObterListaDeClientes() {
        return new ArrayList<>(clientes);
    }

    public List<ContaCorrente> obterListaDeContasCorrentes() {
        return new ArrayList<>(contasCorrentes);
    }

    public List<Conta> obterListaDeContasPoupanca() {
        return new ArrayList<>(contasPoupanca);
    }

    public Cliente pesquisarClientePorId(String idAhPesquisar) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdentificador().contains(idAhPesquisar)) {
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

    public Conta encontrarContaPoupancaPorId(String numeroConta) {
        for (Conta poupanca : contasPoupanca) {
            if (poupanca != null && poupanca.getNumero().equals(numeroConta)) {
                return poupanca;
            }
        }
        throw new RuntimeException("Conta não localizada");
    }

    public ContaCorrente encontrarContaCorrentePorId(String numeroConta) {
        for (ContaCorrente corrente : contasCorrentes) {
            if (corrente.getId().equals(numeroConta)) {
                return corrente;
            }
        }
        return null;
    }

    public void adicionarContaPoupancaNova(Conta conta){
        contasPoupanca.add(conta);
    }

    public void adicionarContaCorrenteNova(ContaCorrente conta){
        contasCorrentes.add(conta);
    }

    public String obterNumeroDeContaPoupancaParaAbertura() {
        int numeroNovo = 0;

        for (Conta conta : contasPoupanca){
            if (Integer.parseInt(conta.getNumero()) > numeroNovo)
                numeroNovo = Integer.parseInt(conta.getNumero());
        }
        numeroNovo ++;

        return Integer.toString(numeroNovo);
    }

    public int obterNumeroDeContaCorrenteParaAbertura() {
        int numeroNovo = 0;

        for (ContaCorrente conta : contasCorrentes){
            if (Integer.parseInt(conta.getId()) > numeroNovo) numeroNovo = Integer.parseInt(conta.getId());
        }
        numeroNovo ++;

        return numeroNovo;
    }
}
