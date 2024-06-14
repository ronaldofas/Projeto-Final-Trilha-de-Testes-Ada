package com.ada.model.entity.conta;

import com.ada.model.entity.cliente.Cliente;
import com.ada.model.entity.cliente.IdentificadorCNPJ;
import com.ada.model.helpers.enums.Classificacao;
import com.ada.model.helpers.services.ArredondamentoDouble;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

class ContaPoupancaTest {

    private ContaPoupanca contaPoupanca;
    private Cliente cliente;
    private Transacao transacao;

    @BeforeEach
    void setup() {
        cliente = new Cliente(new IdentificadorCNPJ("12345678901234"), Classificacao.PF, "Nome");
        contaPoupanca = new ContaPoupanca(cliente);
        transacao = mock(Transacao.class);
    }

    @Test
    void testSacarComSaldoInsuficiente() {
        double valor = 10;
        contaPoupanca.depositar(100);
        contaPoupanca.sacar(valor);
        double saldoAnterior = contaPoupanca.consultarSaldo();
        Assertions.assertEquals(saldoAnterior, contaPoupanca.consultarSaldo());
    }

    @Test
    void testSacarComSaldoSuficiente() {
        double valor = 100;
        contaPoupanca.depositar(1000);
        double saldoAnterior = contaPoupanca.consultarSaldo();
        contaPoupanca.sacar(valor);
        double saldoAtual = contaPoupanca.consultarSaldo();
        Assertions.assertNotEquals(saldoAnterior, saldoAtual);
        Assertions.assertEquals(saldoAtual, contaPoupanca.consultarSaldo());
    }

    @Test
    void testDepositar() {
        double valor = 500;
        double saldoAnterior = contaPoupanca.consultarSaldo();
        contaPoupanca.depositar(valor);
        double saldoAtual = contaPoupanca.consultarSaldo();
        Assertions.assertNotEquals(saldoAnterior, saldoAtual);
        Assertions.assertEquals(
                saldoAnterior + ArredondamentoDouble.arredondar(valor * 1.005), saldoAtual);
    }

    @Test
    void testTransferir() {
        double valor = 200;
        ContaPoupanca contaDestino = new ContaPoupanca(cliente);
        contaPoupanca.depositar(1000);
        double saldoAnterior = contaPoupanca.consultarSaldo();
        contaPoupanca.transferir(valor, contaDestino);
        Assertions.assertEquals(ArredondamentoDouble.arredondar(valor * 1.005), contaDestino.consultarSaldo());
        Assertions.assertEquals(saldoAnterior - valor, contaPoupanca.consultarSaldo());
    }

    @Test
    void testAtivarDesativar() {
        contaPoupanca.ativarDesativar();
        Assertions.assertEquals(false, contaPoupanca.isStatus());
        contaPoupanca.ativarDesativar();
        Assertions.assertEquals(true, contaPoupanca.isStatus());
    }

    @Test
    void testCriarTransacao() {
        List<Transacao> transacoes = new ArrayList<>();
        transacoes.add(transacao);
        contaPoupanca.criarTransacao(transacao);
        Assertions.assertEquals(1, contaPoupanca.getTransacoes().size());
        Assertions.assertEquals(transacoes.get(0), contaPoupanca.getTransacoes().get(0));
    }
}