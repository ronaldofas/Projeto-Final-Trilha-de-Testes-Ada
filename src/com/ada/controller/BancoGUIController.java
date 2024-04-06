package com.ada.controller;

import com.ada.helpers.enums.TipoClienteEnum;
import com.ada.helpers.enums.TipoDeContaEnum;
import com.ada.model.entity.Banco;
import com.ada.model.entity.Cliente;
import com.ada.model.entity.ContaCorrente;
import com.ada.model.entity.ContaPoupanca;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BancoGUIController {
    private final Banco banco = new Banco();

    public void adicionarCliente(String id, int tipo, String nome){
        TipoClienteEnum tipoCliente = getTipoCliente(tipo);

        Cliente cliente = new Cliente(id, tipoCliente, nome);
        banco.adicionarClienteNovo(cliente);
    }

    public void adicionarConta(String id, TipoDeContaEnum tipo) throws RuntimeException{
        switch (tipo){
            case TipoDeContaEnum.CONTA_POUPANCA -> {
                int idConta = banco.obterNumeroDeContaPoupancaParaAbertura();
                Cliente cliente = banco.pesquisarClientePorId(id);
                ContaPoupanca conta = new ContaPoupanca(idConta, cliente);
                banco.adicionarContaPoupancaNova(conta);
            }
            case TipoDeContaEnum.CONTA_CORRENTE -> {
                int idConta = banco.obterNumeroDeContaCorrenteParaAbertura();
                Cliente cliente = banco.pesquisarClientePorId(id);
                ContaCorrente conta = new ContaCorrente(idConta, cliente);
                banco.adicionarContaCorrenteNova(conta);
            }
            default -> throw new RuntimeException("Tipo de conta inválido!");
        }
    }

    @NotNull
    private static TipoClienteEnum getTipoCliente(int tipo) {
        return switch (tipo) {
            case 0 -> TipoClienteEnum.PESSOA_FISICA;
            case 1 -> TipoClienteEnum.PESSOA_JURIDICA;
            default -> throw new RuntimeException("Tipo de Cliente inválido!!!");
        };
    }

    public List<Cliente> obterClientes(){
        return banco.ObterListaDeClientes();
    }
    public List<ContaPoupanca> obterContasPoupanca(){
        return banco.obterListaDeContasPoupanca();
    }
    public List<ContaCorrente> obterContasCorrente(){
        return  banco.obterListaDeContasCorrentes();
    }

}
