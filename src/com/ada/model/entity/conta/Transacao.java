package com.ada.model.entity.conta;

import com.ada.model.entity.cliente.Cliente;
import com.ada.model.helpers.enums.TipoTransacao;

import java.time.LocalDateTime;

public class Transacao {
    private TipoTransacao tipoTransacao;
    private double valor;
    private LocalDateTime dataTransacao;
    private String observacao;
    private Cliente destinatario;

    public Transacao(TipoTransacao tipoTransacao, double valor, String observacao, Cliente destinatario) {
        this.tipoTransacao = tipoTransacao;
        this.valor = valor;
        this.observacao = observacao;
        this.destinatario = destinatario;
    }
}
