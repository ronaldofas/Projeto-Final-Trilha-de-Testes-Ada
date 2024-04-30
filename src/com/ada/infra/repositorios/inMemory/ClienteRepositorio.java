package com.ada.infra.repositorios.inMemory;

import com.ada.model.entity.cliente.Cliente;
import com.ada.model.entity.interfaces.banco.IClienteRepositorio;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepositorio implements IClienteRepositorio {
    final List<Cliente> clientes;

    public ClienteRepositorio() {
        this.clientes = new ArrayList<>();
    }

    @Override
    public void atualizar(Cliente cliente) {
        Cliente clienteAhAtualizar = buscarPorId(cliente.getIdentificador());
        clienteAhAtualizar.alterarNome(cliente.getNome());
        if (cliente.isStatus() != clienteAhAtualizar.isStatus())
            clienteAhAtualizar.ativarDesativar();
        excluirCliente(cliente);
        salvar(cliente);
    }

    @Override
    public List<Cliente> obterClientes() {
        return clientes;
    }

    @Override
    public Cliente buscarPorId(String id) {
        for (Cliente cliente : clientes){
            if (cliente.getIdentificador().equals(id))
                return cliente;
        }
        throw new RuntimeException("Cliente não localizado!");
    }

    @Override
    public void salvar(Cliente cliente) {
        clientes.add(cliente);
    }

    @Override
    public void excluirCliente(Cliente cliente) {
        if(clientes.contains(cliente))
            clientes.remove(cliente);
        else
            throw new RuntimeException("Cliente não localizado!");
    }

    @Override
    public Cliente buscarPorNome(String nome) {
        for (Cliente cliente : clientes){
            if (cliente.getNome().contains(nome))
                return cliente;
        }
        throw new RuntimeException("Cliente não localizado!");
    }
}
