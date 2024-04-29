package com.ada.model.entity;

import com.ada.model.entity.cliente.Cliente;
import com.ada.model.entity.conta.ContaCorrente;
import com.ada.model.entity.interfaces.conta.Conta;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private final List<Cliente> clientes;
    private final List<Conta> contas;

    public Banco() {
        clientes = new ArrayList<>();
        contas = new ArrayList<>();
    }

    public List<Cliente> ObterListaDeClientes() {
        return new ArrayList<>(clientes);
    }

    public List<Conta> obterListaDeContas() {
        return new ArrayList<>(contas);
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

    public Conta encontrarContaPorId(String numeroConta) {
        for (Conta poupanca : contas) {
            if (poupanca != null && poupanca.getNumero().equals(numeroConta)) {
                return poupanca;
            }
        }
        throw new RuntimeException("Conta não localizada");
    }

    public void adicionarContaNova(Conta conta){
        contas.add(conta);
    }

    public String obterNumeroDeContaParaAbertura() {
        int numeroNovo = 0;

        for (Conta conta : contas){
            if (Integer.parseInt(conta.getNumero()) > numeroNovo)
                numeroNovo = Integer.parseInt(conta.getNumero());
        }
        numeroNovo ++;

        return Integer.toString(numeroNovo);
    }
}
