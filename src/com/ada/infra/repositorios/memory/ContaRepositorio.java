package com.ada.infra.repositorios.memory;

import com.ada.model.entity.conta.ContaInvestimento;
import com.ada.model.entity.interfaces.banco.IContaRepositorio;
import com.ada.model.entity.interfaces.conta.Conta;

import java.util.ArrayList;
import java.util.List;

public class ContaRepositorio implements IContaRepositorio {
    private final List<Conta> contas;

    public ContaRepositorio() {
        this.contas = new ArrayList<>();
    }

    @Override
    public void atualizar(final Conta conta) {
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
    public List<Conta> buscarContas(final String identificador) {
        List<Conta> contasLocalizadas = new ArrayList<>();
        for (Conta conta : contas){
            if (conta.getCliente().getIdentificador().equals(identificador))
                contasLocalizadas.add(conta);
        }
        return contasLocalizadas;
    }

    @Override
    public Conta buscarPorNumero(final String numero) {
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numero))
                return conta;
        }
        throw new IllegalArgumentException("Conta não localizada!");
    }

    @Override
    public void salvar(final Conta conta) {
        contas.add(conta);
    }

    @Override
    public void removerConta(final Conta conta) {
        contas.remove(conta);
    }

    @Override
    public Conta buscarContaInvestimento(final String id) {
        Conta resultado = null;
        for (Conta conta : contas){
            if (conta instanceof ContaInvestimento &&
                conta.getCliente().getIdentificador().equals(id))
                resultado = conta;
        }
        return resultado;
    }
}
