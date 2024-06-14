package com.ada.model.entity.conta;

import com.ada.model.entity.cliente.Cliente;
import com.ada.model.entity.cliente.IdentificadorCPF;
import com.ada.model.helpers.enums.Classificacao;
import com.ada.model.helpers.enums.TipoTransacao;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransacaoTest {

    @Test
    void testaTransacao() {
        TipoTransacao tipoTransacao = TipoTransacao.TRANSFERENCIA;
        double valor = 100.0;
        Transacao transacao = new Transacao(tipoTransacao, valor);

        assertEquals(tipoTransacao, transacao.getTipoTransacao());
        assertEquals(valor, transacao.getValor());
        assertNotNull(transacao.getDataTransacao());
        assertNull(transacao.getRemetente());
        assertNull(transacao.getDestinatario());
    }

    @Test
    void testaSetObservacao() {
        TipoTransacao tipoTransacao = TipoTransacao.TRANSFERENCIA;
        double valor = 100.0;
        Transacao transacao = new Transacao(tipoTransacao, valor);

        String observacao = "Transferência realizada com sucesso";
        transacao.setObservacao(observacao);

        assertEquals(observacao, transacao.getObservacao());
    }

    @Test
    void testaSetRemetente() {
        TipoTransacao tipoTransacao = TipoTransacao.TRANSFERENCIA;
        double valor = 100.0;
        Transacao transacao = new Transacao(tipoTransacao, valor);

        Cliente remetente =
                new Cliente( new IdentificadorCPF("12345678901"), Classificacao.PF, "João");
        transacao.setRemetente(remetente);

        assertEquals(remetente, transacao.getRemetente());
    }

    @Test
    void testaSetDestinatario() {
        TipoTransacao tipoTransacao = TipoTransacao.TRANSFERENCIA;
        double valor = 100.0;
        Transacao transacao = new Transacao(tipoTransacao, valor);

        Cliente destinatario =
                new Cliente( new IdentificadorCPF("12345678901"), Classificacao.PF, "Maria");
        transacao.setDestinatario(destinatario);

        assertEquals(destinatario, transacao.getDestinatario());
    }
}