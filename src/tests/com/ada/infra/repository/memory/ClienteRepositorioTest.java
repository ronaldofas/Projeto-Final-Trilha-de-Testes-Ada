package com.ada.infra.repository.memory;

import com.ada.infra.repositorios.memory.ClienteRepositorio;
import com.ada.model.entity.cliente.Cliente;
import com.ada.model.entity.cliente.IdentificadorCNPJ;
import com.ada.model.entity.cliente.IdentificadorCPF;
import com.ada.model.helpers.enums.Classificacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteRepositorioTest {

    private ClienteRepositorio clienteRepositorio;

    @BeforeEach
    void setUp() {
        clienteRepositorio = mock(ClienteRepositorio.class);
    }

    @Test
    void testSalvarClientePessoaFisica() {
        // Arrange
        Cliente cliente = new Cliente(
                new IdentificadorCPF("12345678901"), Classificacao.PF, "João da Silva"
        );
        List<Cliente> listaClientes = new ArrayList<>();
        listaClientes.add(cliente);

        // Act
        clienteRepositorio.salvar(cliente);
        when(clienteRepositorio.obterClientes()).thenReturn(listaClientes);
        List<Cliente> clientes = clienteRepositorio.obterClientes();
        assertEquals(1, clientes.size());
        assertEquals(cliente, clientes.get(0));
        assertEquals(cliente.getClassificacao(), clientes.get(0).getClassificacao());
        verify(clienteRepositorio, times(1)).salvar(any(Cliente.class));
    }

    @Test
    void testSalvarClientePessoaJuridica() {
        // Arrange
        Cliente cliente = new Cliente(
                new IdentificadorCNPJ("12345678000190"), Classificacao.PJ, "Empresa do João"
        );
        List<Cliente> listaClientes = new ArrayList<>();
        listaClientes.add(cliente);

        // Act
        clienteRepositorio.salvar(cliente);
        when(clienteRepositorio.obterClientes()).thenReturn(listaClientes);
        List<Cliente> clientes = clienteRepositorio.obterClientes();
        assertEquals(1, clientes.size());
        assertEquals(cliente, clientes.get(0));
        assertEquals(cliente.getClassificacao(), clientes.get(0).getClassificacao());
        verify(clienteRepositorio, times(1)).salvar(any(Cliente.class));
    }

    @Test
    void testBuscarId() {
        Cliente cliente = new Cliente(
                new IdentificadorCPF("12345678901"), Classificacao.PF, "João da Silva"
        );

        when(clienteRepositorio.buscarPorId(any())).thenReturn(cliente);
        Cliente buscado = clienteRepositorio.buscarPorId(cliente.getId().toString());
        assertEquals(cliente, buscado);
    }


    @Test
    void testBuscarPorNome() {
        Cliente cliente = new Cliente(
                new IdentificadorCPF("12345678901"), Classificacao.PF, "João");

        when(clienteRepositorio.buscarPorNome(any())).thenReturn(cliente);
        Cliente clienteLocalizado = clienteRepositorio.buscarPorNome("João");
        assertEquals(cliente, clienteLocalizado);
    }

    @Test
    void testAtualizar() {
        Cliente cliente = new Cliente(
                new IdentificadorCPF("12345678901"), Classificacao.PF, "João da Silva"
        );
        clienteRepositorio.salvar(cliente);
        cliente.alterarNome("João");
        clienteRepositorio.atualizar(cliente);
        when(clienteRepositorio.buscarPorId(any())).thenReturn(cliente);
        Cliente atualizado = clienteRepositorio.buscarPorId(cliente.getId().toString());
        assertEquals("João", atualizado.getNome());
    }

    @Test
    void testExcluirCliente() {
        Cliente cliente = new Cliente(
                new IdentificadorCPF("12345678901"), Classificacao.PF, "João da Silva"
        );
        clienteRepositorio.salvar(cliente);
        clienteRepositorio.excluirCliente(cliente);
        assertEquals(0, clienteRepositorio.obterClientes().size());
    }
}
