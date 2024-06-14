package com.ada.controller;

import com.ada.model.entity.cliente.Cliente;
import com.ada.model.entity.cliente.IdentificadorCPF;
import com.ada.model.entity.conta.ContaPoupanca;
import com.ada.model.entity.interfaces.banco.IContaRepositorio;
import com.ada.model.helpers.enums.Classificacao;
import com.ada.model.helpers.enums.TipoDeContaEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class BancoControllerTest {

    @InjectMocks
    private BancoController bancoController;

    @Mock
    private IContaRepositorio contaRepositorio;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAbrirContaPoupancaPessoaFisica() {
        // Arrange
        IdentificadorCPF identificadorCPF = new IdentificadorCPF("12345678901");
        Cliente cliente = new Cliente(identificadorCPF, Classificacao.PF, "João da Silva");
        TipoDeContaEnum tipoConta = TipoDeContaEnum.CONTA_POUPANCA;

        // Act
        bancoController.abrirConta(cliente, tipoConta);

        // Assert
        verify(contaRepositorio, times(1)).salvar(any(ContaPoupanca.class));
    }

    // Adicione mais testes para as outras funções do BancoController

}