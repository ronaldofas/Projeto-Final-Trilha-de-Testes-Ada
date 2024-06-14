package com.ada.model.entity.conta;

import com.ada.model.entity.cliente.Cliente;
import com.ada.model.entity.cliente.IdentificadorCNPJ;
import com.ada.model.helpers.enums.Classificacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

class ContaCorrenteTest {

    private ContaCorrente contaCorrente;
    private Cliente cliente;
    private Transacao transacao;

    @BeforeEach
    void setup() {
        cliente = new Cliente(new IdentificadorCNPJ("12345678901234"), Classificacao.PF, "Nome");
        contaCorrente = new ContaCorrente(cliente);
        transacao = mock(Transacao.class);
    }

    @Test
    void testSacarComSaldoInsuficiente() {
        double valor = 10;
        contaCorrente.depositar(100);
        contaCorrente.sacar(valor);
        double saldoAnterior = contaCorrente.consultarSaldo();
        Assertions.assertEquals(saldoAnterior, contaCorrente.consultarSaldo());
    }

    @Test
    void testSacarComSaldoSuficiente() {
        double valor = 100;
        contaCorrente.depositar(1000);
        double saldoAnterior = contaCorrente.consultarSaldo();
        contaCorrente.sacar(valor);
        double saldoAtual = contaCorrente.consultarSaldo();
        Assertions.assertNotEquals(saldoAnterior, saldoAtual);
        Assertions.assertEquals(saldoAtual, contaCorrente.consultarSaldo());
    }

    @Test
    void testDepositar() {
        double valor = 500;
        double saldoAnterior = contaCorrente.consultarSaldo();
        contaCorrente.depositar(valor);
        double saldoAtual = contaCorrente.consultarSaldo();
        Assertions.assertNotEquals(saldoAnterior, saldoAtual);
        Assertions.assertEquals(saldoAnterior + valor, saldoAtual);
    }

    @Test
    void testTransferir() {
        double valor = 200;
        ContaCorrente contaDestino = new ContaCorrente(cliente);
        contaCorrente.depositar(1000);
        double saldoAnterior = contaCorrente.consultarSaldo();
        contaCorrente.transferir(valor, contaDestino);
        Assertions.assertEquals(valor, contaDestino.consultarSaldo());
        Assertions.assertEquals(saldoAnterior - valor, contaCorrente.consultarSaldo());
    }

    @Test
    void testAtivarDesativar() {
        contaCorrente.ativarDesativar();
        Assertions.assertEquals(false, contaCorrente.isStatus());
        contaCorrente.ativarDesativar();
        Assertions.assertEquals(true, contaCorrente.isStatus());
    }

    @Test
    void testCriarTransacao() {
        List<Transacao> transacoes = new ArrayList<>();
        transacoes.add(transacao);
        contaCorrente.criarTransacao(transacao);
        Assertions.assertEquals(1, contaCorrente.getTransacoes().size());
        Assertions.assertEquals(transacoes.get(0), contaCorrente.getTransacoes().get(0));
    }

    @Test
    void testConsultarSaldo() {
        double saldoInicial = contaCorrente.consultarSaldo();
        double valorDepositado = 100;
        contaCorrente.depositar(valorDepositado);
        double saldoAtual = contaCorrente.consultarSaldo();
        Assertions.assertEquals(saldoInicial + valorDepositado, saldoAtual);
    }
}