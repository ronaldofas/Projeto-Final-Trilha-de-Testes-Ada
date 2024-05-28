package com.ada.controller;

import com.ada.model.entity.cliente.Cliente;
import com.ada.model.entity.interfaces.banco.IClienteRepositorio;

import java.util.List;

public class ClienteController {
    private final IClienteRepositorio clienteRepositorio;

    public ClienteController(IClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    public void cadastrarCliente(Cliente cliente){
        clienteRepositorio.salvar(cliente);
    }

    public Cliente buscarClientePorIdentificador(String id){
        return clienteRepositorio.buscarPorId(id);
    }

    public Cliente buscarClientePorNome(String nome){
        return clienteRepositorio.buscarPorNome(nome);
    }

    public void atualizarCliente(Cliente cliente){
        clienteRepositorio.atualizar(cliente);
    }

    public List<Cliente> listarClientes(){
        return clienteRepositorio.obterClientes();
    }
}
