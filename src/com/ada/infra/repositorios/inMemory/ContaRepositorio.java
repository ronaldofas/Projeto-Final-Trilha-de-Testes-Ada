package com.ada.infra.repositorios.inMemory;

import com.ada.model.entity.conta.ContaInvestimento;
import com.ada.model.entity.interfaces.banco.IContaRepositorio;
import com.ada.model.entity.interfaces.conta.Conta;

import java.util.ArrayList;
import java.util.List;

public class ContaRepositorio implements IContaRepositorio {
    final List<Conta> contas;

    public ContaRepositorio() {
        this.contas = new ArrayList<>();
    }

    @Override
    public void atualizar(Conta conta) {
        Conta contaAhAtualizar = buscarPorNumero(conta.getNumero());
        if (contaAhAtualizar.isStatus() != conta.isStatus())
            contaAhAtualizar.ativarDesativar();
        removerConta(conta);
        salvar(contaAhAtualizar);
    }

    @Override
    public List<Conta> buscarContas() {
        return contas;
    }

    @Override
    public List<Conta> buscarContas(String identificador) {
        List<Conta> contasLocalizadas = new ArrayList<>();
        for (Conta conta : contas){
            if (conta.getCliente().getIdentificador().equals(identificador))
                contasLocalizadas.add(conta);
        }
        return contasLocalizadas;
    }

    @Override
    public Conta buscarPorNumero(String numero) {
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numero))
                return conta;
        }
        throw new RuntimeException("Conta não localizada!");
    }

    @Override
    public void salvar(Conta conta) {
        contas.add(conta);
    }

    @Override
    public void removerConta(Conta conta) {
        contas.remove(conta);
    }

    @Override
    public Conta buscarContaInvestimento(String id) {
        Conta resultado = null;
        for (Conta conta : contas){
            if (conta instanceof ContaInvestimento &&
                conta.getCliente().getIdentificador().equals(id))
                resultado = conta;
        }
        return resultado;
    }
}
