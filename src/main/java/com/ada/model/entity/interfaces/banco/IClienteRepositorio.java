package com.ada.model.entity.interfaces.banco;

import com.ada.model.entity.cliente.Cliente;

import java.util.List;

public interface IClienteRepositorio {
    void atualizar(Cliente cliente);
    List<Cliente> obterClientes();
    Cliente buscarPorId(String id);
    void salvar(Cliente cliente);
    void excluirCliente(Cliente cliente);
    Cliente buscarPorNome(String nome);
}
