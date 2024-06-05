package com.ada.controller;

import com.ada.model.entity.cliente.Cliente;
import com.ada.model.entity.interfaces.banco.IClienteRepositorio;

import java.util.List;

public class ClienteController {
    private final IClienteRepositorio clienteRepositorio;

    public ClienteController(final IClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    public void cadastrarCliente(final Cliente cliente){
        clienteRepositorio.salvar(cliente);
    }

    public Cliente buscarClientePorIdentificador(final String id){
        return clienteRepositorio.buscarPorId(id);
    }

    public Cliente buscarClientePorNome(final String nome){
        return clienteRepositorio.buscarPorNome(nome);
    }

    public void atualizarCliente(final Cliente cliente){
        clienteRepositorio.atualizar(cliente);
    }

    public List<Cliente> listarClientes(){
        return clienteRepositorio.obterClientes();
    }
}
