package com.ada.model.entity.cliente;

import com.ada.model.helpers.enums.Classificacao;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    void testAlterarNomePessoaFisica() {
        Cliente cliente = new Cliente(new IdentificadorCPF("12345678901"), Classificacao.PF, "João");

        cliente.alterarNome("Maria");

        assertEquals("Maria", cliente.getNome());
    }

    @Test
    void testAlterarNomePessoaJuridica() {
        Cliente cliente = new Cliente(
                new IdentificadorCNPJ("12345678000190"), Classificacao.PJ, "Empresa do João");

        cliente.alterarNome("Empresa da Maria");

        assertEquals("Empresa da Maria", cliente.getNome());
    }

    @Test
    void testAtivarDesativar() {
        Cliente cliente = new Cliente(new IdentificadorCPF("12345678901"), Classificacao.PF, "João");

        cliente.ativarDesativar();

        assertFalse(cliente.isStatus());
    }

    @Test
    void testToString() {
        // Arrange
        Cliente cliente = new Cliente(new IdentificadorCPF("12345678901"), Classificacao.PF, "João");

        // Act
        cliente.ativarDesativar();
        LocalDate data = cliente.getDataCadastro();

        String expected =
                "Id: 12345678901 - Tipo: PF - Nome: João - Data de Cadastro: " + data + " - Ativo: false";
        assertEquals(expected, cliente.toString());
    }

    @Test
    void testValidar() {
        // Teste de validação com ID nulo
        assertThrows(IllegalArgumentException.class, () ->
                new Cliente(new IdentificadorCPF(null), Classificacao.PF, "João"));

        // Teste de validação com Tipo nulo
        assertThrows(IllegalArgumentException.class, () ->
                new Cliente(new IdentificadorCPF("12345678901"), null, "João"));

        // Teste de validação com Nome vazio
        assertThrows(IllegalArgumentException.class, () ->
                new Cliente(new IdentificadorCPF("12345678901"), Classificacao.PF, ""));
    }
}