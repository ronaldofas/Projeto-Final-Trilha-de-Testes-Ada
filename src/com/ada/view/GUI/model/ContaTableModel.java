package com.ada.view.GUI.model;

import com.ada.model.entity.Cliente;
import com.ada.model.entity.Conta;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ContaTableModel extends AbstractTableModel {

    private List<Conta> contas;

    public ContaTableModel(List<Conta> contas) {
        this.contas = contas;
    }

    @Override
    public int getRowCount() {
        return contas.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Id";
            case 1 -> "Cpf/CNPJ";
            case 2 -> "Nome do Cliente";
            case 3 -> "Saldo";
            case 4 -> "Data Atualização";
            case 5 -> "Status";
            default -> "";
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Conta conta = contas.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> conta.getId();
            case 1 -> conta.getCliente().getId();
            case 2 -> conta.getCliente().getNome();
            case 3 -> conta.consultarSaldo();
            case 4 -> conta.getDataAtualizacao();
            case 5 -> conta.isStatus() ? "Ativo" : "Inativo";
            default -> "";
        };
    }

    public void atualizarContas(List<Conta> contas){
        this.contas = contas;
    }
}