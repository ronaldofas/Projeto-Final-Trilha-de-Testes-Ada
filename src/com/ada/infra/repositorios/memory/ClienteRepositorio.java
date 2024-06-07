package com.ada.infra.repositorios.memory;

import com.ada.model.entity.cliente.Cliente;
import com.ada.model.entity.interfaces.banco.IClienteRepositorio;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepositorio implements IClienteRepositorio {
    private final List<Cliente> clientes;
    private static final String MENSAGEMNAOLOCALIZADO = "Cliente não localizado!";

    public ClienteRepositorio() {
        this.clientes = new ArrayList<>();
    }

    @Override
    public void atualizar(final Cliente cliente) {
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
    public Cliente buscarPorId(final String id) {
        for (Cliente cliente : clientes){
            if (cliente.getIdentificador().equals(id))
                return cliente;
        }
        throw new IllegalArgumentException(MENSAGEMNAOLOCALIZADO);
    }

    @Override
    public void salvar(final Cliente cliente) {
        clientes.add(cliente);
    }

    @Override
    public void excluirCliente(final Cliente cliente) {
        if(clientes.contains(cliente))
            clientes.remove(cliente);
        else
            throw new IllegalArgumentException(MENSAGEMNAOLOCALIZADO);
    }

    @Override
    public Cliente buscarPorNome(final String nome) {
        for (Cliente cliente : clientes){
            if (cliente.getNome().contains(nome))
                return cliente;
        }
        throw new IllegalArgumentException(MENSAGEMNAOLOCALIZADO);
    }
}
