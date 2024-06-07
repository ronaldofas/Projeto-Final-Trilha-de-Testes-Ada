package com.ada.model.entity.interfaces.banco;

import com.ada.model.entity.interfaces.conta.Conta;

import java.util.List;

public interface IContaRepositorio {
    void atualizar(Conta conta);
    List<Conta> buscarContas();
    List<Conta> buscarContas(String identificador);
    Conta buscarPorNumero(String numero);
    void salvar(Conta conta);
    void removerConta(Conta conta);
    Conta buscarContaInvestimento(String id);
}
