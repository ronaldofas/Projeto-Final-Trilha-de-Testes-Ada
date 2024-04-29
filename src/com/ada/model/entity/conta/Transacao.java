package com.ada.model.entity.conta;

import com.ada.model.entity.cliente.Cliente;
import com.ada.model.helpers.enums.TipoTransacao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Transacao {
    private TipoTransacao tipoTransacao;
    private double valor;
    private LocalDateTime dataTransacao;
    private String observacao;
    private Cliente destinatario;
    private Cliente remetente;

    public Transacao(TipoTransacao tipoTransacao, double valor) {
        this.tipoTransacao = tipoTransacao;
        this.valor = valor;
        this.dataTransacao = LocalDateTime.now();
        this.observacao = tipoTransacao.getDescricao();
    }

    public void setDestinatario(Cliente destinatario) {
        this.destinatario = destinatario;
    }

    public void setRemetente(Cliente remetente) {
        this.remetente = remetente;
    }

    public void setDestinatarioERemetente(Cliente cliente){
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
                " - Destinatario: " + destinatario.getNome() + String.format(" - Valor: R$ %.2f", valor)
                + " - Observação: " + observacao;
    }
}
