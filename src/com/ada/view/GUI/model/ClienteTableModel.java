package com.ada.view.GUI.model;

import com.ada.model.entity.Cliente;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ClienteTableModel extends AbstractTableModel {

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
    public String getColumnName(int column) {
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = clientes.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> cliente.getId();
            case 1 -> cliente.getTipo().toString();
            case 2 -> cliente.getNome();
            case 3 -> cliente.getDataCadastro().toString();
            case 4 -> cliente.isStatus() ? "Ativo" : "Inativo";
            default -> "";
        };
    }

    public void atualizarClientes(List<Cliente> clientes){
        this.clientes = clientes;
    }
}