package com.ada.model.entity.conta;

import com.ada.model.entity.cliente.Cliente;
import com.ada.model.helpers.enums.TipoTransacao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Transacao {
    private final TipoTransacao tipoTransacao;
    private final double valor;
    private final LocalDateTime dataTransacao;
    private String observacao;
    private Cliente destinatario;
    private Cliente remetente;

    public Transacao(final TipoTransacao tipoTransacao, final double valor) {
        this.tipoTransacao = tipoTransacao;
        this.valor = valor;
        this.dataTransacao = LocalDateTime.now();
        this.observacao = tipoTransacao.getDescricao();
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public Cliente getDestinatario() {
        return destinatario;
    }

    public Cliente getRemetente() {
        return remetente;
    }

    public void setObservacao(final String observacao) {
        this.observacao = observacao;
    }

    public void setDestinatario(final Cliente destinatario) {
        this.destinatario = destinatario;
    }

    public void setRemetente(final Cliente remetente) {
        this.remetente = remetente;
    }

    public void setDestinatarioERemetente(final Cliente cliente){
        this.remetente =  cliente;
        this.destinatario = cliente;
    }

    private String formatarData(){
        DateTimeFormatter novo = DateTimeFormatter
                .ofPattern("dd/MM/uuuu HH:mm:ss")
                .withResolverStyle(ResolverStyle.STRICT);
        return dataTransacao.format(novo);
    }

    @Override
    public String toString(){
        return "Data: " + formatarData() + " - Tipo transação: " + tipoTransacao.toString() +
                " - Destinatario: " + destinatario.getNome() + String.format(" - Valor: R$ %.2f", (Object)valor)
                + " - Observação: " + observacao;
    }
}
