package com.ada.view.gui.model;

import com.ada.model.entity.cliente.Cliente;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ClienteTableModel extends AbstractTableModel{

    private List<Cliente> clientes;

    public ClienteTableModel(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(final int column) {
        return switch (column) {
            case 0 -> "Id";
            case 1 -> "Tipo";
            case 2 -> "Nome";
            case 3 -> "Data Cadastro";
            case 4 -> "Status";
            default -> "";
        };
    }

    @Override
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        Cliente cliente = clientes.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> cliente.getIdentificador();
            case 1 -> cliente.getClassificacao().toString();
            case 2 -> cliente.getNome();
            case 3 -> cliente.getDataCadastro().toString();
            case 4 -> cliente.isStatus() ? "Ativo" : "Inativo";
            default -> "";
        };
    }

    public void atualizarClientes(final List<Cliente> clientes){
        this.clientes = clientes;
    }
}