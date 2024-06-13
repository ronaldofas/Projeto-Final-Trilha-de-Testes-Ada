package com.ada.view.gui.model;

import com.ada.model.entity.conta.Transacao;

import javax.swing.table.AbstractTableModel;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.List;

public class TransacaoModel extends AbstractTableModel {

    private List<Transacao> transacoes;

    public TransacaoModel(final List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }

    @Override
    public int getRowCount() {
        return transacoes.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        Transacao transacao = transacoes.get(rowIndex);

        DateTimeFormatter novo = DateTimeFormatter
                .ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);

        return switch (columnIndex) {
            case 0 -> transacao.getDataTransacao().format(novo);
            case 1 -> transacao.getTipoTransacao().getDescricao();
            case 2 -> transacao.getValor();
            case 3 -> transacao.getObservacao();
            case 4 -> transacao.getRemetente().getNome();
            default -> "";
        };
    }

    @Override
    public String getColumnName(final int column) {
        return switch (column) {
            case 0 -> "Data";
            case 1 -> "Transação";
            case 2 -> "Valor";
            case 3 -> "Observação";
            case 4 -> "Nome Remetente";
            default -> "";
        };
    }

    public void atualizarTransacoes(final List<Transacao> transacoes){
        this.transacoes = transacoes;
    }
}
