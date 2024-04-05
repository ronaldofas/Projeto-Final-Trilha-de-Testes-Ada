package com.ada.controller;

import com.ada.helpers.enums.TipoClienteEnum;
import com.ada.model.entity.Banco;
import com.ada.model.entity.Cliente;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BancoGUIController {
    private final Banco banco = new Banco();

    public void adicionarCliente(String id, int tipo, String nome){
        TipoClienteEnum tipoCliente = getTipoCliente(tipo);

        Cliente cliente = new Cliente(id, tipoCliente, nome);
        banco.adicionarClienteNovo(cliente);
    }

    @NotNull
    private static TipoClienteEnum getTipoCliente(int tipo) {
        return switch (tipo) {
            case 0 -> TipoClienteEnum.PESSOA_FISICA;
            case 1 -> TipoClienteEnum.PESSOA_JURIDICA;
            default -> throw new RuntimeException("Tipo de Cliente inválido!!!");
        };
    }

    public List<Cliente> obterClientes(){
        return banco.getClientes();
    }
}
