package com.ada.model.entity.conta;

import com.ada.model.entity.cliente.Cliente;
import com.ada.model.entity.cliente.IdentificadorCNPJ;
import com.ada.model.helpers.enums.Classificacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.mock;

public class ContaInvestimentoTest {

    private ContaInvestimento contaInvestimento;
    private Cliente cliente;
    private Transacao transacao;

    @BeforeEach
    void setup() {
        cliente = new Cliente(new IdentificadorCNPJ("12345678901234"), Classificacao.PJ, "Nome");
        contaInvestimento = new ContaInvestimento(cliente);
        transacao = mock(Transacao.class);
    }

    @Test
    void testSacarComSaldoInsuficiente() {
        double valor = 10;
        contaInvestimento.depositar(100);
        contaInvestimento.sacar(valor);
        double saldoAnterior = contaInvestimento.consultarSaldo();
        Assertions.assertEquals(saldoAnterior, contaInvestimento.consultarSaldo());
    }

    @Test
    void testSacarComSaldoSuficiente() {
        double valor = 100;
        contaInvestimento.depositar(1000);
        double saldoAnterior = contaInvestimento.consultarSaldo();
        contaInvestimento.sacar(valor);
        double saldoAtual = contaInvestimento.consultarSaldo();
        Assertions.assertNotEquals(saldoAnterior, saldoAtual);
        Assertions.assertEquals(saldoAtual, contaInvestimento.consultarSaldo());
    }

    @Test
    void testDepositar() {
        double valor = 500;
        double saldoAnterior = contaInvestimento.consultarSaldo();
        contaInvestimento.depositar(valor);
        double saldoAtual = contaInvestimento.consultarSaldo();
        Assertions.assertNotEquals(saldoAnterior, saldoAtual);
        Assertions.assertEquals(saldoAnterior + valor, saldoAtual);
    }

    @Test
    void testTransferir() {
        double valor = 200;
        ContaCorrente contaDestino = new ContaCorrente(cliente);
        contaInvestimento.depositar(1000);
        double saldoAnterior = contaInvestimento.consultarSaldo();
        contaInvestimento.transferir(valor, contaDestino);
        Assertions.assertEquals(valor, contaDestino.consultarSaldo());
        Assertions.assertEquals(saldoAnterior - valor, contaInvestimento.consultarSaldo());
    }

    @Test
    void testAtivarDesativar() {
        contaInvestimento.ativarDesativar();
        Assertions.assertEquals(false, contaInvestimento.isStatus());
        contaInvestimento.ativarDesativar();
        Assertions.assertEquals(true, contaInvestimento.isStatus());
    }

    @Test
    void testCriarTransacao() {
        List<Transacao> transacoes = new ArrayList<>();
        transacoes.add(transacao);
        contaInvestimento.criarTransacao(transacao);
        Assertions.assertEquals(1, contaInvestimento.getTransacoes().size());
        Assertions.assertEquals(transacoes.get(0), contaInvestimento.getTransacoes().get(0));
    }

    @Test
    void testConsultarSaldo() {
        double saldoInicial = contaInvestimento.consultarSaldo();
        double valorDepositado = 100;
        contaInvestimento.depositar(valorDepositado);
        double saldoAtual = contaInvestimento.consultarSaldo();
        Assertions.assertEquals(saldoInicial + valorDepositado, saldoAtual);
    }
}